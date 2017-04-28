package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistHigh;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.GyroTurn;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team2848.robot.commands.drive.VisionTurn;
import org.usfirst.frc.team2848.robot.commands.intake.IntakePID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftTwoGearSecondHalf extends CommandGroup {

    public LeftTwoGearSecondHalf() {
//    	if(Robot.isGear) {
    	addSequential(new ShiftHigh());
		
        addSequential(new DriveToDistHigh(-86));
        addSequential(new ShiftLow());
        addParallel(new IntakePID(Robot.intake.visionPos));
        addSequential(new GyroTurn(90));
        addSequential(new DriveToDistance(35));
        
        addSequential(new VisionTurn());
        addParallel(new IntakePID(Robot.intake.spitPos));
        addSequential(new DriveToDistance(40));
//        addParallel(new SpitGearBreakBeam());
//        addParallel(new BackAway());
//    	}
    }
}