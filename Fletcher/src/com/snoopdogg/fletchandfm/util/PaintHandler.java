package com.snoopdogg.fletchandfm.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;
import org.powerbot.script.rt6.TileMatrix;

import com.snoopdogg.fletchandfm.FletchingMain;
import com.snoopdogg.fletchandfm.variables.Constants;

public class PaintHandler extends ClientAccessor {
	
	private final Constants CONSTS;
	private final FletchingMain MAIN;
	
	public PaintHandler(final FletchingMain MAIN, final ClientContext ctx,final Constants CONSTS) {
		super(ctx);
		this.CONSTS = CONSTS;
		this.MAIN = MAIN;
	}
	
	public void paint(final Graphics graphics) {
		final Graphics2D G = (Graphics2D) graphics;
		G.setColor(Color.ORANGE);
		G.fillRect(0, 414, 550, 200);
		G.setColor(Color.BLACK);
		G.setFont(new Font("SansSerif",Font.BOLD,12));
		if(CONSTS.fletching != null) {
			// firemaking column
			G.drawString("Firemaking xp gained: " + String.valueOf(ctx.skills.experience(Skills.FIREMAKING) - MAIN.START_FIREMAKING_XP), 25, 450);
			G.drawString("Fires started: " + String.valueOf(CONSTS.regular_fire), 25, 475);
			G.drawString("Logs added to bonfire: " + String.valueOf(CONSTS.bonfire_fire), 25, 500);
			G.drawString("Firemaking levels gained: " + String.valueOf(ctx.skills.level(Skills.FIREMAKING) - MAIN.START_FIREMAKING_LEVEL), 25, 525);
			// fletching column
			G.drawString("Fletching xp gained: " + String.valueOf(ctx.skills.experience(Skills.FLETCHING) - MAIN.START_FLETCHING_XP), 225, 450);
			G.drawString("Fletching items created: " + String.valueOf(CONSTS.cut), 225, 475);
			G.drawString("Fletching levels gained: " + String.valueOf(ctx.skills.level(Skills.FLETCHING) - MAIN.START_FLETCHING_LEVEL), 225, 500);
			// runtime field
			G.drawString("Time elapsed: " + formatTime(System.currentTimeMillis() - MAIN.starttime), 100, 575);
		}
		// draw the fire tiles
		if(CONSTS.ACCEPTABLE_TILES.size() > 0) {
			G.setColor(new Color(0,255,0));
			for(final Tile t: CONSTS.ACCEPTABLE_TILES) {
				final TileMatrix matrix = t.matrix(ctx);
				final Polygon p = matrix.bounds();
				G.fill(p);
			}
		}
	}
	
	
	public String formatTime(final long time) {
		long second = (time / 1000) % 60;
		long minute = (time / (60000)) % 60;
		long hour = (time / (3600000)) % 24;

		String timestamp = String.format("%02d:%02d:%02d", hour, minute, second);
		return timestamp;
		
	}
	
	

}
