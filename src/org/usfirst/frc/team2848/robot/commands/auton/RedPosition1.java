package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.commands.drive.AllOmnis;
import org.usfirst.frc.team2848.robot.commands.drive.BackAway;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistHigh;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.GyroTurn;
import org.usfirst.frc.team2848.robot.commands.drive.NoOmnis;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team2848.robot.commands.drive.TestZeroGyro;
import org.usfirst.frc.team2848.robot.commands.drive.VisionTurn;
import org.usfirst.frc.team2848.robot.commands.intake.IntakePID;
import org.usfirst.frc.team2848.robot.commands.intake.SpitGearBreakBeam;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedPosition1 extends CommandGroup {

    public RedPosition1() {
    	
    	// NON VISION
    	/*addSequential(new ShiftLow());
    	addSequential(new NoOmnis());
    	addSequential(new ZeroIntake());
    	addSequential(new testZeroGyro());
    
    	addSequential(new DriveToDistance(70.3)); 
    	addSequential(new Wait(.75));
    	addParallel(new IntakePIDNonStop(Robot.intake.spitPos));
    	addSequential (new GyroTurn(60));
    	addSequential(new Wait(.75));
    	addSequential(new DriveToDistance(62));
    	
    	addSequential(new Wait(.75)); */
    	//NON VISION
    
    	addSequential(new ShiftLow());
    	addSequential(new NoOmnis());
    	addParallel(new ZeroAndVisionPos());
    	addSequential(new TestZeroGyro());
    
    	addSequential(new DriveToDistance(74.3)); 
    	
    	//addParallel(new IntakePIDNonStop(Robot.intake.spitPos));
    	addSequential (new GyroTurn(60));
    	addSequential(new DriveToDistance(30)); //was 30
    	addSequential(new VisionTurn());
    	addSequential(new IntakePID(Robot.intake.spitPos));
    	addSequential(new DriveToDistance(34)); //was 34
    	addParallel(new SpitGearBreakBeam());
    	addSequential(new BackAway());

    	addSequential(new TestZeroGyro());
    	addSequential(new GyroTurn(-60));
    	addSequential(new ShiftHigh());
    	addSequential(new AllOmnis());
    	addSequential(new DriveToDistHigh(125));    	
    }
}
