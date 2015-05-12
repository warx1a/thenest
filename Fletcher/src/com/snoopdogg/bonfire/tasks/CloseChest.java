package com.snoopdogg.bonfire.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

import com.snoopdogg.bonfire.BonfireMain;
import com.snoopdogg.framework.Task;

public class CloseChest extends Task {
	
	private final BonfireMain MAIN;
	private final Component TREASURE_CLOSE = ctx.widgets.component(1252, 5);

	public CloseChest(ClientContext arg0, BonfireMain MAIN) {
		super(arg0);
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		return TREASURE_CLOSE.valid() && TREASURE_CLOSE.visible();
	}

	@Override
	public void execute() {
		MAIN.status = "Burning logs";
		TREASURE_CLOSE.interact("Select");
	}

}
