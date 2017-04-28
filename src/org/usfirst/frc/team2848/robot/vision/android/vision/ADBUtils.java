package org.usfirst.frc.team2848.robot.vision.android.vision;

import org.spectrum3847.RIOdroid.RIOdroid;
import org.usfirst.frc.team2848.robot.Robot;



//Code from spectrum 3847 (Look up riodroid on github)
public class ADBUtils{
	
	public static String adbReverseForward(int remotePort, int localPort) {
		try {
			return RIOdroid.executeCommand("adb reverse tcp:" + remotePort + " tcp:" + localPort);
		} catch(Exception e) {
			return e.getMessage();	
		}
	}
	
	public static void restartApp() {
		Robot.logger.log("Restarting App", 3);
		
		RIOdroid.executeCommand("adb shell am force-stop org.usfirst.frc.team2848.steamworksvision \\; "
				+ "am start org.usfirst.frc.team2848.steamworksvision/org.usfirst.frc.team2848.steamworksvision.VisionTrackingTestActivity");
	}
}
