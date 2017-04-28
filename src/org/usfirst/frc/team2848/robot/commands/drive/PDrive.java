package org.usfirst.frc.team2848.robot.commands.drive;
import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PDrive extends Command {

	double kP = .05;
	double error;
	private double setpoint;
	double motorPower;
    public PDrive(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.leftEncoder.reset();
    	Robot.drivetrain.rightEncoder.reset();
    	
    	Robot.drivetrain.leftEncoder.setDistancePerPulse(.0493);
    	Robot.drivetrain.rightEncoder.setDistancePerPulse(.0488);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = setpoint - Robot.drivetrain.leftEncoder.getDistance();
    	motorPower = error*kP;
    	Robot.drivetrain.leftDrive1.set(motorPower);
    	Robot.drivetrain.leftDrive2.set(motorPower);
    	Robot.drivetrain.leftDrive3.set(motorPower);
    	
    	Robot.drivetrain.rightDrive1.set(-motorPower);
    	Robot.drivetrain.rightDrive2.set(-motorPower);
    	Robot.drivetrain.rightDrive3.set(-motorPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(error-setpoint) < 2)
    		return true;
    	return false;
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
