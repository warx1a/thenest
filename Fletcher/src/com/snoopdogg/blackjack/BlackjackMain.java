package com.snoopdogg.blackjack;

import java.awt.Graphics;
import java.util.ArrayList;


import org.powerbot.script.Condition;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;

import com.snoopdogg.blackjack.tasks.BlackjackThievesGuild;
import com.snoopdogg.blackjack.tasks.GotoBank;
import com.snoopdogg.blackjack.tasks.Pickpocket;
import com.snoopdogg.blackjack.tasks.ReorientCamera;
import com.snoopdogg.blackjack.util.MessageHandler;
import com.snoopdogg.blackjack.util.PaintHandler;
import com.snoopdogg.blackjack.variables.Variables;
import com.snoopdogg.framework.Task;

@Script.Manifest(name="BasicBlackjack", description = "Trains blackjacking at thieves guild, also supports draynor master farmer", properties = "topic=1228850;hidden=false;client=6;")
public class BlackjackMain extends PollingScript<ClientContext> implements PaintListener, MessageListener {
	
	private ArrayList<Task> TASKS = new ArrayList<Task>();
	
	private final Variables VARS = new Variables();
	private final GUI gui = new GUI(this,VARS);
	private final PaintHandler PAINTER = new PaintHandler(ctx,this);
	private final MessageHandler MESSAGEHANDLER = new MessageHandler(this);
	
	public long starttime;
	
	public int START_THIEVING_XP, START_THIEVING_LEVEL;
	
	public int thieved, failures;
	
	public String status;
	
	public boolean finished = false;
	
	@Override
	public void start() {
		gui.init();
		starttime = System.currentTimeMillis();
		START_THIEVING_XP = ctx.skills.experience(Skills.THIEVING);
		START_THIEVING_LEVEL = ctx.skills.level(Skills.THIEVING);
	}

	@Override
	public void poll() {
		if(finished) {
			for(Task t: TASKS) {
				if(t.activate()) {
					t.execute();
				}
			}
			Condition.sleep(Random.nextInt(150, 300));
		}
	}

	@Override
	public void messaged(MessageEvent arg0) {
		MESSAGEHANDLER.handleMessage(arg0);
	}

	@Override
	public void repaint(Graphics arg0) {
		PAINTER.paint(arg0);
	}
	
	public void addBlackjackTasks() {
		TASKS.add(new ReorientCamera(ctx));
		TASKS.add(new BlackjackThievesGuild(ctx,this,VARS));
	}
	
	public void addPickpocketTasks() {
		TASKS.add(new ReorientCamera(ctx));
		TASKS.add(new Pickpocket(ctx,this,VARS));
		TASKS.add(new GotoBank(ctx,this,VARS));
	}

}
