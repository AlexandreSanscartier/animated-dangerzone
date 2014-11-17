package com.resistance.server;

import java.util.List;
import java.util.ArrayList;

import com.resistance.game.NetworkResistanceGame;
import com.resistance.server.commands.ServerCommand;
import com.resistance.util.NetworkUtil;

public class Server {

	private final int port = NetworkUtil.getServerPort();
	private List<ServerCommand> serverCommands; 

	private ServerConnectionThread serverConnectionThread;
	private List<ServerClientConnectionThread> clientConnectionThreads;

	public static void main(String[] args) {
		Server s = new Server();
		s.init();
	}

	public int getPort() {
		return port;
	}

	protected void addServerThread(ServerClientConnectionThread st) {
		clientConnectionThreads.add(st);
	}

	public void pushCommand(ServerCommand command) {
		serverCommands.add(command);
	}

	public List<ServerClientConnectionThread> getServerThreads() {
		return clientConnectionThreads;
	}

	public void init() {
		serverCommands = new ArrayList<ServerCommand>();
		clientConnectionThreads = new ArrayList<ServerClientConnectionThread>();

		serverConnectionThread = new ServerConnectionThread(this);
		Thread t = new Thread(serverConnectionThread);
		t.start();

		// Run commands
		while(true) {

			//Once 5 clients have connected start the game
			if(clientConnectionThreads.size() == 5) {
				//TODO: NetworkResistanceGame needs to be in its own Thread
				NetworkResistanceGame nrg = new NetworkResistanceGame(clientConnectionThreads.size(), this);
				Thread networkGame = new Thread(nrg);
				networkGame.start();
			}

			//TODO: Check for commands to run on server
			if(serverCommands.size() > 0)
				runCommands();

			Thread.yield();

		}

	}

	//Synchronized because what happens if a commands comes in while we're running this function?
	//TODO: Flag commands that have been executed for deletion
	private synchronized void runCommands() {
		//Run all current commands that are in the command queue
		for(int i = 0; i < serverCommands.size(); i++) {
			serverCommands.get(i).execute();
		}
		//Remove all commands since they have been run or flag them for deletion??
		for(int i = 0; i < serverCommands.size(); i++) {
			serverCommands.remove(i);
		}


	}

}