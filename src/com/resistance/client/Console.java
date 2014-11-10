package com.resistance.client;

import com.resistance.game.Resistance;

public class Console {

	public static void main(String[] args) {
		
		System.out.println("Console App starting");
		System.out.println("====================");
		
		Resistance resistance = new Resistance(10);
		resistance.PlayGame();
	}

}
