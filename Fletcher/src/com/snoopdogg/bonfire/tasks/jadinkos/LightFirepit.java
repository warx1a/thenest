package com.snoopdogg.bonfire.tasks.jadinkos;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

import com.snoopdogg.bonfire.BonfireMain;
import com.snoopdogg.framework.Task;

public class LightFirepit extends Task {
	
	private final BonfireMain MAIN;
	private final int LOGGED_PIT = 12285;

	public LightFirepit(ClientContext arg0, BonfireMain MAIN) {
		super(arg0);
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		return ctx.players.local().idle() && ctx.objects.select().id(LOGGED_PIT).poll().valid();
	}

	@Override
	public void execute() {
		MAIN.status = "Lighting firepit";
		final GameObject READY_FIREPIT = ctx.objects.id(LOGGED_PIT).nearest().poll();
		if(READY_FIREPIT.inViewport()) {
			if(READY_FIREPIT.interact("Light")) {
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return ctx.players.local().idle();
					}
				});
			}
		} else {
			ctx.movement.step(READY_FIREPIT);
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return ctx.players.local().idle();
				}
			});
		}
	}

}
