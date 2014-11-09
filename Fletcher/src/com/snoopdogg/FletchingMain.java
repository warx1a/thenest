package com.snoopdogg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;
import org.powerbot.script.Script;

import com.snoopdogg.tasks.Bank;
import com.snoopdogg.tasks.Burn;
import com.snoopdogg.tasks.Fletch;
import com.snoopdogg.util.MessageHandler;
import com.snoopdogg.variables.Constants;
import com.snoopdogg.framework.Task;

@Script.Manifest(name="BasicFletcher", description = "fletcher", properties = "client=6;")
public class FletchingMain extends PollingScript<ClientContext> implements PaintListener, MessageListener {
	
    private final Constants CONSTS = new Constants(ctx);
    private final MessageHandler MESSAGEHANDLER = new MessageHandler(ctx,CONSTS);
	
	private final ArrayList<Task> TASKS = new ArrayList<Task>();
	
	private GUI gui;
	
	private int START_FLETCHING_XP;
	private int START_FIREMAKING_XP;
	private long starttime;
    
    @Override
    public void start() {
    	START_FLETCHING_XP = ctx.skills.experience(Skills.FLETCHING);
    	START_FIREMAKING_XP = ctx.skills.experience(Skills.FIREMAKING);
    	starttime = System.currentTimeMillis();
    	gui = new GUI(CONSTS);
    	TASKS.add(new Fletch(ctx,CONSTS));
    	TASKS.add(new Bank(ctx,CONSTS));
    	TASKS.add(new Burn(ctx,CONSTS));
    }

    @Override
    public void poll() {
    	if(gui.finished) {
        	for(Task t: TASKS) {
        		if(t.activate()) {
        			t.execute();
        		}
        	}
    	}
    }
	@Override
	public void repaint(Graphics g) {
		final Graphics2D G = (Graphics2D) g;
		G.setColor(Color.ORANGE);
		G.fillRect(0, 414, 550, 200);
		G.setColor(Color.BLACK);
		if(CONSTS.fletching != null) {
			// firemaking column
			G.drawString("Firemaking xp gained: " + String.valueOf(ctx.skills.experience(Skills.FIREMAKING) - START_FIREMAKING_XP), 25, 450);
			G.drawString("Fires started: " + String.valueOf(CONSTS.regular_fire), 25, 475);
			G.drawString("Logs added to bonfire: " + String.valueOf(CONSTS.bonfire_fire), 25, 500);
			// fletching column
			G.drawString("Fletching xp gained: " + String.valueOf(ctx.skills.experience(Skills.FLETCHING) - START_FLETCHING_XP), 175, 450);
			G.drawString("Fletching items created: " + String.valueOf(CONSTS.cut), 175, 475);
			G.drawString("Time elapsed: " + formatTime(System.currentTimeMillis() - starttime), 100, 550);
		}
	}

	@Override
	public void messaged(MessageEvent message) {
		MESSAGEHANDLER.handleMessage(message);
	}
	
	public String formatTime(final long time) {
		long second = (time / 1000) % 60;
		long minute = (time / (60000)) % 60;
		long hour = (time / (3600000)) % 24;

		String timestamp = String.format("%02d:%02d:%02d", hour, minute, second);
		return timestamp;
		
	}
}
