package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Conveyor extends Subsystem {

	public static Spark shooterElevator = new Spark(RobotMap.p_elevator);
//	public static double conveyWait = Robot.prefs.getDouble("conveyWait", .25);
//	public static double conveyRuntime = Robot.prefs.getDouble("conveyRuntime", .25);
//	public static Timer conveyTimer = new Timer();
//	public static Timer totalTimer = new Timer();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void elevator(double power){
    	shooterElevator.set(power);
    }
    public void stopElevator() {
    	shooterElevator.set(0);
    }
}
