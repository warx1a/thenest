package com.snoopdogg.snippets.framework;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class TaskChain<T extends Task<?>> {
	
	private final ArrayList<AtomicReference<T>> tasks;
	
	public TaskChain() {
		tasks = new ArrayList<AtomicReference<T>>();
	}
	
	public synchronized ArrayList<AtomicReference<T>> tasklist() {
		synchronized(tasks) {
			return tasks;
		}
	}

}
