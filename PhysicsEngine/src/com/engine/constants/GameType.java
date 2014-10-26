package com.engine.constants;

public enum GameType {
	
	FPS("FPS"),
	MMORPG("MMORPG"),
	SANDBOX("SANDBOX"),
	OTHER("OTHER");
	
	private String gametype;
	
	GameType(final String gametype) {
		this.gametype = gametype;
	}
	
	public GameType getGameType() {
		switch(gametype) {
		case("FPS"): return GameType.FPS;
		case("MMORPG"): return GameType.MMORPG;
		case("SANDBOX"): return GameType.SANDBOX;
		default: return GameType.OTHER;
		}
	}

}
