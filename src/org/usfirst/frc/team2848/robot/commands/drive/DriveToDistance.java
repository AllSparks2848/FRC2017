package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistance extends Command {
	private double setpoint;
	private double time;
	Timer timer = new Timer();
	
	public DriveToDistance(double setpoint) {
		requires(Robot.drivetrain);
		this.setpoint = setpoint;
		if(setpoint > 40) {
			time = 2.5;
		}
		else if(setpoint >70)
			time = 3;
		else {
			time = 1;
		}
	}

    protected void initialize() {
    	Robot.drivetrain.leftEncoder.reset();
    	Robot.drivetrain.rightEncoder.reset();
    	
    	Robot.drivetrain.leftEncoder.setDistancePerPulse(.0493);
    	Robot.drivetrain.rightEncoder.setDistancePerPulse(.0488);
    	
    	Robot.drivetrain.setOutputRange(-.6, .6); //was .6
    	Robot.drivetrain.setSetpoint(setpoint);
    	
    	Robot.drivetrain.enable();
    	timer.start();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
    	System.out.println("Error: " + Robot.drivetrain.getPIDController().getError());
    	System.out.println("PID Out: " + Robot.drivetrain.getPIDController().get());
    	
    	if(timer.get() > time)
    		return true;
    	return false;
        //return (Math.abs(Robot.drivetrain.getPosition()-setpoint) < .5);
    }

    protected void end() {
    	Robot.drivetrain.disable();
//    	Robot.drivetrain.leftDrive1.set(0);
//    	Robot.drivetrain.leftDrive2.set(0);
//    	Robot.drivetrain.leftDrive3.set(0);
//    	Robot.drivetrain.rightDrive1.set(0);
//    	Robot.drivetrain.rightDrive2.set(0);
//    	Robot.drivetrain.rightDrive3.set(0);
    }

    protected void interrupted() {
    	end();
    }
}