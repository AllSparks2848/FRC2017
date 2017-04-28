package org.usfirst.frc.team2848.robot.commands.shooter;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ConveyBurst extends Command {

	private double amplitude;
	private double power;
	Timer timer = new Timer();
    public ConveyBurst(double amplitude) {
    	requires(Robot.conveyor);
    	this.amplitude = amplitude;
    }
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }
    protected void execute() {
//    	power = amplitude*Math.sin(Math.PI*timer.get()/4);
//    	if(power>1)
//    		power = 1;
//    	if(power<0)
//    		power = 0;
    	if(Math.abs(Math.abs(Shooter.shooterFrontEnc.getRate())-Shooter.targetRPM) < 20)
    		Robot.conveyor.elevator(power);
    	else
    		Robot.conveyor.elevator(0);
//    	Robot.conveyor.elevator(power);
//    	Timer.delay(.3);
//    	Robot.conveyor.elevator(0);
//    	Timer.delay(.2);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.conveyor.stopElevator();
    }

    protected void interrupted() {
    	end();
    }
}
