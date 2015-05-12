package com.snoopdogg.fletchandfm;

import java.awt.Graphics;
import java.util.ArrayList;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;
import org.powerbot.script.Script;

import com.snoopdogg.fletchandfm.tasks.Bank;
import com.snoopdogg.fletchandfm.tasks.Burn;
import com.snoopdogg.fletchandfm.tasks.Fletch;
import com.snoopdogg.fletchandfm.tasks.GetReward;
import com.snoopdogg.fletchandfm.util.MessageHandler;
import com.snoopdogg.fletchandfm.util.PaintHandler;
import com.snoopdogg.fletchandfm.variables.Constants;
import com.snoopdogg.framework.Task;

@Script.Manifest(name="BasicFletcher", description = "Fletches bows and burns logs", properties = "topic=1228038;hidden=true;client=6;")
public class FletchingMain extends PollingScript<ClientContext> implements PaintListener, MessageListener {
	
    private final Constants CONSTS = new Constants(ctx);
    private final MessageHandler MESSAGEHANDLER = new MessageHandler(ctx,CONSTS);
    private final PaintHandler PAINTHANDLER = new PaintHandler(this,ctx,CONSTS);
	
	private final ArrayList<Task> TASKS = new ArrayList<Task>();
	
	private GUI gui;

	public final int START_FLETCHING_XP = ctx.skills.experience(Skills.FLETCHING);
	public final int START_FIREMAKING_XP = ctx.skills.experience(Skills.FIREMAKING);
	public final int START_FIREMAKING_LEVEL = ctx.skills.level(Skills.FIREMAKING);
	public final int START_FLETCHING_LEVEL = ctx.skills.level(Skills.FLETCHING);
	public final long starttime = System.currentTimeMillis();
	
	public final ClientContext context = ctx;
    
    @Override
    public void start() {
    	gui = new GUI(this,CONSTS);
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
		PAINTHANDLER.paint(g);
	}

	@Override
	public void messaged(MessageEvent message) {
		MESSAGEHANDLER.handleMessage(message);
	}
	
	public void addFletchTasks() {
    	TASKS.add(new Fletch(ctx,CONSTS));
    	TASKS.add(new Bank(ctx,CONSTS));
	}
	
	public void addFiremakingTasks() {
		TASKS.add(new Bank(ctx,CONSTS));
    	TASKS.add(new Burn(ctx,CONSTS));
    	TASKS.add(new GetReward(ctx,CONSTS));
	}
}
