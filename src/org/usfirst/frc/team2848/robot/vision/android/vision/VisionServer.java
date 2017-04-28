package org.usfirst.frc.team2848.robot.vision.android.vision;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;

import org.spectrum3847.RIOdroid.RIOadb;
import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.vision.android.vision.messages.CameraModeMessage;
import org.usfirst.frc.team2848.robot.vision.android.vision.messages.HeartbeatMessage;
import org.usfirst.frc.team2848.robot.vision.android.vision.messages.OffWireMessage;
import org.usfirst.frc.team2848.robot.vision.android.vision.messages.TargetTypeMessage;
import org.usfirst.frc.team2848.robot.vision.android.vision.messages.VisionMessage;

import edu.wpi.first.wpilibj.Timer;



/**
 * A server to run on the robot and maintain the link with an Android phone used for vision of targets
 *
 */
public class VisionServer implements Runnable {

    private static VisionServer instance = null;
    private ServerSocket serverSocket;
    double lastMessageReceivedTime = 0;
    private boolean running = true;
    private volatile boolean wantsAppRestart = false;
    private Socket p;

    private ArrayList<ServerThread> serverThreads = new ArrayList<>();
    private ArrayList<VisionUpdateReceiver> receivers = new ArrayList<>();

    private static final int port = 1848;

    public static VisionServer getInstance() {
        if (instance == null) {
            instance = new VisionServer();
        }
        return instance;
    }

    private boolean connected = false;

    public boolean isConnected() {
        return connected;
    }

    /**
     * Thread created to handle messages sent back and forth with the phone, 
     * responding to {@link HeartbeatMessage}s, allowing {@link VisionMessage}s to be sent,
     * and sending received {@link VisionUpdate}s out to {@link VisionUpdateReceiver}s registered on the {@link VisionServer}
     *
     */
    protected class ServerThread implements Runnable {
        private Socket socket;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        /**
         * Taking a message and writing it to the OutputStream of the socket connected to the phone
         * 
         * @param message - The message to be sent
         */
        public void send(VisionMessage message) {
            String toSend = message.toJson() + "\n";
            if (socket != null && socket.isConnected()) {
                try {
                    OutputStream os = socket.getOutputStream();
                    os.write(toSend.getBytes());
                } catch (IOException e) {
                    System.err.println("VisionServer: Could not send data to socket");
                }
            }
        }

        /**
         * Takes a {@link VisionMessage} received from the phone, sending it to {@link VisionUpdateReceiver}s if it is a {@link VisionUpdate}
         * and responding if it is a {@link HeartbeatMessage}
         * 
         * @param message - The VisionMessage received
         * @param timestamp - The time at which the message was received
         */
        public void handleMessage(VisionMessage message, double timestamp) {
            if ("targets".equals(message.getType())) {
                VisionUpdate update = VisionUpdate.generateFromJsonString(timestamp, message.getMessage());
                receivers.removeAll(Collections.singleton(null));
                if (update.isValid()) {
                    for (VisionUpdateReceiver receiver : receivers) {
                        receiver.gotUpdate(update);
                    }
                }
            }
            if ("heartbeat".equals(message.getType())) {
                send(HeartbeatMessage.getInstance());
            }
        }

        /**
         * A method to check whether a {@link ServerThread} is still running and connected
         * 
         * @return - true if still connected and running, false otherwise
         */
        public boolean isAlive() {
            return socket != null && socket.isConnected() && !socket.isClosed();
        }

        @Override
        public void run() {
            // Once thread used, do not run entire loop
            if (socket == null) {
                return;
            }
            try {
                InputStream is = socket.getInputStream();
                byte[] buffer = new byte[2048];
                int read;
                // Continue while connected and have messages to read
                while (socket.isConnected() && (read = is.read(buffer)) != -1) {
                    double timestamp = getTimestamp();
                    lastMessageReceivedTime = timestamp;
                    String messageRaw = new String(buffer, 0, read);

                    // Process messages split by newlines
                    String[] messages = messageRaw.split("\n");
                    for (String message : messages) {
                        OffWireMessage parsedMessage = new OffWireMessage(message);
                        if (parsedMessage.isValid()) {
                            handleMessage(parsedMessage, timestamp);
                        }
                    }
                }
                Robot.logger.log("Socket disconnected", 3);
                p = null;
            } catch (IOException e) {
                Robot.logger.log("Could not talk to socket", 3);
                socket = null;
                p = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private VisionServer() {
        Robot.logger.log("VisionServer initializing", 3);
        try {
            // Creating a socket and setting up connection over adb to start tcp
            serverSocket = new ServerSocket(port);
            RIOadb.init();
            //ADBUtils.restartApp();
            ADBUtils.adbReverseForward(port, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
        new Thread(new AppMaintainanceThread()).start();
    }

   /**
    * If a {@link VisionUpdateReceiver} is not in the list, add it to the list to start sending {@link VisionUpdate}s
    * 
    * @param receiver - The VisionUpdateReceiver to add to the list
    */
    public void addVisionUpdateReceiver(VisionUpdateReceiver receiver) {
        if (!receivers.contains(receiver)) {
            receivers.add(receiver);
        }
    }
    
	/**
	 * If a {@link VisionUpdateReceiver} is in the list, remove it from the list to stop sending {@link VisionUpdate}s
	 * 
	 * @param receiver - The VisionUpdateReceiver to remove from the list
	 */
    public void removeVisionUpdateReceiver(VisionUpdateReceiver receiver) {
        if (receivers.contains(receiver)) {
            receivers.remove(receiver);
        }
    }
    
    /**
     * Takes a message and sends it to the most recently created ServerThread, which should have a socket set up
     * 
     * @param message - The message to be sent to the phone
     * @see ServerThread
     */
    private void sendMessageMostRecentThread(VisionMessage message) {
    	ServerThread mostRecentThread = serverThreads.get(serverThreads.size() - 1);
    	
    	mostRecentThread.send(message);
    }
    
    public void setCameraFront() {
    	CameraModeMessage frontMessage = CameraModeMessage.getFrontFacingMessage();
    	sendMessageMostRecentThread(frontMessage);
    }
    
    public void setCameraRear() {
    	CameraModeMessage rearMessage = CameraModeMessage.getRearFacingMessage();
    	sendMessageMostRecentThread(rearMessage);
    }
    
    public void setTargetBoiler() {
    	TargetTypeMessage boilerMessage = TargetTypeMessage.getBoilerMessage();
    	sendMessageMostRecentThread(boilerMessage);
    }
    
    public void setTargetLift() {
    	TargetTypeMessage liftMessage = TargetTypeMessage.getLiftMessage();
    	sendMessageMostRecentThread(liftMessage);
    }

    @Override
    public void run() {
        Robot.logger.log("VisionServer thread starting", 3);

        p = null;
        while (running) {
            // If socket is disconnected, attempt to reconnect and start new ServerThread
            try {
                if (p == null) {
                    Robot.logger.log("Attempting to accept socket", 3);
                    p = serverSocket.accept();
                    Robot.logger.log("Socket Accepted!", 3);
                    
                    ServerThread s = new ServerThread(p);
                    new Thread(s).start();
                    serverThreads.add(s);
                    Robot.logger.log("Created a new thread(total: " + serverThreads.size() + ")", 3);
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class AppMaintainanceThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (getTimestamp() - lastMessageReceivedTime > .1) {
                    // camera disconnected
                    ADBUtils.adbReverseForward(port, port);
                    connected = false;
                } else {
                    connected = true;
                }
                if (wantsAppRestart) {
                    ADBUtils.restartApp();
                    wantsAppRestart = false;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private double getTimestamp() {
        return Timer.getFPGATimestamp();
    }

}
