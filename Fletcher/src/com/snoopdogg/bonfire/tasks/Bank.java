package com.snoopdogg.bonfire.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.bonfire.BonfireMain;
import com.snoopdogg.bonfire.variables.Constants;
import com.snoopdogg.framework.Task;

public class Bank extends Task {
	
	private final Constants CONSTS;
	private final BonfireMain MAIN;
	
	public Bank(ClientContext arg0, final Constants consts, final BonfireMain MAIN) {
		super(arg0);
		this.CONSTS = consts;
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		return (ctx.backpack.select().id(CONSTS.fletching.getId()).isEmpty() || ctx.bank.opened()) && !ctx.npcs.select().id(CONSTS.FIRE_SPIRIT_ID).nearest().poll().valid();
	}

	@Override
	public void execute() {
		MAIN.status = "Banking";
		if(ctx.bank.inViewport()) {
			ctx.camera.turnTo(ctx.bank.nearest());
			if(ctx.bank.open()) {
				if(ctx.bank.depositInventory()) {
					if(ctx.bank.withdraw(CONSTS.fletching.getId(), 28)) {
						ctx.bank.close();
					}
				}
			}
		} else {
			ctx.movement.step(ctx.bank.nearest());
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return !ctx.players.local().inMotion();
				}
			});
		}
	}
}
