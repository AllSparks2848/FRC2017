package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeGear extends Command {

    public IntakeGear() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.intake(-1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Intake Current: " + Robot.intake.pdp.getCurrent(14));
    	if (Robot.intake.isBeamBroken()) {
    		System.out.println("Beam Broken");
    		return true;
    	}
    	else {
    		System.out.println("Beam Not Broken");
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intakeRoller.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
