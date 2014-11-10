package com.resistance.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.resistance.network.NetworkObjectManager;
import com.resistance.util.NetworkUtil;

public class NetworkedClient {
	
	private Socket socket;
	private NetworkObjectManager objectManager;
	
	public static void main(String[] args) {
		NetworkedClient nc = new NetworkedClient();
		while(true) {
			
			//1. Wait for Server to send a message that all players are connected
			//2. Send a message that you are ready for the game to start
			//3. Receive Player names / id's 
			
			//[4.] If spy server sends a message with all other spies
			//[5.] If leader then send to server who is on team
			
			//6. Server sends the team that leader has chosen
			//7. Vote if you accept this team or reject this team
			
			//[8.] If on mission send success or failure to the server
			
			//9. Server send results to all players
			
			//10. Server sends next leader
			
		}
	}

	public NetworkedClient() {
		
		socket = createSocket();
		objectManager = new NetworkObjectManager(socket);

	}

	private static Socket createSocket() {

		String ipAddress = NetworkUtil.getServerIP(); 
		int port = NetworkUtil.getServerPort();

		try {
			Socket s = new Socket(ipAddress, port);
			System.out.println("Successfully connected to server!");
			return s;

		} catch (UnknownHostException e) {

			System.out.println("Can not connect to unknown host");
			e.printStackTrace();

		} catch (IOException e) {

			System.out.println("Can not connect to host");
			e.printStackTrace();
		}
		return null;
	}

}
