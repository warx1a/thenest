package com.snoopdogg.fletchandfm.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import com.snoopdogg.fletchandfm.variables.Constants;
import com.snoopdogg.framework.Task;

public class GetReward extends Task {

	private final Constants CONSTS;

	public GetReward(ClientContext arg0, final Constants CONSTS) {
		super(arg0);
		this.CONSTS = CONSTS;
	}

	@Override
	public boolean activate() {
		return ctx.npcs.select().id(CONSTS.FIRE_SPIRIT_ID).nearest().poll().valid();
	}

	@Override
	public void execute() {
		final Npc SPIRIT = ctx.npcs.id(CONSTS.FIRE_SPIRIT_ID).nearest().poll();
		if(SPIRIT.valid() && SPIRIT.inViewport()) {
			SPIRIT.interact("Collect-reward");
		}
	}

}
