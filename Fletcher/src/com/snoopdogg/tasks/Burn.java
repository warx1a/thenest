package com.snoopdogg.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Item;

import com.snoopdogg.framework.Task;
import com.snoopdogg.variables.Constants;

public class Burn extends Task {
	
	private final Constants CONSTS;

	public Burn(ClientContext arg0,final Constants CONSTS) {
		super(arg0);
		this.CONSTS = CONSTS;
	}

	@Override
	public boolean activate() {
		// TODO Auto-generated method stub
		return ctx.backpack.select().id(CONSTS.fletching.getId()).count() >= 1
				&& CONSTS.method.equals("Burn")
				// if we arent aleady burning
				&& ctx.players.local().animation() != CONSTS.BURN_ANIMATION
				&& ctx.players.local().stance() != CONSTS.BURN_STANCE;
	}

	@Override
	public void execute() {
		if(!CONSTS.ACCEPTABLE_TILES.contains(ctx.players.local().tile()) && CONSTS.ACCEPTABLE_TILES.size() > 0) {
			ctx.movement.step(CONSTS.ACCEPTABLE_TILES.get(Random.nextInt(0, CONSTS.ACCEPTABLE_TILES.size() - 1)));
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return !ctx.players.local().inMotion();
				}
			});
		}
		// if a fires already lit in the area
		if(fireAlreadyLit()) {
			final GameObject fire = ctx.objects.select().id(CONSTS.FIRE_ID).nearest().poll();
			ctx.camera.turnTo(fire);
			if(fire.inViewport()) {
				if(fire.interact("Use")) {
					Condition.wait(new Callable<Boolean>() {
						@Override
						public Boolean call() throws Exception {
							return CONSTS.ADD_TO_FIRE.visible() && CONSTS.ADD_TO_FIRE.valid();
						}
					});
					if(CONSTS.ADD_TO_FIRE.visible() && CONSTS.ADD_TO_FIRE.valid()) {
						if(CONSTS.ADD_TO_FIRE.click()) {
							Condition.wait(new Callable<Boolean>() {
								@Override
								public Boolean call() throws Exception {
									return ctx.backpack.select().id(CONSTS.fletching.getId()).count() == 0;
								}
							});
						}
					}
				}
			}
		} else {
			// we need to light our own fire
			final Item LOG = ctx.backpack.select().id(CONSTS.fletching.getId()).poll();
			if(LOG.interact("Craft")) {
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return CONSTS.BURN_BUTTON.valid() && CONSTS.BURN_BUTTON.visible();
					}
				},200,3);
				if(CONSTS.BURN_BUTTON.valid() && CONSTS.BURN_BUTTON.visible()) {
					if(CONSTS.BURN_BUTTON.click()) {
						Condition.wait(new Callable<Boolean>() {
							@Override
							public Boolean call() throws Exception {
								return ctx.backpack.select().id(CONSTS.fletching.getId()).count() == 0;
							}
						});
					}
				}
			}
		}
	}
	
	public boolean fireAlreadyLit() {
		if(ctx.objects.select().id(CONSTS.FIRE_ID).nearest().poll().tile().distanceTo(ctx.players.local().tile()) <= 10) {
			return true;
		}
		return false;
	}

}
