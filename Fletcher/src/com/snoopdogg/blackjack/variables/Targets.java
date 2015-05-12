package com.snoopdogg.blackjack.variables;

public enum Targets {
	
	NONE(new int[]{-1},""),
	KOSHING_VOLUNTEER(new int[]{11291,11293,11289,11297},"Blackjack"),
	PICKPOCKET_VOLUNTEER(new int[]{11285,11283,11287,11281},"Pickpocket"),
	MASTER_GARDENER(new int[]{2234,3299},"Pickpocket");
	
	private int[] IDS;
	private String METHOD;
	
	Targets(final int[] IDS,final String METHOD) {
		this.IDS = IDS;
		this.METHOD = METHOD;
	}
	
	public int[] getIds() {
		return IDS;
	}
	
	public String getMethod() {
		return METHOD;
	}
	
	@Override
	public String toString() {
			return Character.toUpperCase(this.name().charAt(0)) + this.name().substring(1).toLowerCase().replaceAll("_", " ");
	}

}
