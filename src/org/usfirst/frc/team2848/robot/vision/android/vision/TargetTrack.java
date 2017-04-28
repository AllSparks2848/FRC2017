package org.usfirst.frc.team2848.robot.vision.android.vision;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.util.Translation2d;

import edu.wpi.first.wpilibj.Timer;

public class TargetTrack {
	Map<Double, Translation2d> observedPositions = new TreeMap<>();
	Translation2d smoothedPosition = null;
	int id;
	
	// TODO: Tweak these parameters if needed
	private static final double MAX_DISTANCE = 18.0;
	public static final double MAX_AGE = 0.3;
	private static final double FRAME_RATE = 20;
	
	public TargetTrack(double timestamp, Translation2d initialObservation, int id) {
		observedPositions.put(timestamp, initialObservation);
		smoothedPosition = initialObservation;
		this.id = id;
	}
	
	public boolean tryUpdate(double timestamp, Translation2d observation) {
		if (!isAlive()) {
			return false;
		}
		double distance = smoothedPosition.inverse().translateBy(observation).norm();
		Robot.logger.log("Attempting to update track, with distance: " + distance, 3);
		
		if (distance < MAX_DISTANCE) {
			observedPositions.put(timestamp, observation);
			pruneByTime();
			
			return true;
		} else {
			pruneByTime();
			
			return false;
		}
	}
	
	public boolean isAlive() {
		return (observedPositions.size() > 0);
	}
	
	// Removes all positions observed before a certain time, smooths remaining positions
	public void pruneByTime() {
		double deleteBefore = Timer.getFPGATimestamp() - MAX_AGE;
		for (Iterator<Map.Entry<Double, Translation2d>> it = observedPositions.entrySet().iterator(); it.hasNext();) {
			Map.Entry<Double, Translation2d> entry = it.next();
			if (entry.getKey() < deleteBefore) {
				it.remove();
			}
		}
		
		if (observedPositions.isEmpty()) {
			smoothedPosition = null;
		} else {
			smooth();
		}
	}
	
	// Smooths goal location by averaging last few locations
	void smooth() {
		if (isAlive()) {
			double x = 0;
			double y = 0;
			for (Map.Entry<Double, Translation2d> entry : observedPositions.entrySet()) {
				x += entry.getValue().getX();
				y += entry.getValue().getY();
			}
			x /= observedPositions.size();
			y /= observedPositions.size();
			
			smoothedPosition = new Translation2d(x, y);
		}
	}
	
	public Translation2d getSmoothedPosition() {
		return smoothedPosition;
	}
	
	public double getLatestTimestamp() {
		return observedPositions.keySet().stream().max(Double::compareTo).orElse(0.0);
	}
	
	// Returns a value from 0 to 1 giving percentage of the time that the goal was visible in past MAX_AGE seconds
	public double getStability() {
		return Math.min(1.0, observedPositions.size() / (FRAME_RATE * MAX_AGE));
	}
	
	public int getId() {
		return id;
	}
}
