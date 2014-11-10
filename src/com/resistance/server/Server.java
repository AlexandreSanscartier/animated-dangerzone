package com.resistance.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

import com.resistance.util.NetworkUtil;

public class Server {

	private final int port = NetworkUtil.getServerPort();
	private ServerSocket serverSocket;
	private List<Socket> clientSockets;

	private List<ServerThread> serverThreads;
	
	public static void main(String[] args) {
		Server s = new Server();
		s.init();
	}
	
	public List<ServerThread> getServerThreads() {
		return serverThreads;
	}
	
	public void init() {

		serverThreads = new ArrayList<ServerThread>();

		// create Server socket
		try {

			serverSocket = new ServerSocket(port);

		} catch (IOException e) {

			System.out.println("Can't listen to port : " + port);
		}

		clientSockets = new ArrayList<Socket>();                
		System.out.println("Awaiting Connections...");

		// Listen for connection
		while(true) {

			try {

				Socket clientSocket = serverSocket.accept();
				clientSockets.add(clientSocket);

				ServerThread st = new ServerThread(clientSocket, this);
				serverThreads.add(st);
				Thread t = new Thread(st);
				t.start();

			} catch (IOException e) {

				System.err.println("Accept a échoué.");
				System.exit(1);

			}

			System.out.println("Connection accepted");

		}

	}

}