package com.snoopdogg.variables;

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
	public int cut = 0;
	
	public final ArrayList<Tile> UNACCEPTABLE_TILES = new ArrayList<Tile>();
	public final ArrayList<Tile> ACCEPTABLE_TILES = new ArrayList<Tile>();
	
	public final int ARROW_SHAFT_ID = 52;
	public final int[] FIRE_ID = new int[]{70755,70757,70761,70764};
	public final int BURN_STANCE = 16701;

    public final Component KNIFE_CLICK = ctx.widgets.component(1179,33);
    public final Component FLETCH_BUTTON = ctx.widgets.component(1370, 20);
    public final Component CANCEL_BUTTON = ctx.widgets.component(1251, 19);
    public final Component BURN_BUTTON = ctx.widgets.component(1179, 16);
    public final Component ADD_TO_FIRE = ctx.widgets.component(1179, 33);
	
	public final Component FIRST_INDEX = ctx.widgets.component(1371, 44).component(1);
	public final Component SECOND_INDEX = ctx.widgets.component(1371, 44).component(4);
	public final Component THIRD_INDEX = ctx.widgets.component(1371, 44).component(8);
	public final Component FOURTH_INDEX = ctx.widgets.component(1371, 44).component(12);

}
