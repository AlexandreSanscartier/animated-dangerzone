package com.resistance.game;

import java.util.ArrayList;

import com.resistance.network.IMessage;

public interface GameActions {
	
	public void chooseTeam(Player[] players); 		//Only if player is leader
	public void teamVote(TeamVoteSelection choice); //Always after team is chosen by leader
	public void missionVote(MissionSuccess choice); //Only if chosen to be on team
	

}
