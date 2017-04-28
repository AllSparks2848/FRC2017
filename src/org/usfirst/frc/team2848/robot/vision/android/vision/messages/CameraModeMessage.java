package org.usfirst.frc.team2848.robot.vision.android.vision.messages;

public class CameraModeMessage extends VisionMessage {
	
	private static final String FRONT_FACING = "front";
	private static final String REAR_FACING = "rear";
	
	private String message = REAR_FACING;
	
	private CameraModeMessage(String message) {
		this.message = message;
	}
	
	public static CameraModeMessage getFrontFacingMessage() {
		return new CameraModeMessage(FRONT_FACING);
	}
	
	public static CameraModeMessage getRearFacingMessage() {
		return new CameraModeMessage(REAR_FACING);
	}

	@Override
	public String getType() {
		return "cameraMode";
	}

	@Override
	public String getMessage() {
		return message;
	}

}
