package org.usfirst.frc.team2848.robot;

import org.usfirst.frc.team2848.robot.commands.auton.BangBangShoot;
import org.usfirst.frc.team2848.robot.commands.climber.Climb;
import org.usfirst.frc.team2848.robot.commands.drive.AllOmnis;
import org.usfirst.frc.team2848.robot.commands.drive.FollowPath;
import org.usfirst.frc.team2848.robot.commands.drive.NoOmnis;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeDown;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeGear;
import org.usfirst.frc.team2848.robot.commands.intake.IntakePID;
import org.usfirst.frc.team2848.robot.commands.intake.IntakePIDNonStop;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeUp;
import org.usfirst.frc.team2848.robot.commands.intake.SetBottomPos;
import org.usfirst.frc.team2848.robot.commands.intake.SpitGear;
import org.usfirst.frc.team2848.robot.commands.intake.ZeroIntake;
import org.usfirst.frc.team2848.robot.commands.shooter.Convey;
import org.usfirst.frc.team2848.robot.commands.shooter.ManualShoot;
import org.usfirst.frc.team2848.robot.commands.shooter.ShootAndConvey;
import org.usfirst.frc.team2848.robot.util.XboxTrigger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class OI {
	Joystick xbox1 = new Joystick(RobotMap.p_xbox1);
	Joystick xbox2 = new Joystick(RobotMap.p_xbox2);
//	Joystick buttonBox = new Joystick(RobotMap.p_buttonBox);
//	Joystick buttonBox2 = new Joystick(RobotMap.p_buttonBox2);
	Joystick buttonBoxL = new Joystick(RobotMap.p_buttonBoxL);
//	Joystick climbControl = new Joystick(5);
	
//	Controller 1
	Button a1 = new JoystickButton(xbox1, 1);
	Button b1 = new JoystickButton(xbox1, 2);
	Button x1 = new JoystickButton(xbox1, 3);
	Button y1 = new JoystickButton(xbox1, 4);
	
	XboxTrigger lTrig1 = new XboxTrigger(xbox1, 2);
	XboxTrigger rTrig1 = new XboxTrigger(xbox1, 3);
	Button lBump1 = new JoystickButton(xbox1, 5);
	Button rBump1 = new JoystickButton(xbox1, 6);
	
//	Controller 2
	Button a2 = new JoystickButton(xbox2, 1);
//	Button b2 = new JoystickButton(xbox2, 2);
//	Button x2 = new JoystickButton(xbox2, 3);
//	Button y2 = new JoystickButton(xbox2, 4);
//	
//	Button start = new JoystickButton(xbox2, 8);
//	
//	Button lBump2 = new JoystickButton(xbox2, 5);
//	Button rBump2 = new JoystickButton(xbox2, 6);
//	XboxTrigger lTrig2 = new XboxTrigger(xbox2, 2);
//	XboxTrigger rTrig2 = new XboxTrigger(xbox2, 3);
//	
//	Button clickRight2 = new JoystickButton(xbox2, 10);
//	
////	Button bOx
//	Button bb1 = new JoystickButton(buttonBox, 6);
//	Button bb2 = new JoystickButton(buttonBox, 4);
//	Button bb3 = new JoystickButton(buttonBox, 2);
//	Button bb4 = new JoystickButton(buttonBox, 5);
//	Button bb5 = new JoystickButton(buttonBox, 3);
//	Button bb6 = new JoystickButton(buttonBox, 1);
//	Button bb7 = new JoystickButton(buttonBox, 7);
//	Button bb8 = new JoystickButton(buttonBox, 9);
//	Button bb9 = new JoystickButton(buttonBox, 8);
//	
////	button Box 2
//	Button bb21 = new JoystickButton(buttonBox2, 3);
//	Button bb22 = new JoystickButton(buttonBox2, 12);
//	Button bb23 = new JoystickButton(buttonBox2, 15);
//	Button bb24 = new JoystickButton(buttonBox2, 6);
//	Button bb25 = new JoystickButton(buttonBox2, 9);
//	Button bb26 = new JoystickButton(buttonBox2, 2);
//	Button bb27 = new JoystickButton(buttonBox2, 13);
//	Button bb28 = new JoystickButton(buttonBox2, 16);
//	Button bb29 = new JoystickButton(buttonBox2, 5);
//	Button bb210 = new JoystickButton(buttonBox2, 10);
//	Button bb211 = new JoystickButton(buttonBox2, 4); 
//	Button bb212 = new JoystickButton(buttonBox2, 11);
//	Button bb213 = new JoystickButton(buttonBox2, 14);
//	Button bb214 = new JoystickButton(buttonBox2, 7);
//	Button bb215 = new JoystickButton(buttonBox2, 8);
	
	
	//button box L
	Button bbL1 = new JoystickButton(buttonBoxL, 16);
	Button bbL2 = new JoystickButton(buttonBoxL, 10);
	Button bbL3 = new JoystickButton(buttonBoxL, 15);
	Button bbL4 = new JoystickButton(buttonBoxL, 13);
	Button bbL5 = new JoystickButton(buttonBoxL, 14);
	Button bbL6 = new JoystickButton(buttonBoxL, 5);
	Button bbL7 = new JoystickButton(buttonBoxL, 11);
	Button bbL8 = new JoystickButton(buttonBoxL, 12);
	Button bbL9 = new JoystickButton(buttonBoxL, 3);
	Button bbL10 = new JoystickButton(buttonBoxL, 4);
	Button bbL11 = new JoystickButton(buttonBoxL, 8);
	Button bbL12 = new JoystickButton(buttonBoxL, 7);
	Button bbL13 = new JoystickButton(buttonBoxL, 9);
	
	
	public OI() {
		
		
		
		LiveWindow.addActuator("drivetrain", "Drivetrain", Robot.drivetrain.getPIDController());
		
//		bb21.whenPressed(new IntakeGear());
//		bb22.whileHeld(new SpitGear());
//		
//		bb24.whenPressed(new IntakePID(Robot.intake.bottomPos)); 
//		bb25.whenPressed(new IntakePID(Robot.intake.spitPos));
//		bb29.whenPressed(new IntakePID(Robot.intake.intakePos));
//		bb210.whenPressed(new IntakePID(Robot.intake.tuckPos));
//		bb28.whileHeld(new Convey(.9));
//		bb211.whileHeld(new Climb());
//		bb214.whileHeld(new IntakeUp());
//		bb215.whileHeld(new IntakeDown());
		
//		a1.whenPressed(new TeleOpGearAlign()); 
		x1.whileHeld(new SetBottomPos());
		y1.whileHeld(new ManualShoot(-.675, .7));
		b1.whenPressed(new ZeroIntake());
		
		rTrig1.whenPressed(new NoOmnis());
		rBump1.whenPressed(new AllOmnis()); 
		lBump1.whenPressed(new ShiftHigh());
		lTrig1.whenPressed(new ShiftLow());
		
//		bb8.whileHeld(new SpitGear());
//		bb6.whileHeld(new IntakeDown());
//		bb3.whileHeld(new IntakeUp());
//		bb9.whenPressed(new IntakeGear());
//		bb1.whenPressed(new IntakePID(Robot.intake.bottomPos)); 
//		bb2.whenPressed(new IntakePID(Robot.intake.intakePos));
//		bb4.whenPressed(new IntakePID(Robot.intake.spitPos));
//		bb5.whenPressed(new IntakePID(Robot.intake.tuckPos));
//		bb7.whileHeld(new ShootAndConvey()); 
		
		
		bbL1.whenPressed(new IntakePID(Robot.intake.intakePos));
		bbL2.whenPressed(new IntakePIDNonStop(Robot.intake.spitPos));
		bbL3.whenPressed(new IntakePID(Robot.intake.tuckPos));
		bbL4.whileHeld(new Climb(.5));
		bbL5.whileHeld(new IntakeUp());
		bbL6.whileHeld(new IntakeDown());
		bbL7.whenPressed(new ZeroIntake());
		bbL8.whileHeld(new Climb(1));
		bbL9.whileHeld(new BangBangShoot());
		bbL10.whileHeld(new Convey(.55)); //was .6
		bbL11.whileHeld(new ShootAndConvey());
		bbL12.whenPressed(new IntakeGear());
		bbL13.whileHeld(new SpitGear());
		
		
		
//		b2.whenPressed(new DriveStraight(40));
		a2.whenPressed(new FollowPath("TestPath", false, false,1,Robot.drivetrain.gyro.getYaw()));
////		lBump2.whenPressed(new RedPosition3());
//		x2.whenPressed(new DriveStraight(60));
//		y2.whenPressed(new DriveStraight(70));
//		rBump2.whenPressed(new MiddleAuton());
		
		
	}
//	public double getClimberJoystick(){ 
	public double getLeftJoystick() {
		return xbox1.getRawAxis(1);
	}
	
	public double getRightJoystick() {
		return xbox1.getRawAxis(4);
	}
}