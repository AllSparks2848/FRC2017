package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionTurn extends Command {
Timer timer = new Timer();
double angle = 0.0;
    public VisionTurn() {
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	angle = (45.257*Robot.x - .15077);
    	if(angle==-.15077) {
    		angle = 0.0;
    	}
    	SmartDashboard.putNumber("Angle", angle);
    	Robot.drivetrain.gyro.zeroYaw();
    	Robot.drivetrain.gyroController.setOutputRange(-.5, .5);
    	Robot.drivetrain.gyroController.setSetpoint(angle);
    	Robot.drivetrain.gyroController.enable();
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.leftDrive1.pidWrite(Robot.drivetrain.gyroController.get()); 
    	Robot.drivetrain.leftDrive2.pidWrite(Robot.drivetrain.gyroController.get());
    	Robot.drivetrain.leftDrive3.pidWrite(Robot.drivetrain.gyroController.get());
    	
    	Robot.drivetrain.rightDrive1.pidWrite(Robot.drivetrain.gyroController.get());
    	Robot.drivetrain.rightDrive2.pidWrite(Robot.drivetrain.gyroController.get());
    	Robot.drivetrain.rightDrive3.pidWrite(Robot.drivetrain.gyroController.get());
		System.out.println("Running Vision Turn");
		System.out.println("Setpoint: " + angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(angle-Robot.drivetrain.gyro.getYaw()) < 1)||timer.get()>1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.gyroController.disable();
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
