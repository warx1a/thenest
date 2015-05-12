package com.snoopdogg.fletchandfm.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.fletchandfm.variables.Constants;
import com.snoopdogg.framework.Task;

public class Bank extends Task {
	
	private final Constants CONSTS;
	
	public Bank(ClientContext arg0, final Constants consts) {
		super(arg0);
		this.CONSTS = consts;
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().id(CONSTS.fletching.getId()).isEmpty() || ctx.bank.opened();
	}

	@Override
	public void execute() {
		if(ctx.bank.inViewport()) {
			ctx.camera.turnTo(ctx.bank.nearest());
			if(ctx.bank.open()) {
				if(ctx.bank.depositInventory()) {
					if(ctx.bank.withdraw(CONSTS.fletching.getId(), 28)) {
						if(ctx.bank.close()) {
							return;
						}
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