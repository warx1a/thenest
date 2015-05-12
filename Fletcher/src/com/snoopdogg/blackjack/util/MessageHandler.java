package com.snoopdogg.blackjack.util;

import org.powerbot.script.MessageEvent;

import com.snoopdogg.blackjack.BlackjackMain;

public class MessageHandler {
	
	private final BlackjackMain MAIN;
	
	public MessageHandler(BlackjackMain MAIN) {
		this.MAIN = MAIN;
	}
	
	public void handleMessage(MessageEvent e) {
		if(e.source().equals("")) {
			if(e.text().contains("You empty the") || e.text().contains("You retrieve") || e.text().contains("You pick")) {
				MAIN.thieved += 1;
			} else if(e.text().contains("stunned")) {
				MAIN.failures += 1;
			}
		}
	}

}
