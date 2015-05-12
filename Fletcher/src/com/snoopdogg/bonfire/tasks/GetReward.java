package com.snoopdogg.bonfire.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import com.snoopdogg.bonfire.BonfireMain;
import com.snoopdogg.bonfire.variables.Constants;
import com.snoopdogg.framework.Task;

public class GetReward extends Task {

	private final Constants CONSTS;
	private final BonfireMain MAIN;

	public GetReward(ClientContext arg0, final Constants CONSTS, final BonfireMain MAIN) {
		super(arg0);
		this.CONSTS = CONSTS;
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		return ctx.npcs.select().id(CONSTS.FIRE_SPIRIT_ID).nearest().poll().valid() && ctx.backpack.select().count() <= 24;
	}

	@Override
	public void execute() {
		MAIN.status = "Collecting fire spirit";
		final Npc SPIRIT = ctx.npcs.select().id(CONSTS.FIRE_SPIRIT_ID).nearest().poll();
		if(SPIRIT.inViewport()) {
			if(SPIRIT.interact("Collect-reward")) {
				CONSTS.rewards_gathered += 1;
			}
		} else {
			MAIN.status = "Moving towards fire spirit";
			if(ctx.movement.step(SPIRIT)) {
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return ctx.players.local().idle();
					}
				});
			}
		}
	}

}

