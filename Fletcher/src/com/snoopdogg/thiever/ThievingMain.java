package com.snoopdogg.thiever;

import java.awt.Graphics;
import java.util.ArrayList;

import java.util.concurrent.ConcurrentHashMap;

import org.powerbot.script.Condition;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;

import com.snoopdogg.thiever.tasks.ReorientCamera;
import com.snoopdogg.framework.Task;

@Script.Manifest(name="BasicThiever", description = "Thieves various NPCs", properties = "topic=1228850;hidden=true;client=6;")
public class ThievingMain extends PollingScript<ClientContext> implements PaintListener, MessageListener {
	
	private ArrayList<Task> TASKS = new ArrayList<Task>();
	
	public long starttime;
	
	public int START_THIEVING_XP, START_THIEVING_LEVEL;
	
	public int thieved, failures;
	
	public String status;
	
	public boolean finished = false;
	
	public ConcurrentHashMap<Integer, Integer> prices;
	
	@Override
	public void start() {
		prices = new ConcurrentHashMap<Integer,Integer>();
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
	}

	@Override
	public void repaint(Graphics arg0) {
	}
	
	public void addPickpocketTasks() {
		TASKS.add(new ReorientCamera(ctx));
	}

}