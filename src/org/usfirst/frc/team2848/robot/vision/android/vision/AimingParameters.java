package org.usfirst.frc.team2848.robot.vision.android.vision;

import org.usfirst.frc.team2848.robot.util.Rotation2d;

public class AimingParameters {
    protected double distance;
    protected Rotation2d angle;
    protected boolean isValid;
    
    public AimingParameters(Rotation2d angle, double distance, boolean isValid) {
        this.angle = angle;
        this.distance = distance;
        this.isValid = isValid;
    }
    
    public Rotation2d getAngle() {
        return angle;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public boolean isValid() {
        return isValid;
    }

}