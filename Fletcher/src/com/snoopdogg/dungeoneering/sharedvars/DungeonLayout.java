package com.snoopdogg.dungeoneering.sharedvars;

import java.util.concurrent.CopyOnWriteArrayList;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

public class DungeonLayout extends ClientAccessor {
	
	public DungeonLayout(ClientContext arg0) {
		super(arg0);
	}

	public CopyOnWriteArrayList<GameObject> valid_tiles_entities = new CopyOnWriteArrayList<GameObject>();
	public CopyOnWriteArrayList<Tile> reachables = new CopyOnWriteArrayList<Tile>();

}
