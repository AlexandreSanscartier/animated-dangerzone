package com.resistance.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.resistance.util.Numbers;

public class ConsoleResistanceGame extends ReistanceGame {

	public ConsoleResistanceGame(int numPlayers) {
		super(numPlayers);
	}	

	@Override
	protected void teamAssignments() {
		Player[] shuffledPlayers = players;
		Collections.shuffle(Arrays.asList(shuffledPlayers));

		for(int i = 0; i < PLAYERS_PER_TEAM[numberOfPlayers-5][missionNumber]; i++) {
			teamMembers.add(shuffledPlayers[i]);
		}

		System.out.println("-------------------");
		System.out.println("-- First Team is --");
		System.out.println("-------------------");

		for(int i = 0; i < PLAYERS_PER_TEAM[numberOfPlayers-5][missionNumber]; i++) {
			System.out.println(shuffledPlayers[i].getName());
		}
	}

	@Override
	protected void missionVote() {
		int successCount = 0;
		int failureCount = 0;

		for(int i = 0; i < PLAYERS_PER_TEAM[numberOfPlayers-5][missionNumber]; i++) {
			if(teamMembers.get(i).isSpy())
				failureCount++;
			else
				successCount++;
		}

		System.out.println("-------------------");
		System.out.println("-- Mission Counter --");
		System.out.println("-------------------");
		System.out.println("Success: " + successCount);
		System.out.println("Failure: " + failureCount);



		if((missionNumber == 4 && numberOfPlayers >= 7 && failureCount >= 2) 
				|| failureCount >= 1 && (numberOfPlayers < 7)
				|| missionNumber != 4 && numberOfPlayers >= 7 && failureCount >= 1) {
			System.out.println("Spies get a point!");
			spyPoints++;

		} else {
			System.out.println("Rebels get a point!");
			rebelPoints++;
		}
	}
		
	public void addTeamMember(Player player) {
		// TODO Auto-generated method stub
		if(teamMembers.size() < PLAYERS_PER_TEAM[numberOfPlayers-5][missionNumber])
			teamMembers.add(player);
		else
			System.out.println("Error cannot add more team members!");
	}
	
	
	protected void setNextLeader() {
		if(leaderIndex == players.length - 1)
			leaderIndex = 0;
		else
			leaderIndex++;

		Player Leader = players[leaderIndex];
		System.out.println("-------------------");
		System.out.println("- Next Leader is  -");
		System.out.println("-------------------");
		System.out.println(leaderIndex + " : " + Leader.getName());

	}
	
	protected void setSpies() {
		int numberOfSpies = SPIES[numberOfPlayers-5];
		for(int i = 0; i < numberOfSpies; i++) {
			players[i].setSpy();
		}

		String spys = "";

		for(int i = 0; i < numberOfPlayers; i++) {
			if(players[i].isSpy()) {
				spys += players[i].getName() + " ";
			}
		}

		System.out.println("These are the spies: " + spys);

	}
	
	protected void checkVictoryConditions() {
		if(spyPoints == 3) {
			System.out.println("Spies win!");
			victoryCondition = true;
		} else if(rebelPoints == 3) {
			System.out.println("Rebels win!");
			victoryCondition = true;
		}
	}
	/**
	 * 
	 * @return Player the round's current leader
	 */
	protected Player designateLeader() {
		leaderIndex = Numbers.getRandom(0, numberOfPlayers - 1);
		Player Leader = players[leaderIndex];
		System.out.println("-------------------");
		System.out.println("- First Leader is -");
		System.out.println("-------------------");
		System.out.println(leaderIndex + " : " + Leader.getName());

		return Leader;
	}

	@Override
	protected void getPlayerVotes() {
		for(int i = 0; i < numberOfPlayers; i++) {
			if(Numbers.getRandom(0,1) == 0) 
				noVotes++;
			else
				yesVotes++;
		}
		System.out.println("-------------------");
		System.out.println("-- Vote Counter --");
		System.out.println("-------------------");
		System.out.println("Yes: " + yesVotes);
		System.out.println("No : " + noVotes);
	}
}
