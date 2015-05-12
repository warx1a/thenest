package com.snoopdogg.snippets.lobby;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

public class Lobby extends ClientAccessor {
	
	// for text color for worlds
	private final int FREE_WORLD_TEXT_COLOR = 16763653;
	private final int MEMBER_WORLD_TEXT_COLOR = 16777215;
	
	// for lootsharing
	private final int CHECK_MARK = 18694;
	private final int DASH_MARK = 23768;

	public Lobby(ClientContext arg0) {
		super(arg0);
	}
	
	// get the first favorite world
	public String getFirstWorld() {
		return ctx.widgets.component(906, 509).text();
	}
	
	// get the second favorite world
	public String getSecondWorld() {
		return ctx.widgets.component(906, 501).text();
	}
	
	// get the third favorite world
	public String getThirdWorld() {
		return ctx.widgets.component(906, 536).text();
	}
	
	// get the currently selected world
	public Integer getCurrentlySelectedWorld() {
		final Component selected = ctx.widgets.component(906, 511);
		if(selected.valid() && selected.visible()) {
			return Integer.parseInt(selected.text());
		}
		return -1;
	}
	
	// returns favorite worlds for user
	public List<Integer> getWorldFavorites() {
		final List<Integer> favorites = new ArrayList<Integer>();
		// rows for world favorites
		final Component first_row = ctx.widgets.component(910, 13).component(7);
		final Component second_row = ctx.widgets.component(910, 14).component(6);
		final Component third_row = ctx.widgets.component(910, 15).component(6);
		if(first_row.valid() && first_row.visible()) {
			favorites.add(Integer.parseInt(first_row.text()));
		}
		if(second_row.valid() && second_row.visible()) {
			favorites.add(Integer.parseInt(second_row.text()));
		}
		if(third_row.valid() && third_row.visible()) {
			favorites.add(Integer.parseInt(third_row.text()));
		}
		return favorites;
	}
	
	// get list of available free worlds
	public List<Integer> getFreeWorlds() {
		final List<Integer> freeWorlds = new ArrayList<Integer>();
		final Component[] worldNumbers = ctx.widgets.component(910, 75).components();
		for(final Component world: worldNumbers) {
			if(world.textColor() == FREE_WORLD_TEXT_COLOR) {
				freeWorlds.add(Integer.parseInt(world.text()));
			}
		}
		return freeWorlds;
	}
	
	// get list of available member worlds
	public List<Integer> getMemberWorlds() {
		final List<Integer> memberWorlds = new ArrayList<Integer>();
		final Component[] worldNumbers = ctx.widgets.component(910, 75).components();
		for(final Component world: worldNumbers) {
			if(world.textColor() == MEMBER_WORLD_TEXT_COLOR) {
				memberWorlds.add(Integer.parseInt(world.text()));
			}
		}
		return memberWorlds;
	}
	
	// get world population
	public Integer getPlayers(final int worldNumber) {
		final Component[] worldNumbers = ctx.widgets.component(910, 75).components();
		for(final Component world: worldNumbers) {
			if(Integer.parseInt(world.text()) == worldNumber) {
				final int row = world.index();
				final Component population = ctx.widgets.component(910, 77).component(row);
				return Integer.parseInt(population.text());
			}
		}
		return null;
	}
	
	// return the world activity
	public String getWorldActivity(final int worldNumber) {
		final Component[] worldNumbers = ctx.widgets.component(910, 75).components();
		for(final Component world: worldNumbers) {
			if(Integer.parseInt(world.text()) == worldNumber) {
				final int row = world.index();
				final Component activity = ctx.widgets.component(910, 78).component(row);
				return activity.text();
			}
		}
		return null;
	}
	
	
	// return if the world has lootshare enabled
	public boolean canLootshare(final int worldNumber) {
		final Component[] worldNumbers = ctx.widgets.component(910, 75).components();
		for(final Component world: worldNumbers) {
			if(Integer.parseInt(world.text()) == worldNumber) {
				final int row = world.index();
				final Component activity = ctx.widgets.component(910, 81).component(row);
				return activity.textureId() == CHECK_MARK;
			}
		}
		return false;
	}
	
	// get the worlds ping
	public int getPing(final int worldNumber) {
		final Component[] worldNumbers = ctx.widgets.component(910, 75).components();
		for(final Component world: worldNumbers) {
			if(Integer.parseInt(world.text()) == worldNumber) {
				final int row = world.index();
				final Component activity = ctx.widgets.component(910, 82).component(row);
				return Integer.parseInt(activity.text());
			}
		}
		return -1;
	}
	
	// can add others in but not sure why you would need for a script, opens jagex message centre
	public boolean openJagexMessages() {
		final Component messageButton = ctx.widgets.component(906, 570);
		if(messageButton.valid() && messageButton.visible()) {
			if(messageButton.interact("Select")) {
				return true;
			}
		}
		return false;
	}

}
