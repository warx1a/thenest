package com.snoopdogg.bonfire.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

import com.snoopdogg.bonfire.BonfireMain;
import com.snoopdogg.framework.Task;

public class ClosePollOption extends Task {
	
	private final BonfireMain MAIN;
	final Component POLL_OPTION = ctx.widgets.component(669, 1);

	public ClosePollOption(ClientContext arg0, BonfireMain MAIN) {
		super(arg0);
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		return POLL_OPTION.valid() && POLL_OPTION.visible();
	}

	@Override
	public void execute() {
		MAIN.status = "Closing the poll option";
		POLL_OPTION.interact("Close");
	}

}
