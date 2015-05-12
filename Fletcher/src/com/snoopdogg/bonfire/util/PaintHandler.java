package com.snoopdogg.bonfire.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.text.DecimalFormat;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;
import org.powerbot.script.rt6.TileMatrix;

import com.snoopdogg.bonfire.BonfireMain;
import com.snoopdogg.bonfire.variables.Constants;

public class PaintHandler extends ClientAccessor {
	
	private final Constants CONSTS;
	private final BonfireMain MAIN;
	private final DecimalFormat FORMAT = new DecimalFormat();
	public final Rectangle PAINT_RECT = new Rectangle(0, 400, 575, 600);
	
	public PaintHandler(final BonfireMain MAIN, final ClientContext ctx,final Constants CONSTS) {
		super(ctx);
		this.CONSTS = CONSTS;
		this.MAIN = MAIN;
		FORMAT.setMaximumFractionDigits(2);
	}
	
	public void paint(final Graphics graphics) {
		// the average gp per xp gained
		double AVERAGE_GP_PER_XP;
		// calculates the part of the hour thats passed
		final double PART_OF_HOUR = (3600000d / (System.currentTimeMillis() - MAIN.starttime));
		// gets the amt of xp gained
		final int XP_GAINED = ctx.skills.experience(Skills.FIREMAKING) - MAIN.START_FIREMAKING_XP;
		// gets gp losses of burning logs
		final int LOSSES = (CONSTS.regular_fire + CONSTS.bonfire_fire) * CONSTS.costs_per_log;
		// calcs average gp per xp
		if(LOSSES > 0) {
			AVERAGE_GP_PER_XP = (double)LOSSES / XP_GAINED;
		} else {
			AVERAGE_GP_PER_XP = (double)0;
		}
		// calcs the xp per hour
		final int XP_PER_HOUR = (int) ((ctx.skills.experience(Skills.FIREMAKING) - MAIN.START_FIREMAKING_XP) * PART_OF_HOUR);
		// calcs the amount of loss of gp per hour
		final int LOSS_PER_HOUR = (int) (((CONSTS.bonfire_fire + CONSTS.regular_fire) * CONSTS.costs_per_log) * PART_OF_HOUR);
		// calcs the amount of rewards earned per hour
		final int REWARDS_PER_HOUR = (int) (CONSTS.rewards_gathered * PART_OF_HOUR);
		// calcs the amt of logs burned per hour
		final int LOGS_PER_HOUR = (int)((CONSTS.bonfire_fire + CONSTS.regular_fire) * PART_OF_HOUR);
		// clacs the amt of xp to next level
		final int XP_TNL = (ctx.skills.experienceAt(ctx.skills.level(Skills.FIREMAKING) + 1)) - ctx.skills.experience(Skills.FIREMAKING);
		final Graphics2D G = (Graphics2D) graphics;
		G.setColor(Color.WHITE);
		G.fillRect(0, 400, 575, 600);
		G.setColor(Color.BLACK);
		G.setFont(new Font("SansSerif",Font.BOLD,12));
		if(CONSTS.fletching != null) {
			// 1st column
			ctx.skills.level(ctx.skills.level(Skills.WOODCUTTING));
			G.drawString("Firemaking xp gained: " + String.valueOf(XP_GAINED), 25, 450);
			G.drawString("XP per hour: " + String.valueOf(XP_PER_HOUR), 25, 475);
			G.drawString("Firemaking levels gained: +" + String.valueOf(ctx.skills.level(Skills.FIREMAKING) - MAIN.START_FIREMAKING_LEVEL), 25, 500);
			G.drawString("Logs added to bonfire: " + String.valueOf(CONSTS.bonfire_fire), 25, 525);
			G.drawString("Fires started: " + String.valueOf(CONSTS.regular_fire), 25, 550);
			// 2nd column
			G.drawString("Xp to next level: " + String.valueOf(XP_TNL), 200, 450);
			G.drawString("Rewards gathered: " + String.valueOf(CONSTS.rewards_gathered), 200, 475);
			G.drawString("Logs per hour: " + String.valueOf(LOGS_PER_HOUR), 200, 500);
			G.drawString("Money lost: " + String.valueOf(LOSSES), 200, 525);
			G.drawString("Loss per hour: " + String.valueOf(LOSS_PER_HOUR), 200, 550);
			// 3rd column
			G.drawString("Rewards per hour: " + String.valueOf(REWARDS_PER_HOUR), 400, 475);
			// time running field
			G.drawString("Time elapsed: " + formatTime(System.currentTimeMillis() - MAIN.starttime), 25, 575);
			G.drawString("Status: " + MAIN.status, 200, 575);
			G.drawString("Average gp/xp: " + FORMAT.format(AVERAGE_GP_PER_XP), 400, 575);
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
