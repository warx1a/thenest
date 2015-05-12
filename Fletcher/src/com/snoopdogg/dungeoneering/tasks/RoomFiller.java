package com.snoopdogg.dungeoneering.tasks;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.MobileIdNameQuery;

import com.snoopdogg.dungeoneering.sharedvars.DungeonLayout;
import com.snoopdogg.framework.Task;

public class RoomFiller extends Task {
	
	private final DungeonLayout layout;
	private final int invalid_object = 50574;
	private final int[] corner_object = new int[]{51213,51214};

	public RoomFiller(final DungeonLayout layout, ClientContext arg0) {
		super(arg0);
		this.layout = layout;
	}

	@Override
	public boolean activate() {
		return true;
	}

	@Override
	public void execute() {
		final MobileIdNameQuery<GameObject> invalids = ctx.objects.select().id(corner_object).nearest().within(20d);
		
		for(GameObject invalid: invalids) {
			if(!layout.valid_tiles_entities.contains(invalid)) {
				layout.valid_tiles_entities.add(invalid);
			}
		}
		floodRoom(ctx.players.local().tile());
	}
	
	public void floodRoom(final Tile start_tile) {
		if(start_tile.matrix(ctx).reachable()) {
			if(!layout.reachables.contains(start_tile)) {
				layout.reachables.add(start_tile);
			}
			// move horizontal one
			floodRoom(new Tile(start_tile.x() + 1, start_tile.y(),0));
			// move vertical one
			floodRoom(new Tile(start_tile.x(), start_tile.y() + 1,0));
			// move horiz - one
			floodRoom(new Tile(start_tile.x() - 1, start_tile.y(),0));
			// move vert - one
			floodRoom(new Tile(start_tile.x(), start_tile.y() - 1,0));
		}
	}

}
