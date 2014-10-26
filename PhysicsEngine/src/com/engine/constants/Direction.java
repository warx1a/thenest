package com.engine.constants;

public enum Direction {
	
	X("X"),Y("Y"),Z("Z");
	
	private final String direction;
	
	Direction(final String direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		switch(direction) {
		case "X": return Direction.X;
		case "Y": return Direction.Y;
		case "Z": return Direction.Z;
		default: return null;	
		}
	}

}
