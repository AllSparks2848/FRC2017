package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.commands.drive.AllOmnis;
import org.usfirst.frc.team2848.robot.commands.drive.BackAway;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistHigh;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.GyroTurn;
import org.usfirst.frc.team2848.robot.commands.drive.GyroTurnHigh;
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
public class BlueShootFirst extends CommandGroup {

    public BlueShootFirst() {
    	
    addSequential(new ShiftLow());
	addSequential(new NoOmnis());
	addParallel(new ZeroIntake());
	addSequential(new TestZeroGyro());
	
	addSequential(new ShootAuton(510));
	addSequential(new DriveToDistance(-25)); //was -43.3
	addSequential(new Wait(.1));
	
	addSequential(new ShiftHigh());
	addParallel(new IntakePID(Robot.intake.visionPos));
	addSequential(new GyroTurnHigh(105)); //was -112.6
	addSequential(new Wait(.05));
	addSequential(new DriveToDistHigh(68)); //was 62
	addSequential(new TestZeroGyro());
	addSequential(new GyroTurnHigh(60));
	addSequential(new ShiftLow());
	addSequential(new DriveToDistance(20));
	addSequential(new VisionTurn());
	addParallel(new IntakePIDNonStop(Robot.intake.spitPos));
	addSequential(new DriveToDistance(36)); //was 30
	addParallel(new SpitGearBreakBeam());
	addSequential(new BackAway());
	
	addSequential(new TestZeroGyro());
	addSequential(new GyroTurn(-60));
	addSequential(new ShiftHigh());
	addSequential(new AllOmnis());
	addSequential(new DriveToDistHigh(125));    
    }
}
