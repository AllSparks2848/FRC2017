package org.usfirst.frc.team2848.robot.commands.shooter;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDShoot extends Command {
    public PIDShoot() {
    	requires(Robot.shooter);
    	Robot.shooter.setOutputRange(0.0, .9);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.setSetpoint(Shooter.targetRPM);
    	Robot.shooter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	SmartDashboard.putNumber("Shoot RPM", Shooter.shooterBackEnc.getRate());
    	SmartDashboard.putNumber("Shoot Power", Shooter.shooterBack.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.disable();
    	Robot.shooter.stopShoot();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
