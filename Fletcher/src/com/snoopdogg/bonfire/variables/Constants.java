package com.snoopdogg.bonfire.variables;

import java.util.ArrayList;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

public class Constants extends ClientAccessor {
	
	public Constants(ClientContext arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public Items fletching;

	public String method;
	
	public int bonfire_fire = 0;
	public int regular_fire = 0;
	public int rewards_gathered = 0;
	public int costs_per_log = 0;

	public final ArrayList<Tile> ACCEPTABLE_TILES = new ArrayList<Tile>();
	public final ArrayList<Tile> FIRE_TILES = new ArrayList<Tile>();
	
	public final int[] FIRE_ID = new int[]{70755,70757,70758,70761,70764,70765,87548};
	public final int BURN_STANCE = 16701;
	public final int BURN_ANIMATION = 24886;
	public final int FIRE_SPIRIT_ID = 15451;

    public final Component KNIFE_CLICK = ctx.widgets.component(1179,33);
    public final Component FLETCH_BUTTON = ctx.widgets.component(1370, 20);
    public final Component CANCEL_BUTTON = ctx.widgets.component(1251, 19);
    public final Component BURN_BUTTON = ctx.widgets.component(1179, 17);
    public final Component ADD_TO_FIRE = ctx.widgets.component(1179, 34);
	
	public final Component FIRST_INDEX = ctx.widgets.component(1371, 44).component(1);
	public final Component SECOND_INDEX = ctx.widgets.component(1371, 44).component(4);
	public final Component THIRD_INDEX = ctx.widgets.component(1371, 44).component(8);
	public final Component FOURTH_INDEX = ctx.widgets.component(1371, 44).component(12);

}
