package com.snoopdogg.bonfire;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;
import org.powerbot.script.Script;

import com.snoopdogg.bonfire.tasks.Bank;
import com.snoopdogg.bonfire.tasks.Burn;
import com.snoopdogg.bonfire.tasks.CloseChest;
import com.snoopdogg.bonfire.tasks.ClosePollOption;
import com.snoopdogg.bonfire.tasks.GetReward;
import com.snoopdogg.bonfire.tasks.ReorientCamera;
import com.snoopdogg.bonfire.tasks.jadinkos.BurnRoots;
import com.snoopdogg.bonfire.tasks.jadinkos.CollectRoots;
import com.snoopdogg.bonfire.tasks.jadinkos.CutRoots;
import com.snoopdogg.bonfire.tasks.jadinkos.LightFirepit;
import com.snoopdogg.bonfire.tasks.jadinkos.StartFirepit;
import com.snoopdogg.bonfire.util.MessageHandler;
import com.snoopdogg.bonfire.util.PaintHandler;
import com.snoopdogg.bonfire.variables.Constants;
import com.snoopdogg.framework.Task;

@Script.Manifest(name="BasicBonfire", description = "Burns logs up to elder.", properties = "topic=1228656;hidden=false;client=6;")
public class BonfireMain extends PollingScript<ClientContext> implements PaintListener, MessageListener, MouseListener {
	
	private final ArrayList<Task> TASKS = new ArrayList<Task>();
	
	private final Constants CONSTS = new Constants(ctx);
	private final PaintHandler PAINTHANDLER = new PaintHandler(this,ctx,CONSTS);
	private final MessageHandler MESSAGEHANDLER = new MessageHandler(ctx,CONSTS);
	
	private GUI gui;

	public final int START_FLETCHING_XP = ctx.skills.experience(Skills.FLETCHING);
	public final int START_FIREMAKING_XP = ctx.skills.experience(Skills.FIREMAKING);
	public final int START_FIREMAKING_LEVEL = ctx.skills.level(Skills.FIREMAKING);
	public final int START_FLETCHING_LEVEL = ctx.skills.level(Skills.FLETCHING);
	public final long starttime = System.currentTimeMillis();
	public boolean paint_visible = true;
	
	public String status;
	
	public final ClientContext context = ctx;
    
    @Override
    public void start() {
    	gui = new GUI(this,CONSTS);
    }

    @Override
    public void poll() {
    	if(gui.finished && ctx.game.clientState() == ctx.game.INDEX_MAP_LOADED) {
        	for(Task t: TASKS) {
        		if(t.activate()) {
        			t.execute();
        		}
        	}
    	}
    }
	@Override
	public void repaint(Graphics g) {
		if(paint_visible) {
			PAINTHANDLER.paint(g);
		}
	}

	@Override
	public void messaged(MessageEvent message) {
		MESSAGEHANDLER.handleMessage(message);
	}
	
	public void addFiremakingTasks() {
		TASKS.add(new ReorientCamera(ctx));
		TASKS.add(new ClosePollOption(ctx,this));
		TASKS.add(new CloseChest(ctx,this));
		TASKS.add(new Bank(ctx,CONSTS,this));
    	TASKS.add(new Burn(ctx,CONSTS,this));
    	TASKS.add(new GetReward(ctx,CONSTS,this));
	}

	public void addJadinkoTasks() {
		TASKS.add(new ClosePollOption(ctx,this));
		TASKS.add(new BurnRoots(ctx,this));
		TASKS.add(new LightFirepit(ctx,this));
		TASKS.add(new StartFirepit(ctx,this));
		TASKS.add(new ReorientCamera(ctx));
		TASKS.add(new CollectRoots(ctx,this));
		TASKS.add(new CutRoots(ctx,this));
	}

	@Override
	public void mouseClicked(MouseEvent ev) {
		if(PAINTHANDLER.PAINT_RECT.contains(ev.getPoint())) {
			if(paint_visible) {
				paint_visible = false;
			} else {
				paint_visible = true;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}