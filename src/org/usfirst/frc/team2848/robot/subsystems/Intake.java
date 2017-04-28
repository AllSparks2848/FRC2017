package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;
import org.usfirst.frc.team2848.robot.commands.intake.SetLEDStatus;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Intake extends PIDSubsystem {
	public static double p = .025;
	public static double i = 0.002;
	public static double d = 0.0025;
	public int bottomPos = -93;
	public int intakePos = -88;
	public int spitPos = -16; //-28 on practice. subject to change on comp
	public int tuckPos = 0;
	public int visionPos = -50;
	public PowerDistributionPanel pdp = new PowerDistributionPanel();
	public Spark intakeRoller = new Spark(RobotMap.p_intakeRoller);
	public Spark intakePivot = new Spark(RobotMap.p_intakePivot);
	DigitalInput breakbeam = new DigitalInput(RobotMap.p_breakbeam);
	public static Encoder intakeEnc = new Encoder(RobotMap.p_intakeEncA, RobotMap.p_intakeEncB, false,
			EncodingType.k4X);
	
	public static DigitalOutput out1 = new DigitalOutput(RobotMap.p_out1);
//	public static DigitalOutput out2 = new DigitalOutput(RobotMap.p_out2);
	
	DigitalInput photogate = new DigitalInput(RobotMap.p_photogate);

	public Timer timer = new Timer();
	
	public boolean isIntakePos = false;

	public Intake() {
		super("Intake", p, i, d);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
		setDefaultCommand(new SetLEDStatus());
		
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return Intake.intakeEnc.get();
	}

	protected void usePIDOutput(double output) {
		intakePivot.set(-output);
	}

	public void intake(double speed) {
		intakeRoller.set(speed);
	}

	public void stopIntake() {
		intakeRoller.set(0);
	}

	public void stopActuate() {
		intakePivot.set(0);
	}

	public void actuate(double speed) {
		intakePivot.set(speed);
	}

	public boolean isBeamBroken() {
		return !breakbeam.get();
	}

	public double getPivot() {
		return intakePivot.get();
	}

	public double getBottomPos() {
		return bottomPos;
	}

	public void setBottomPos() {
		Intake.intakeEnc.reset();
	}

	public double getIntakePos() {
		return intakePos;
	}

	public double getSpitPos() {
		return spitPos;
	}

	public double getTuckPos() {
		return tuckPos;
	}
	public boolean isPhotoGateBroken(){
		return(!photogate.get());
	}
	public void setIsIntakePos(){
		if(Intake.intakeEnc.get()>-86)
			isIntakePos = false;
		else
			isIntakePos = true;
	}
}
