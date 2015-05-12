package com.snoopdogg.bonfire.variables;

public enum Items {
	
	OTHER(0),
	LOGS(1511),
	OAK(1521),
	WILLOW(1519),
	MAPLE(1517),
	YEW(1515),
	MAGIC(1513),
	ELDER(29556);
	
	private final int ID;
	
	Items(final int ID) {
		this.ID = ID;
		
	}
	
	public int getId() {
		return ID;
	}
	
	@Override
	public String toString() {
		return Character.toUpperCase(this.name().charAt(0)) + this.name().substring(1).toLowerCase();
	}

}
