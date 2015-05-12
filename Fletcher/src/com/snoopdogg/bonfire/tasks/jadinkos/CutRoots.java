package com.snoopdogg.bonfire.tasks.jadinkos;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

import com.snoopdogg.bonfire.BonfireMain;
import com.snoopdogg.framework.Task;

public class CutRoots extends Task {
	
	private final BonfireMain MAIN;
	private final int CURLY_ROOT_ID = 12274;
	private final int CUT_CURLY_ROOT = 12279;
	private final int[] bounds = {-20, 20, -2000, -1200, -20, 20};

	public CutRoots(ClientContext arg0,final BonfireMain MAIN) {
		super(arg0);
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().count() < 28 && ctx.players.local().idle() && !ctx.objects.select().id(CUT_CURLY_ROOT).nearest().poll().valid();
	}

	@Override
	public void execute() {
		GameObject.doSetBounds(bounds);
		MAIN.status = "Cutting roots";
		final GameObject ROOT = ctx.objects.select().id(CURLY_ROOT_ID).nearest().poll();
		if(ROOT.inViewport()) {
			if(ROOT.interact("Chop")) {
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return ctx.players.local().idle();
					}
				});
			}
		} else {
			ctx.movement.step(ROOT);
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return ctx.players.local().idle();
				}
			});
		}
	}

}
