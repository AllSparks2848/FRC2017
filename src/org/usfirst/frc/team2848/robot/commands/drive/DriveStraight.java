package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private double setpoint;
	double time;
	double rightOutput = 0;
	double leftOutput = 0;
	double driveOutput = 0;
	double gyroOutput = 0;
	
	double driveP = .15;
	double gyroP = .05;
	Timer timer = new Timer();
    public DriveStraight(double setpoint) {
    	requires(Robot.drivetrain);
		this.setpoint = setpoint;
		if(Math.abs(setpoint)<20)
			time = .4;
		else if(Math.abs(setpoint)<40)
			time = .7; //2.5
		else if(Math.abs(setpoint)<60)
			time = 1.25;
		else
			time = 1.75;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.leftEncoder.reset();
    	Robot.drivetrain.rightEncoder.reset();
    	
    	Robot.drivetrain.leftEncoder.setDistancePerPulse(.0493);
    	Robot.drivetrain.rightEncoder.setDistancePerPulse(.0488);
    	
    	Robot.drivetrain.gyro.reset(); 
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveOutput = driveP*(setpoint - Robot.drivetrain.leftEncoder.getDistance()); //COMP USE BOTH
    	gyroOutput = gyroP*(0 - Robot.drivetrain.gyro.getYaw());
    	
    	if(driveOutput>.8)
    		driveOutput = .8;
    	if(Math.abs(gyroOutput) >.2) {
    		if(gyroOutput > 0)
    			gyroOutput = .2;
    		else
    			gyroOutput = -.2;	
    	}
    	leftOutput = driveOutput + gyroOutput;
    	rightOutput = -(driveOutput - gyroOutput);
    	Robot.drivetrain.leftDrive1.set(leftOutput);
    	Robot.drivetrain.leftDrive2.set(leftOutput);
    	Robot.drivetrain.leftDrive3.set(leftOutput);
    	
    	Robot.drivetrain.rightDrive1.set(rightOutput);
    	Robot.drivetrain.rightDrive2.set(rightOutput);
    	Robot.drivetrain.rightDrive3.set(rightOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Error: " + Robot.drivetrain.getPIDController().getError());
    	System.out.println("PID Out: " + Robot.drivetrain.getPIDController().get());
    	
    	if(timer.get() > time)
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setPowerZero();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
