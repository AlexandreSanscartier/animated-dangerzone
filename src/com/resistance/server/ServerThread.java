package com.resistance.server;

import java.net.Socket;

import com.resistance.network.NetworkObjectManager;

public class ServerThread implements Runnable {

	private Socket socket;
	private Server server;
	private NetworkObjectManager objectManager;

	public ServerThread(Socket s, Server server) {

		this.socket = s;                
		this.server = server;
		this.objectManager = new NetworkObjectManager(socket);
		
	}

	public void run() {

		// 1. Initialize ServerThread
		
		while(true) {
			// 2. While running Thread
			
		}

	}       
}
