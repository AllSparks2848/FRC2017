package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.GearTurn;
import org.usfirst.frc.team2848.robot.commands.drive.GyroTurn;
import org.usfirst.frc.team2848.robot.commands.drive.NoOmnis;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team2848.robot.commands.drive.TestZeroGyro;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeGear;
import org.usfirst.frc.team2848.robot.commands.intake.IntakePID;
import org.usfirst.frc.team2848.robot.commands.intake.SpitGearBreakBeam;
import org.usfirst.frc.team2848.robot.commands.intake.ZeroIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftTwoGear extends CommandGroup {

    public LeftTwoGear() {
    	addSequential(new ShiftLow());
    	addSequential(new NoOmnis());
    	addSequential(new ZeroIntake());
    	addParallel(new IntakePID(Robot.intake.spitPos));
    	addSequential(new Wait(.4));
       
        addSequential(new DriveStraight(75)); 
//        addSequential(new Wait(.2));
        addParallel(new SpitGearBreakBeam());
        
        addSequential(new DriveStraight(-51)); 
        addSequential(new TestZeroGyro());
        addParallel(new IntakePID(Robot.intake.intakePos));
        addSequential(new GyroTurn(-90));
        addSequential(new TestZeroGyro());
        addSequential(new DriveStraight(20));
        addSequential(new GearTurn());
        addParallel(new IntakePID(Robot.intake.intakePos));
//        odith
        
        addParallel(new IntakeGear());
//        addParallel(new ShootAuton(660));
        addSequential(new DriveToDistance(60)); 
        addSequential(new GyroTurn(0));
        addSequential(new LeftTwoGearSecondHalf());
    }
}
