package com.snoopdogg.framework;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

public abstract class Task extends ClientAccessor {
	
	public Task(final ClientContext arg0) {
		super(arg0);
	}

	public abstract boolean activate();
	
	public abstract void execute();

}
