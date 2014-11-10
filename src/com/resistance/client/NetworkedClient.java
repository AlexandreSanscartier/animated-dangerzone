package com.resistance.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.resistance.util.NetworkUtil;

public class NetworkedClient {
	
	public static void main(String[] args) {
		NetworkedClient nc = new NetworkedClient();
	}

	public NetworkedClient() {
		
		Socket ClientSocket = createSocket();

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
