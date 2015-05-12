package com.snoopdogg.bonfire.tasks.jadinkos;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

import com.snoopdogg.bonfire.BonfireMain;
import com.snoopdogg.framework.Task;

public class StartFirepit extends Task {
	
	private final BonfireMain MAIN;
	private final int DRY_PATCH = 12284;
	private final int BURNING_PIT = 12286;

	public StartFirepit(ClientContext arg0, BonfireMain MAIN) {
		super(arg0);
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().count() == 28 && ctx.objects.select().id(DRY_PATCH).poll().valid() 
				&& ctx.players.local().idle() && !ctx.objects.select().id(BURNING_PIT).poll().valid();
	}

	@Override
	public void execute() {
		MAIN.status = "Adding root to firepit";
		final GameObject FIREPIT = ctx.objects.id(DRY_PATCH).nearest().poll();
		if(FIREPIT.inViewport()) {
			if(FIREPIT.interact("Add-root")) {
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return ctx.players.local().idle();
					}
				});
			}
		} else {
			ctx.movement.step(FIREPIT);
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return ctx.players.local().idle();
				}
			});
		}
	}

}
