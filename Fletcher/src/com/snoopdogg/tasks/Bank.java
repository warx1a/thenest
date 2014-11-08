package com.snoopdogg.tasks;

import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.framework.Task;
import com.snoopdogg.variables.Constants;

public class Bank extends Task {
	
	private final Constants consts;

	public Bank(ClientContext arg0, final Constants consts) {
		super(arg0);
		this.consts = consts;
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().id(consts.fletching.getId()).isEmpty();
	}

	@Override
	public void execute() {
		if(ctx.bank.nearest().tile().distanceTo(ctx.players.local().tile()) <= 10) {
			if(ctx.bank.open()) {
				if(ctx.bank.depositInventory()) {
					if(ctx.bank.withdraw(consts.fletching.getId(), 28)) {
						ctx.bank.close();
					}
				}
			}
		}
	}

}
