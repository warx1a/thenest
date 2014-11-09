package com.snoopdogg.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.framework.Task;
import com.snoopdogg.variables.Constants;

public class Bank extends Task {
	
	private final Constants CONSTS;
	
	public Bank(ClientContext arg0, final Constants consts) {
		super(arg0);
		this.CONSTS = consts;
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().id(CONSTS.fletching.getId()).isEmpty();
	}

	@Override
	public void execute() {
		if(ctx.bank.inViewport()) {
			ctx.camera.turnTo(ctx.bank.nearest());
			if(ctx.bank.open()) {
				if(ctx.bank.depositInventory()) {
					if(ctx.bank.withdraw(CONSTS.fletching.getId(), 28)) {
						ctx.bank.close();
					} else {
						ctx.controller.stop();
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
