package com.resistance.util;

import java.util.Random;

public class Numbers {
	
	public static int getRandom(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

}
