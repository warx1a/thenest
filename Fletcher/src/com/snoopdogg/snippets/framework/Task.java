package com.snoopdogg.snippets.framework;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;

public abstract class Task<T extends ClientContext<?>> extends ClientAccessor<T> {

	public Task(T ctx) {
		super(ctx);
	}
	
	public abstract boolean activate();
	
	public abstract void execute();
	
	public int getPriority() {
		return 0;
	}

}
