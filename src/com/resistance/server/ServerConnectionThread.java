package com.resistance.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/***
 * Awaits Client connections
 * @author sea-alex
 *
 */
public class ServerConnectionThread implements Runnable {

	private Server server;
	private ServerSocket serverSocket;
	private List<Socket> clientSockets;

	public ServerConnectionThread(Server server) {
		this.server = server;
	}

	@Override
	public void run() {

		int port = server.getPort();
		// TODO Auto-generated method stub
		// create Server socket
		try {

			serverSocket = new ServerSocket(port);

		} catch (IOException e) {

			System.out.println("Can't listen to port : " + port);
		}

		clientSockets = new ArrayList<Socket>();                
		System.out.println("Awaiting Connections...");

		while(true) {

			try {

				//serverSocket.accept is blocking
				Socket clientSocket = serverSocket.accept();
				clientSockets.add(clientSocket);

				ServerClientConnectionThread st = new ServerClientConnectionThread(clientSocket, server);
				server.addServerThread(st);
				Thread t = new Thread(st);
				t.start();
				System.out.println("Connection accepted");

			} catch (IOException e) {

				System.err.println("Accept a échoué.");

			}

		}

	}

}
