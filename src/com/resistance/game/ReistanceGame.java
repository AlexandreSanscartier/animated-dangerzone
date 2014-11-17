package com.resistance.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.resistance.util.Numbers;

public abstract class ReistanceGame {
	
	//Constants that define the player types based on number of players
	protected final int[] SPIES = {2,2,3,3,3,4};
	protected final int[][] PLAYERS_PER_TEAM  = {
			{2,3,2,3,3}, //For 5 players
			{2,3,4,3,4}, //For 6 players
			{2,3,3,4,4}, //For 7 players
			{3,4,4,5,5}, //For 8 players
			{3,4,4,5,5}, //For 9 players
			{3,4,4,5,5}  //For 10 players
	};

	protected int leaderIndex = 0;
	protected int missionNumber = 0;
	protected int spyPoints = 0;
	protected int rebelPoints = 0;
	
	protected int yesVotes = 0, noVotes = 0;
	
	protected boolean victoryCondition = false;

	protected int numberOfPlayers;
	protected Player[] players;
	protected ArrayList<Player> teamMembers;
	
	public ReistanceGame(int numPlayers) {
		numberOfPlayers = numPlayers;
		players = new Player[numberOfPlayers];
	}

	//Init the players
	protected void initPlayers() {
		for(int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player();
			players[i].setPlayerName("P:" + i);
		}
		//Shuffle players array
		Collections.shuffle(Arrays.asList(players));
	}
	
	/***
	 * 
	 */
	protected void setNextLeader() {
		if(leaderIndex == players.length - 1)
			leaderIndex = 0;
		else
			leaderIndex++;
	}
	
	/***
	 * 
	 */
	protected void checkVictoryConditions() {
		if(spyPoints == 3) {
			victoryCondition = true;
		} else if(rebelPoints == 3) {
			victoryCondition = true;
		}
	}
	
	/**
	 * 
	 * @param player
	 */
	public void addTeamMember(Player player) {
		if(teamMembers.size() < PLAYERS_PER_TEAM[numberOfPlayers-5][missionNumber])
			teamMembers.add(player);
	}
	
	/**
	 * 
	 * @param player
	 */
	public void removeTeamMember(Player player) {
		if(teamMembers.contains(player)) {
			teamMembers.remove(player);
		}
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Player> getTeamMembers() {
		return teamMembers;
	}

	
	/**
	 * Set the spies for this instance of Resistance
	 */
	protected void setSpies() {
		int numberOfSpies = SPIES[numberOfPlayers-5];
		for(int i = 0; i < numberOfSpies; i++) {
			players[i].setSpy();
		}
	}	
	
	/**
	 * 
	 * @return Player the round's current leader
	 */
	protected Player designateLeader() {
		leaderIndex = Numbers.getRandom(0, numberOfPlayers - 1);
		Player Leader = players[leaderIndex];

		return Leader;
	}
	
	protected abstract void getPlayerVotes();
	
	private void playerVote() {

		int voteCount = 0;

		do {
			//Re-init vote count
			noVotes = 0;
			yesVotes = 0;
			getPlayerVotes();
			voteCount++;
			if(yesVotes <= noVotes) {
				setNextLeader();
				teamAssignments();
			}
			
			
		} while(yesVotes <= noVotes && voteCount != 5);
		if(voteCount == 5) {
			//TODO: Don't exit but send that spies are victorious
			System.out.println("Spies win!");
			System.exit(0);
		}
	}
	
	/***
	 * 
	 */
	private void setUpTeamMembers() {
		teamMembers = new ArrayList<Player>();
	}

	protected abstract void teamAssignments();
	protected abstract void missionVote();
	
	/***
	 * 
	 */
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
			teamAssignments();
			//TODO: Leader chooses players to send on mission

			//Mission Team Vote
			playerVote();
			//TODO: Players vote for the team to pass or not
			//TODO: After 5 rejected mission teams the spies win

			//Conduct Mission
			missionVote();
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


}
