package com.resistance.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkObjectManager {

	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;

	public NetworkObjectManager(Socket socket) {

		createObjectOutputStream(socket);
		createObjectInputStream(socket);

	}

	/**
	 * Create the NetworkObjectManagers output stream
	 * @param socket the Socket to create an ObjectOutputStream on
	 */
	private void createObjectOutputStream(Socket socket) {

		try {

			outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.flush();

		} catch (IOException e) {

			System.out.println("Unable to instatiate outputStream");
			e.printStackTrace();

		}

	}

	/**
	 * Create the NetworkObjectManagers input stream
	 * @param socket the Socket to create an ObjectInputStream on
	 */
	private void createObjectInputStream(Socket socket) {
		try {

			inputStream = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {

			System.out.println("Unable to instatiate inputStream");
			e.printStackTrace();

		}
	}

	/**
	 * Send an object over the network 
	 * @param obj the Object to send
	 */
	public void write(Object obj) {

		try {

			outputStream.writeObject(obj);

		} catch (IOException e) {

			System.out.println("OutputStream is not connected");
			e.printStackTrace();

		}
	}

	/**
	 * Read an object sent over the network
	 * @return the read Object
	 */
	public Object read() {  

		try {

			return inputStream.readObject();

		} catch (IOException e) {

			System.out.println("InputStream is not connected");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			System.out.println("Class not found");
			e.printStackTrace();
		}

		return null;

	}

}
