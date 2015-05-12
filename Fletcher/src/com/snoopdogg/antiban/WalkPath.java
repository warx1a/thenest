package com.snoopdogg.antiban;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.TilePath;

public class WalkPath extends ClientAccessor {
	
	private final List<Tile> tilepath;

	public WalkPath(ClientContext arg0, final TilePath path) {
		super(arg0);
		this.tilepath = Arrays.asList(path.array());
	}
	
	public void walk() {
		final GameObject obj = ctx.objects.select().id(0).nearest().poll();
		final Area tree_area = new Area(new Tile(0,0), new Tile(1,1));
		final Tile randtile = tree_area.getCentralTile();
		final TilePath path = ctx.movement.newTilePath(randtile.tile());
		final Iterator<Tile> iter =  tilepath.iterator();
		while(iter.hasNext()) {
			final Tile next_tile = iter.next();
			if(ctx.movement.step(next_tile)) {
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return !ctx.players.local().inMotion();
					}
				});
			}
		}
	}

}
