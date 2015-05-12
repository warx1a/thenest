package com.snoopdogg.blackjack.variables;

public enum Locations {
	
	NONE(new String[]{"None"}, new Targets[]{Targets.NONE}),
	THIEVES_GUILD(new String[]{"None","Blackjack","Pickpocket"}, new Targets[]{Targets.NONE,Targets.KOSHING_VOLUNTEER, Targets.PICKPOCKET_VOLUNTEER}),
	DRAYNOR_VILLAGE(new String[]{"None","Pickpocket"}, new Targets[]{Targets.NONE,Targets.MASTER_GARDENER});
	
	private String[] METHODS;
	private Targets[] TARGETS;
	
	Locations(final String[] METHODS, final Targets[] TARGETS) {
		this.METHODS = METHODS;
		this.TARGETS = TARGETS;
	}
	
	public String[] getMethods() {
		return METHODS;
	}
	
	public Targets[] getTargets() {
		return TARGETS;
	}
	
	@Override
	public String toString() {
			return Character.toUpperCase(this.name().charAt(0)) + this.name().substring(1).toLowerCase().replaceAll("_", " ");
	}

}
