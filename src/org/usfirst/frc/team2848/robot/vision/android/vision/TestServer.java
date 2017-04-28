package org.usfirst.frc.team2848.robot.vision.android.vision;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

import org.spectrum3847.RIOdroid.RIOadb;

public class TestServer {
	private static int port = 1848;
	private ServerSocket socket;
	public TestServer() {
		RIOadb.init();      //Start up ADB deamon and get an instance of jadb
		System.out.println(RIOadb.getDevicesList());
		int numOfDevice = RIOadb.getDevicesList().size();
		if(numOfDevice > 0) {
			System.out.println("Going ahead with reverse port forwarding...");
		    ADBUtils.adbReverseForward(port, port); //Forward our port to the phones port
		    System.out.println("Ran reverse port forward command, going ahead with socket...");
			try {
				socket = new ServerSocket(port);
			} catch (IOException e) {
				System.out.println("Error creating socket, possibly port already in use...");
				e.printStackTrace();
			}
			if(socket != null) {
				
				System.out.println("Socket is instantiated, moving to accept connections...");
				
				try {
					socket.setReuseAddress(true);
				} catch (SocketException e) {
					System.out.println("Failure in allowing socket to be reused...");
					e.printStackTrace();
				}
				
				try {
					socket.accept();
				} catch (IOException e) {
					System.out.println("Failure in socket accepting connections...");
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("No android devices found...");
		}
		
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Failed to close socket...");
				e.printStackTrace();
			}
		}
		
		System.out.println("End of server test.");
	}
}
