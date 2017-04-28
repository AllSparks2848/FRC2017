package org.usfirst.frc.team2848.robot.commands.shooter;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Convey extends Command {

	private double power;
//	private double totalTime;
    public Convey(double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.conveyor);
    	this.power = power;
    }
//    public Convey(double power, double totalTime) {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(Robot.conveyor);
//    	this.power = power;
//    	this.totalTime = totalTime;
//    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.conveyor.elevator(power);
//    	Conveyor.conveyTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	if (Conveyor.conveyRuntime > Conveyor.conveyTimer.get()) {
//    		Conveyor.shooterElevator.stopMotor();
//    		Conveyor.conveyTimer.reset();
//    		Conveyor.conveyTimer.start();
//    		if (Conveyor.conveyWait > Conveyor.conveyTimer.get()) {
//        		Conveyor.shooterElevator.set(power);
//        		Conveyor.conveyTimer.reset();
//        		Conveyor.conveyTimer.start();
//        	}
//    	}     	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.conveyor.stopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
