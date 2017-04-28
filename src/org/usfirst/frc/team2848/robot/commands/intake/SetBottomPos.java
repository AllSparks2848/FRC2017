package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetBottomPos extends Command {

    public SetBottomPos() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.setBottomPos();
    	SmartDashboard.putNumber("Potentiometer Position", Intake.intakeEnc.get());
    	SmartDashboard.putNumber("Bottom Position", Robot.intake.bottomPos);
    	SmartDashboard.putNumber("Intake Position", Robot.intake.intakePos);
    	SmartDashboard.putNumber("Spit Position", Robot.intake.spitPos);
    	SmartDashboard.putNumber("Tuck Position", Robot.intake.tuckPos);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
