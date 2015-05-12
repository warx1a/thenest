package com.snoopdogg.granitesplitter.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.granitesplitter.SplitterMain;

public class GranitePaintHandler extends ClientAccessor {
	
	private final SplitterMain MAIN;
	private final Color BROWN = new Color(102,51,0);
	
	public GranitePaintHandler(final SplitterMain MAIN, final ClientContext ctx) {
		super(ctx);
		this.MAIN = MAIN;
	}
	
	public void paint(final Graphics graphics) {
		final Graphics2D G = (Graphics2D) graphics;
		G.setColor(BROWN);
		G.fillRect(0, 414, 550, 200);
		G.setColor(Color.WHITE);
		G.setFont(new Font("SansSerif",Font.BOLD,12));
		G.drawString("Status: " + MAIN.status, 25, 450);
		G.drawString("Runtime: " + formatTime(MAIN.getRuntime()),25,550);
	}
	
	public String formatTime(final long time) {
		long second = (time / 1000) % 60;
		long minute = (time / (60000)) % 60;
		long hour = (time / (3600000)) % 24;

		String timestamp = String.format("%02d:%02d:%02d", hour, minute, second);
		return timestamp;
		
	}

}
