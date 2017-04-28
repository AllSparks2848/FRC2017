package org.usfirst.frc.team2848.robot.util;

public class VisionTable {
	double[] visionArr;
	public VisionTable() {
//		visionArr = new double[]{-13.6,-11.4,-9.7,-6.75,-4.95,-1.85,0,2.64,3.5,6.3,8.6,12,13.25};
	}
	
	

	public int getIndex(double robotX) {
		int index = 0;
		double rawIndex;
		rawIndex = 20*robotX +6;
		index = (int) Math.round(rawIndex);
		return index;
	}
	
	public double getAngleToTurn(int index) {
		return visionArr[index];
	}
}


