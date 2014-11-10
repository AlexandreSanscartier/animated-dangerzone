package com.resistance.game;

/**
 * 
 * @author Alex
 *
 */
public class Player {
	
	private String playerName;
	private boolean isSpy;
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getName() {
		return this.playerName;
	}

	public void setSpy() {
		isSpy = true;
	}
	
	public boolean isSpy() {
		return isSpy;
	}

}
