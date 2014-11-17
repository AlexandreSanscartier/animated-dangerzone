package com.resistance.client;

import com.resistance.game.ConsoleResistanceGame;

public class Console {

	public static void main(String[] args) {
		
		System.out.println("Console App starting");
		System.out.println("====================");
		
		ConsoleResistanceGame resistance = new ConsoleResistanceGame(10);
		resistance.PlayGame();
	}

}
