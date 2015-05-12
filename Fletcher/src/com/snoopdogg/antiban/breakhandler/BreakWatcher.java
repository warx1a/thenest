package com.snoopdogg.antiban.breakhandler;

import java.util.ArrayList;

public class BreakWatcher implements Runnable {
	
	private static BreakWatcher watcher;
	private final ArrayList<Long> breaktimes;
	
	private BreakWatcher(ArrayList<Long> breaktimes){
		this.breaktimes = breaktimes;
	};
	
	public static synchronized BreakWatcher getBreakWatcher(ArrayList<Long> breaktimes) {
		if(watcher == null) {
			return new BreakWatcher(breaktimes);
		} else {
			return watcher;
		}
	}
	
	public synchronized void signalBreak() {
		System.out.println("breaking");
	}

	@Override
	public void run() {
		while(breaktimes.size() > 0) {
			for(Long time: breaktimes) {
				if(System.currentTimeMillis() >= time) {
					signalBreak();
				}
			}
		}
	}

}
