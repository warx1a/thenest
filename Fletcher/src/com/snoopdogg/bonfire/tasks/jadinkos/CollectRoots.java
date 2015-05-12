package com.snoopdogg.bonfire.tasks.jadinkos;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

import com.snoopdogg.bonfire.BonfireMain;
import com.snoopdogg.framework.Task;

public class CollectRoots extends Task {
	
	private final BonfireMain MAIN;
	private final int CUT_CURLY_ROOT = 12279;
	private final int[] bounds = {-20, 20, -2000, -1200, -20, 20};

	public CollectRoots(ClientContext arg0, final BonfireMain MAIN) {
		super(arg0);
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		final GameObject CUT_ROOT = ctx.objects.select().id(CUT_CURLY_ROOT).poll();
		return ctx.backpack.select().count() < 28 && CUT_ROOT.valid() && ctx.players.local().idle();
	}

	@Override
	public void execute() {
		MAIN.status = "Collecting roots";
		final GameObject CUT_ROOT = ctx.objects.select().id(CUT_CURLY_ROOT).nearest().poll();
		if(CUT_ROOT.inViewport()) {
			GameObject.doSetBounds(bounds);
			if(CUT_ROOT.interact("Collect-root")) {
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return ctx.players.local().idle();
					}
				});
			}
		} else {
			ctx.movement.step(CUT_ROOT);
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return ctx.players.local().idle();
				}
			});
		}
	}

}
