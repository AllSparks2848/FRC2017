package org.usfirst.frc.team2848.robot.vision.android.vision.messages;

/**
 * Message used to ensure communication is maintained between phone and robot
 *
 */
public class HeartbeatMessage extends VisionMessage {
	
	static HeartbeatMessage sInst = null;

    public static HeartbeatMessage getInstance() {
        if (sInst == null) {
            sInst = new HeartbeatMessage();
        }
        return sInst;
    }

    @Override
    public String getType() {
        return "heartbeat";
    }

    @Override
    public String getMessage() {
        return "{}";
    }

}
