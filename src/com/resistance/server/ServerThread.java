package com.resistance.server;

import java.net.Socket;

public class ServerThread implements Runnable {

	private Socket socket;
	private Server server;

	public ServerThread(Socket s, Server server) {

		this.socket = s;                
		this.server = server;
	}

	public void run() {



	}       
}
