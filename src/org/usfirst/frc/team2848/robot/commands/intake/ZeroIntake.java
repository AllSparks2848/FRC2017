package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroIntake extends Command {

	boolean startedTooFarUp = false;
    public ZeroIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.intake.isPhotoGateBroken()){
    		Robot.intake.actuate(-.2);
    		startedTooFarUp = false;
    	}
    	else{
    		Robot.intake.actuate(.2);
    		startedTooFarUp = true;
    	}
    	
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(!startedTooFarUp && !Robot.intake.isPhotoGateBroken()){
    		Robot.intake.stopActuate();
    		Robot.intake.setBottomPos();
    		return true;
    	}
    	if(startedTooFarUp && Robot.intake.isPhotoGateBroken()) {
    		Robot.intake.stopActuate();
    		Robot.intake.setBottomPos();
    		return true;
    	}
    	Robot.intake.setBottomPos();
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
