package com.snoopdogg.antiban.breakhandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

public class BreakHandler extends ClientAccessor {
	
	private final ArrayList<Long> breaktimes;
	private final static BreakHandler handler = null;
	private Long updatetime;

	private BreakHandler(ClientContext arg0) {
		super(arg0);
		breaktimes = (ArrayList<Long>) Collections.synchronizedList(new ArrayList<Long>());
	}
	
	public static synchronized BreakHandler getBreakHandler(final ClientContext context) {
		if(handler == null) {
			return new BreakHandler(context);
		} else {
			return handler;
		}
	}
	
	// parses in format HH:MM:SS
	public void addBreak(final String _break) {
		final SimpleDateFormat format = new SimpleDateFormat("HH:MM:SS");
		Date date = null;
		try {
			date = format.parse(_break);
		} catch (ParseException e) {
			date = null;
		} finally {
			if(date != null) {
				synchronized(breaktimes) {
					breaktimes.add(System.currentTimeMillis() + date.getTime());
				}
			}
		}
	}
	
	
	public synchronized void addBreak(final long timestamp) {
		if(timestamp > System.currentTimeMillis()) {
			synchronized(breaktimes) {
				breaktimes.add(timestamp);
			}
		}
	}
	
	public synchronized ArrayList<Long> getBreaklist() {
		synchronized(breaktimes) {
			return breaktimes;
		}
	}
	
	public synchronized void updateEvery(final long time,final TimeUnit unit) {
		synchronized(updatetime) {
			updatetime = time * unit.getMilliseconds();
		}
	}
	
	public void start() {
		final BreakWatcher watcher = BreakWatcher.getBreakWatcher(breaktimes);
		final Thread thread = new Thread(watcher);
		thread.start();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
