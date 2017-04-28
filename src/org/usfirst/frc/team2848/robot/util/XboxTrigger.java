package org.usfirst.frc.team2848.robot.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Treats left and right triggers as buttons. Utilizes same methods.
 * @param joystick     The GenericHID object that has the button (e.g. Joystick, KinectStick, etc)
 * 
 * @param axis ID The axis number of the trigger
 */
public class XboxTrigger extends Button {
	private final GenericHID m_joystick;
	private final int m_axisID;
	
	/**
	 * Treats left and right triggers as buttons. Utilizes same methods.
	 * @param joystick     The GenericHID object that has the button (e.g. Joystick, KinectStick, etc)
	 * 
	 * @param axis ID The axis number of the trigger
	 */
	public XboxTrigger(GenericHID joystick, int axisID) {
	    m_joystick = joystick;
	    m_axisID = axisID;
	  }
	
	/**
	   * Gets the raw value of the trigger.
	   *
	   * @return The raw value of the trigger
	   */
	  public boolean get() {
		  if(m_joystick.getRawAxis(m_axisID)>0.1)
			  return true;
		  return false;
	  }
}