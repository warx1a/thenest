package com.snoopdogg;

import java.util.ArrayList;

import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.Script;

import com.snoopdogg.tasks.Bank;
import com.snoopdogg.tasks.Fletch;
import com.snoopdogg.variables.Constants;
import com.snoopdogg.framework.Task;

@Script.Manifest(name="BasicFletcher", description = "fletcher", properties = "client=6;")
public class FletchingMain extends PollingScript<ClientContext> {
	
    private final Constants CONSTS = new Constants(ctx);
	
	private final ArrayList<Task> TASKS = new ArrayList<Task>();
	
	private GUI gui;
    
    @Override
    public void start() {
    	gui = new GUI(CONSTS);
    	TASKS.add(new Fletch(ctx,CONSTS));
    	TASKS.add(new Bank(ctx,CONSTS));
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

}
