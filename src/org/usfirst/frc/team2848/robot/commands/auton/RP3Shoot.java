package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.commands.drive.DriveFAST;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.GyroTurn;
import org.usfirst.frc.team2848.robot.commands.drive.GyroTurnLong;
import org.usfirst.frc.team2848.robot.commands.drive.NoOmnis;
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
public class RP3Shoot extends CommandGroup {

    public RP3Shoot() {
    	
    	//fast version
    	
//    	addSequential(new ShiftHigh());
//    	addSequential(new NoOmnis());
//    	addParallel(new ZeroAndVisionPos());
//    	addSequential(new testZeroGyro());
//    
//    	addSequential(new DriveToDistHigh(76.3)); //was 74.3
//    	//addParallel(new IntakePIDNonStop(Robot.intake.spitPos));
//    	addSequential (new GyroTurnHigh(-55));
//    	addSequential(new Wait(.1));
//    	addSequential(new ShiftLow());
//    	addSequential(new DriveToDistance(38)); //was 39
//    	addSequential(new VisionTurn());
//    	addSequential(new IntakePID(Robot.intake.spitPos));
//    	addSequential(new DriveToDistance(29));
//    	addParallel(new SpitGearBreakBeam());
//    	addSequential(new Wait(.1));
//    	addSequential(new DriveFAST(-40));
//    	addSequential(new GyroTurn(-148)); //was -156
//    	addParallel(new IntakePID(Robot.intake.tuckPos));
//    	addParallel(new ShootAuton());
//    	addSequential(new DriveFAST(80)); 
    	
    	
    			addSequential(new ShiftLow());
    	    	addSequential(new NoOmnis());
    	    	addSequential(new ZeroIntake());
    	    	addSequential(new TestZeroGyro());
    	    
    	    	addSequential(new DriveToDistance(74.3)); //was 74.3
    	    	addParallel(new IntakePIDNonStop(Robot.intake.visionPos));
    	    	//addParallel(new IntakePIDNonStop(Robot.intake.spitPos));
    	    	addSequential (new GyroTurn(-60));
    	    	addSequential(new DriveToDistance(39));
    	    	addSequential(new VisionTurn());
    	    	addSequential(new IntakePID(Robot.intake.spitPos));
    	    	addSequential(new DriveToDistance(29));
    	    	addParallel(new SpitGearBreakBeam());
    	   	
    	    	addSequential(new DriveFAST(-40));
    	    	addSequential(new GyroTurnLong(-148));
    	    	addParallel(new IntakePID(Robot.intake.tuckPos));
    	    	addParallel(new ShootAutonLong());
    	    	addSequential(new DriveFAST(84));
    }
}
