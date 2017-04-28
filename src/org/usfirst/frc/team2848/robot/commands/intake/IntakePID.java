package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakePID extends Command {
	private double setpoint;
	
    public IntakePID(double setpoint) {
    	this.setpoint = setpoint;
        requires(Robot.intake);
        Robot.intake.setOutputRange(-1, 1);
    }

    protected void initialize() {
//    	if(!Robot.intake.isPhotoGateBroken()) {
//        	if(Intake.intakeEnc.get()>-25) {
//        		Robot.intake.intakePivot.set(.2);
//        		if(Robot.intake.isPhotoGateBroken())
//        		{
//        			Robot.intake.intakePivot.stopMotor();
//        		}
//        	}
//        		if(Intake.intakeEnc.get()<-50) {
//            		Robot.intake.disable();
//            		Robot.intake.intakePivot.set(-.2);
//            		if(Robot.intake.isPhotoGateBroken())
//            		{
//            			Robot.intake.intakePivot.stopMotor();
//            		}
//        		}
//        	}
    	Robot.intake.setSetpoint(setpoint);
    	Robot.intake.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Intake.out1.set(!Robot.intake.isBeamBroken());
//    	Intake.out2.set(!Robot.intake.isIntakePos);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Encoder Reading: " + Intake.intakeEnc.get());
    	System.out.println("Motor Power: " + Robot.intake.getPivot());
    	
        if(Math.abs((setpoint - Intake.intakeEnc.get())) <= 1) {
        	return true;
        }
        
//        if(!Robot.intake.isPhotoGateBroken()) {
//        	if(Intake.intakeEnc.get()>-25) {
//        		Robot.intake.disable();
//        		Robot.intake.intakePivot.set(.2);
//        		if(Robot.intake.isPhotoGateBroken())
//        		{
//        			return true;
//        		}
//        	}
//        		if(Intake.intakeEnc.get()<-50) {
//            		Robot.intake.disable();
//            		Robot.intake.intakePivot.set(-.2);
//            		if(Robot.intake.isPhotoGateBroken())
//            		{
//            			return true;
//            		}
//        		}
//        	}
        
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}