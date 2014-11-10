package com.resistance.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.resistance.util.Numbers;

public class Resistance {

	//Constants that define the player types based on number of players
	private final int[] SPIES = {2,2,3,3,3,4};
	private final int[][] PLAYERS_PER_TEAM  = {
			{2,3,2,3,3}, //For 5 players
			{2,3,4,3,4},  //For 6 players
			{2,3,3,4,4},  //For 7 players
			{3,4,4,5,5},  //For 8 players
			{3,4,4,5,5},  //For 9 players
			{3,4,4,5,5}  //For 10 players
	};

	private int leaderIndex = 0;
	private int missionNumber = 0;
	private int spyPoints = 0;
	private int rebelPoints = 0;
	
	private boolean victoryCondition = false;

	private int numberOfPlayers;
	private Player[] players;
	private ArrayList<Player> teamMembers;

	public Resistance(int numPlayers) {
		numberOfPlayers = numPlayers;
		players = new Player[numberOfPlayers];
	}

	//Init the players
	private void initPlayers() {
		for(int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player();
			players[i].setPlayerName("P:" + i);
		}
		//Shuffle players array
		Collections.shuffle(Arrays.asList(players));
	}

	public void PlayGame() {

		initPlayers();

		//Spies Reveal
		setSpies();

		//Designate a leader
		Player Leader = designateLeader();

		//TODO: Eventually allow Leader to choose round number if that rule is set
		missionNumber = 0;

		do {
			// Set up the array that will hold the Players that form the TeamMembers
			setUpTeamMembers();
			//Mission Team Assignments	
			mockTeamAssignments();
			//TODO: Leader chooses players to send on mission

			//Mission Team Vote
			mockPlayerVote();
			//TODO: Players vote for the team to pass or not
			//TODO: After 5 rejected mission teams the spies win

			//Conduct Mission
			mockMissionVote();
			//TODO: Each player on the mission votes success or fail
			//TODO: Resistance players must select Mission Success
			//TODO: Spies get to choose either success or failure

			//TODO: Check for victory conditions
			checkVictoryConditions();
			//TODO: Pass Leader to next player
			if(!victoryCondition) {
				setNextLeader();
				missionNumber++;
			}
		} while(!victoryCondition);

	}

	private void mockMissionVote() {

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

	private void mockPlayerVote() {

		int voteCount = 0;
		int yesVotes = 0;
		int noVotes = 0;

		do {
			yesVotes = 0;
			noVotes = 0;
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
			voteCount++;
		} while(yesVotes <= noVotes && voteCount != 5);
		if(voteCount == 5) {
			System.out.println("Spies win!");
			System.exit(0);
		} 

	}

	private void mockTeamAssignments() {
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

	private void setNextLeader() {
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

	private void checkVictoryConditions() {
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
	 */
	private void setUpTeamMembers() {
		teamMembers = new ArrayList<Player>();
	}

	/**
	 * 
	 * @return Player the round's current leader
	 */
	private Player designateLeader() {
		leaderIndex = Numbers.getRandom(0, numberOfPlayers - 1);
		Player Leader = players[leaderIndex];
		System.out.println("-------------------");
		System.out.println("- First Leader is -");
		System.out.println("-------------------");
		System.out.println(leaderIndex + " : " + Leader.getName());

		return Leader;
	}

	/**
	 * Set the spies for this instance of Resistance
	 */
	private void setSpies() {
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


	/**
	 * 
	 * @param player
	 */
	public void addTeamMember(Player player) {
		// TODO Auto-generated method stub
		if(teamMembers.size() < PLAYERS_PER_TEAM[numberOfPlayers-5][missionNumber])
			teamMembers.add(player);
		else
			System.out.println("Error cannot add more team members!");
	}

	/**
	 * 
	 * @param player
	 */
	public void removeTeamMember(Player player) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Player> getTeamMembers() {
		return teamMembers;
	}


}
