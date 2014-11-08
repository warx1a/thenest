package com.snoopdogg.variables;

public enum Items {
	
	LOGS(1511,"Shortbow",50,2,"Stock",9440,3,"Shieldbow",48,4),
	OAK(1521,"Shortbow",54,1,"Stock",9442,2,"Shieldbow",56,3),
	WILLOW(1519,"Shortbow",58,1,"Stock",9444,2,"Shieldbow",58,3),
	MAPLE(1517,"Shortbow",60,1,"Stock",9448,2,"Shieldbow",62,3),
	YEW(1515,"Shortbow",68,1,"Stock",9452,2,"Shieldbow",66,3);
	
	private final int ID;
	private final String SHORTBOW;
	private final int SHORTBOW_ID;
	private final int SHORTBOW_INDEX;
	private final String STOCK;
	private final int STOCK_ID;
	private final int STOCK_INDEX;
	private final String LONGBOW;
	private final int LONGBOW_ID;
	private final int LONGBOW_INDEX;
	
	Items(final int ID, final String SHORTBOW, final int SHORTBOW_ID,final int SHORTBOW_INDEX,
			final String STOCK, final int STOCK_ID,final int STOCK_INDEX,
			final String LONGBOW,final int LONGBOW_ID,final int LONGBOW_INDEX) {
		this.ID = ID;
		this.SHORTBOW = SHORTBOW;
		this.SHORTBOW_ID = SHORTBOW_ID;
		this.SHORTBOW_INDEX = SHORTBOW_INDEX;
		this.STOCK = STOCK;
		this.STOCK_ID = STOCK_ID;
		this.STOCK_INDEX = STOCK_INDEX;
		this.LONGBOW = LONGBOW;
		this.LONGBOW_ID = LONGBOW_ID;
		this.LONGBOW_INDEX = LONGBOW_INDEX;
		
	}
	
	public int getId() {
		return ID;
	}
	
	public String getShortbowName() {
		return SHORTBOW;
	}
	
	public int getShortbowId() {
		return SHORTBOW_ID;
	}
	
	public int getShortbowIndex() {
		return SHORTBOW_INDEX;
	}
	
	public String getStockName() {
		return STOCK;
	}
	
	public int getStockId() {
		return STOCK_ID;
	}
	
	public int getStockIndex() {
		return STOCK_INDEX;
	}
	
	public String getLongbowName() {
		return LONGBOW;
	}
	
	public int getLongbowId() {
		return LONGBOW_ID;
	}
	
	public int getLongbowIndex() {
		return LONGBOW_INDEX;
	}
	
	@Override
	public String toString() {
		return Character.toUpperCase(this.name().charAt(0)) + this.name().substring(1).toLowerCase();
	}

}
