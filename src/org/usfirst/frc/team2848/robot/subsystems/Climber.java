package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    Spark climbR = new Spark(RobotMap.p_climbR);
    Spark climbL = new Spark(RobotMap.p_climbL);
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void climb(double speed) {
    	climbR.set(speed);
    	climbL.set(-speed);
    }
    
    public void stopClimbing() {
    	climbR.set(0);
    	climbL.set(0);
    }
}

