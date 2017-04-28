package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;
import org.usfirst.frc.team2848.robot.commands.auton.BluePosition1;
import org.usfirst.frc.team2848.robot.commands.auton.BluePosition3;
import org.usfirst.frc.team2848.robot.commands.auton.MiddleAuton;
import org.usfirst.frc.team2848.robot.commands.auton.RedPosition1;
import org.usfirst.frc.team2848.robot.commands.auton.RedPosition3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutonSelector extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    DigitalInput autoSelect1 = new DigitalInput(RobotMap.p_autoSelect1);
    DigitalInput autoSelect2 = new DigitalInput(RobotMap.p_autoSelect2);
    DigitalInput autoSelect3 = new DigitalInput(RobotMap.p_autoSelect3);
    DigitalInput autoSelect4 = new DigitalInput(RobotMap.p_autoSelect4);
    
    public int getAutoNum() {
    	if(autoSelect1.get()){ //true
    		if(autoSelect2.get()) { //true-true
    			if(autoSelect3.get()) { //true-true-true
    				return 7;
    			}
    			else { //true-true-false
    				return 6;
    			}
    		}
    		else { //true-false
    			if(autoSelect3.get()) { //true-false-true
    				return 3;
    			}
    			else { //true-false-false
    				return 2;
    			}
    		}
    	}
    	else { //false
    		if(autoSelect2.get()) { //false-true
    			if(autoSelect3.get()) { //false-true-true
    				return 5;
    			}
    			else { //false-true-false
    				return 4;
    			}
    		}
    		else { //false-false
    			if(autoSelect3.get()) { //false-false-true
    				if(autoSelect4.get()) { //false-false-true-true
    					return 9;
    				}
    				else { //false-false-true-false
    					return 1;
    				}
    			}
    			else { //false-false-false
    				if(autoSelect4.get()) {
    					return 8;
    				}
    				else {
    					return 0;
    				}
    			}
    		}
    	}
    }
    public int autoNum = getAutoNum();
    public Command getAutoCommand() {
    	Command autonomousCommand = new MiddleAuton();
        switch(autoNum) {
        case 0: autonomousCommand = new RedPosition1();
        	break;
        case 1: autonomousCommand = new MiddleAuton();
    	break;
        case 2: autonomousCommand = new RedPosition3();
    	break;
        case 3: autonomousCommand = new BluePosition1();
    	break;
        case 4: autonomousCommand = new MiddleAuton();
    	break;
        case 5: autonomousCommand = new BluePosition3();
    	break;
        case 6: autonomousCommand = new RedPosition1();
    	break;
        case 7: autonomousCommand = new RedPosition1();
    	break;
        case 8: autonomousCommand = new RedPosition1();
    	break;
        case 9: autonomousCommand = new RedPosition1();
    	break;
        }
        System.out.println(autonomousCommand.getName());
        return autonomousCommand;
    }
    
    		
}
