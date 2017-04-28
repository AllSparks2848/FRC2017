package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistHigh;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.GyroTurnHigh;
import org.usfirst.frc.team2848.robot.commands.drive.NoOmnis;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team2848.robot.commands.drive.VisionTurn;
import org.usfirst.frc.team2848.robot.commands.intake.IntakePID;
import org.usfirst.frc.team2848.robot.commands.intake.SpitGearBreakBeam;
import org.usfirst.frc.team2848.robot.commands.intake.ZeroIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedMidShot extends CommandGroup {

    public RedMidShot() {
    	//"Ian's idea"
    	addSequential(new ShiftLow());
    	addSequential(new NoOmnis());
    	addSequential(new ZeroIntake());
    	addParallel(new IntakePID(Robot.intake.visionPos));
        addSequential(new DriveToDistance(45));
        addSequential(new VisionTurn());
       
       
        //addSequential(new IntakePID(Robot.intake.spitPos));
        System.out.println(Robot.distance);
        addSequential(new IntakePID(Robot.intake.spitPos));
         addSequential(new DriveToDistance(28));
         
        addParallel(new SpitGearBreakBeam());
        addSequential(new ShiftHigh());
        addSequential(new Wait(.25));
        addSequential(new DriveToDistHigh(-38)); //was -44.1
        addSequential(new GyroTurnHigh(102.3));
        addParallel(new IntakePID(Robot.intake.tuckPos));
    	addParallel(new ShootAutonLong());
        addSequential(new DriveToDistHigh(100)); //was 114

    }
}
