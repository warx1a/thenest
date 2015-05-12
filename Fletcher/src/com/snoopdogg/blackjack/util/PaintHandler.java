package com.snoopdogg.blackjack.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;

import com.snoopdogg.blackjack.BlackjackMain;

public class PaintHandler extends ClientAccessor {
	
	private final BlackjackMain MAIN;
	
	private final Color PURPLE = new Color(51,0,102);
	
	public PaintHandler(final ClientContext ctx, final BlackjackMain MAIN) {
		super(ctx);
		this.MAIN = MAIN;
	}
	
	
	public void paint(final Graphics graphics) {
		final double PART_OF_HOUR = (3600000d / (System.currentTimeMillis() - MAIN.starttime));
		final int XP_GAINED = ctx.skills.experience(Skills.THIEVING) - MAIN.START_THIEVING_XP;
		final int LEVELS_GAINED = ctx.skills.level(Skills.THIEVING) - MAIN.START_THIEVING_LEVEL;
		final int XP_PER_HOUR = (int) ((ctx.skills.experience(Skills.THIEVING) - MAIN.START_THIEVING_XP) * PART_OF_HOUR);
		final int LOOTS_PER_HOUR = (int)(MAIN.thieved * PART_OF_HOUR);
		final long TIME_ELAPSED = System.currentTimeMillis() - MAIN.starttime;
		final Graphics2D G = (Graphics2D) graphics;
		G.setColor(PURPLE);
		G.fillRect(0, 414, 550, 200);
		G.setColor(Color.WHITE);
		G.setFont(new Font("SansSerif",Font.BOLD,12));
		// 1st column
		G.drawString("Thieving xp gained: " + String.valueOf(XP_GAINED), 25, 450);
		G.drawString("Thieving levels gained: +" + String.valueOf(LEVELS_GAINED), 25, 475);
		G.drawString("Objects/People thieved: " + String.valueOf(MAIN.thieved), 25, 500);
		G.drawString("Status: " + MAIN.status,25,525);
		G.drawString("XP per hour: " + String.valueOf(XP_PER_HOUR), 25, 550);
		// 2nd column
		if(MAIN.failures >= 1) {
			G.drawString("Thieving ratio: " + String.valueOf((int) MAIN.thieved / MAIN.failures) + " to 1", 225, 475);
		} else {
			G.drawString("Thieving ratio: " + String.valueOf(MAIN.thieved) + " to 1", 225, 475);
		}
		G.drawString("Failures: " + String.valueOf(MAIN.failures),225,500);
		G.drawString("Loots per hour: " + String.valueOf(LOOTS_PER_HOUR), 225, 450);
		G.drawString("Current level: " + String.valueOf(ctx.skills.level(Skills.THIEVING)), 225, 475);
		// bottom row
		G.drawString("Time elapsed: " + formatTime(TIME_ELAPSED), 100, 575);
	}
	
	public String formatTime(final long time) {
		long second = (time / 1000) % 60;
		long minute = (time / (60000)) % 60;
		long hour = (time / (3600000)) % 24;

		String timestamp = String.format("%02d:%02d:%02d", hour, minute, second);
		return timestamp;
		
	}

}
