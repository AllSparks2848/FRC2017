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
import org.usfirst.frc.team2848.robot.commands.intake.IntakePIDNonStop;
import org.usfirst.frc.team2848.robot.commands.intake.SpitGearBreakBeam;
import org.usfirst.frc.team2848.robot.commands.intake.ZeroIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BluePosition3 extends CommandGroup {

    public BluePosition3() {
    	addSequential(new ShiftLow());
    	addSequential(new NoOmnis());
    	addSequential(new ZeroIntake());
    	addSequential(new TestZeroGyro());
    
    	addSequential(new DriveToDistance(72)); //initially 70.3
    	addParallel(new IntakePIDNonStop(Robot.intake.visionPos));
    	//addParallel(new IntakePIDNonStop(Robot.intake.spitPos));
    	addSequential (new GyroTurn(-60));
    	addSequential(new DriveToDistance(38)); //initially 30
    	addSequential(new VisionTurn());
    	addParallel(new IntakePID(Robot.intake.spitPos));
    	addSequential(new DriveToDistance(38));
    	addParallel(new SpitGearBreakBeam());
    	addSequential(new BackAway());
    	
    	addSequential(new TestZeroGyro());
    	addSequential(new GyroTurn(60));
    	addSequential(new ShiftHigh());
    	addSequential(new AllOmnis());
    	addSequential(new DriveToDistHigh(125));    
    }
}