package com.snoopdogg.antiban.breakhandler;

public enum TimeUnit {
	
	MILLISECOND(1),
	SECOND(1000),
	MINUTE(60000),
	HOUR(3600000);
	
	private final long milliseconds;
	
	TimeUnit(final long milliseconds) {
		this.milliseconds = milliseconds;
	}
	
	public synchronized long getMilliseconds() {
		return milliseconds;
	}
}
