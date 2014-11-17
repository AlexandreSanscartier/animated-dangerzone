package com.resistance.game;

import java.util.ArrayList;

import com.resistance.constants.MissionSuccessEnum;
import com.resistance.constants.TeamVoteSelectionEnum;
import com.resistance.network.IMessage;

public interface GameActions {
	
	public void chooseTeam(Player[] players); 			//Only if player is leader
	public void teamVote(TeamVoteSelectionEnum choice); //Always after team is chosen by leader
	public void missionVote(MissionSuccessEnum choice); //Only if chosen to be on team
	

}
