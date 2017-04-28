package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BangBangShoot extends Command {

    public BangBangShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(-Shooter.shooterFrontEnc.getRate()<Shooter.targetRPM) { //was *1.2
    		Shooter.shooterFront.set(-.63);
    		Shooter.shooterBack.set(.63);
    	}
    	else {
    		Shooter.shooterFront.set(0);
    		Shooter.shooterBack.set(0);
    	}
    	
//    	if(Math.abs(Math.abs(Shooter.shooterFrontEnc.getRate())-Shooter.targetRPM) < 20)
//    		Robot.conveyor.elevator(.6);
//    	else
//    		Robot.conveyor.elevator(0);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Shooter.shooterBack.set(0);
    	Shooter.shooterFront.set(0);
//    	Robot.conveyor.elevator(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
