package com.snoopdogg.dungeoneering.variables;

public enum Doors {
	
	
	GUARDIAN(50346);
	
	private final int[] ids;
	
	Doors(final int... ids) {
		this.ids = ids;
	}
	
	public int[] getIds() {
		return ids;
	}

}
