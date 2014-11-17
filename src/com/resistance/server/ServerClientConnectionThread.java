package com.resistance.server;

import java.net.Socket;

import com.resistance.network.NetworkObjectManager;
import com.resistance.server.commands.DisplayMessageServerCommand;

public class ServerClientConnectionThread implements Runnable {

	private Socket socket;
	private Server server;
	private NetworkObjectManager objectManager;

	public ServerClientConnectionThread(Socket s, Server server) {

		this.socket = s;                
		this.server = server;
		this.objectManager = new NetworkObjectManager(socket);
		
	}

	public void run() {

		// 1. Initialize ServerThread
		
		//TODO: REMOVE LINE
		DisplayMessageServerCommand command = new DisplayMessageServerCommand("ServerThread Created");
		server.pushCommand(command);
		
		while(true) {
			// 2. While running Thread
			
			Thread.yield();
			
		}

	}       
}
