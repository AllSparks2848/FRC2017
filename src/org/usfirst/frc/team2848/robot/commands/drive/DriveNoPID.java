package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveNoPID extends Command {
	private double setpoint;
    public DriveNoPID(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.setpoint=setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.leftEncoder.reset();
    	Robot.drivetrain.rightEncoder.reset();
    	Robot.drivetrain.leftEncoder.setDistancePerPulse(.0493);
    	Robot.drivetrain.rightEncoder.setDistancePerPulse(.0488);
    	
    	Robot.drivetrain.leftDrive1.set(.8);
    	Robot.drivetrain.leftDrive2.set(.8);
    	Robot.drivetrain.leftDrive3.set(.8);
    	Robot.drivetrain.rightDrive1.set(-.8);
    	Robot.drivetrain.rightDrive2.set(-.8);
    	Robot.drivetrain.rightDrive3.set(-.8);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Motor Power: " + Robot.drivetrain.leftDrive1.get());
        return (Robot.drivetrain.leftEncoder.getDistance() > setpoint);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.leftDrive1.set(0);
    	Robot.drivetrain.leftDrive2.set(0);
    	Robot.drivetrain.leftDrive3.set(0);
    	Robot.drivetrain.rightDrive1.set(0);
    	Robot.drivetrain.rightDrive2.set(0);
    	Robot.drivetrain.rightDrive3.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
