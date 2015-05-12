package com.snoopdogg.granitesplitter;

import java.awt.Graphics;
import java.util.ArrayList;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.framework.Task;
import com.snoopdogg.granitesplitter.tasks.Bank;
import com.snoopdogg.granitesplitter.tasks.SplitGranite;
import com.snoopdogg.granitesplitter.util.GranitePaintHandler;

@Script.Manifest(name="BasicGraniteSplitter", description = "Splits granite at a bank", properties = "topic=1230730;hidden=false;client=6;")
public class SplitterMain extends PollingScript<ClientContext> implements PaintListener{
	
	public String status;
	
	public final int GRANITE_2KG_ID = 6981;
	
	private final ArrayList<Task> TASKS = new ArrayList<Task>();
	
	private final GranitePaintHandler PAINTHANDLER = new GranitePaintHandler(this,ctx);
	
	@Override
	public void start() {
		TASKS.add(new Bank(ctx,this));
		TASKS.add(new SplitGranite(ctx,this));
	}

	@Override
	public void poll() {
		for(Task t: TASKS) {
			if(t.activate()) {
				t.execute();
			}
		}
	}

	@Override
	public void repaint(Graphics arg0) {
		PAINTHANDLER.paint(arg0);
	}

}
