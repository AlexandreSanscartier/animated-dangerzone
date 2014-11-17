package com.resistance.game;

import com.resistance.server.Server;

public class NetworkResistanceGame extends ReistanceGame implements Runnable {
	
	private Server server;

	public NetworkResistanceGame(int numPlayers, Server server) {
		super(numPlayers);
		this.server = server;
	}

	@Override
	protected void getPlayerVotes() {
		// TODO Auto-generated method stub
		System.out.println("Player Assignments");
	}

	@Override
	protected void teamAssignments() {
		// TODO Auto-generated method stub
		System.out.println("Team Assignments");
		//GET TEAM ASSIGNMENTS FROM SERVER
		while(true) {
			System.out.println("Awaiting response...");
		}
	}

	@Override
	protected void missionVote() {
		// TODO Auto-generated method stub
		System.out.println("Mission Votes");
	}

	@Override
	public void run() {
		super.PlayGame();
	}
	

}
