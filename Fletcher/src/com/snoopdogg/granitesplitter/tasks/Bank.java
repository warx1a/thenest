package com.snoopdogg.granitesplitter.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Locatable;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.framework.Task;
import com.snoopdogg.granitesplitter.SplitterMain;

public class Bank extends Task {
	
	private final SplitterMain MAIN;

	public Bank(ClientContext arg0, SplitterMain MAIN) {
		super(arg0);
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().id(MAIN.GRANITE_2KG_ID).count() == 0;
	}

	@Override
	public void execute() {
		MAIN.status = "Banking";
		if(ctx.bank.inViewport()) {
			ctx.camera.turnTo(ctx.bank.nearest());
			if(ctx.bank.open()) {
				if(ctx.bank.depositInventory()) {
					if(ctx.bank.withdraw(MAIN.GRANITE_2KG_ID, 7)) {
						ctx.bank.close();
					}
				}
			}
		} else {
			MAIN.status = "Moving towards bank";
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
