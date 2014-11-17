package com.resistance.server.commands;

public class DisplayMessageServerCommand implements ServerCommand {
	
	private String message;

	
	public DisplayMessageServerCommand(String message) {
		
		this.message = message;
		
	}

	@Override
	public void execute() {
		
		System.out.println(message);


	}


}
