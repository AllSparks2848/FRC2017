package org.usfirst.frc.team2848.robot.commands.shooter;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualShoot extends Command {

	private double powerFront = 0;
	private double powerBack = 0;
	private double time = 0;
    public ManualShoot(double powerInner, double powerOuter) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	this.powerFront = powerInner;
    	this.powerBack = powerOuter;
    	//this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.shoot(powerFront, powerBack);
    	Robot.shooter.shootTime.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Front Motor Speed: " + Shooter.shooterFrontEnc.getRate());
//    	System.out.println("Back Motor Speed: " + Shooter.shooterBackEnc.getRate());
    	System.out.println("\n");
//        return Robot.shooter.shootTime.get() > time;
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stopShoot();
    	Robot.shooter.shootTime.stop();
    	Robot.shooter.shootTime.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
