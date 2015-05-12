package com.snoopdogg.thiever.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

import com.snoopdogg.blackjack.BlackjackMain;
import com.snoopdogg.blackjack.variables.Locations;
import com.snoopdogg.blackjack.variables.Variables;
import com.snoopdogg.framework.Task;

public class GotoBank extends Task {
	
	private final Variables VARS;
	private final BlackjackMain MAIN;

	public GotoBank(ClientContext arg0,final BlackjackMain MAIN, final Variables VARS) {
		super(arg0);
		this.VARS = VARS;
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		final int bit = ctx.varpbits.varpbit(659,0x23284651);
		return (ctx.players.local().inCombat() || getHealthPercent() <= 0.5 || ctx.backpack.select().count() == 28) && (bit != 589841985 && ctx.players.local().animation() != 424);
	}

	@Override
	public void execute() {
		MAIN.status = "Going to bank";
		if(VARS.location.equals(Locations.DRAYNOR_VILLAGE)) {
			ctx.movement.step(ctx.bank.nearest());
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return ctx.players.local().idle() && getHealthPercent() == 1.0;
				}
			});
			if(ctx.backpack.count() == 28) {
				if(ctx.bank.open()) {
					if(ctx.bank.depositInventory()) {
						ctx.bank.close();
					}
				}
			}
		}
	}
	
	
	public double getHealthPercent() {
		final Component HEALTHBAR = ctx.widgets.component(1430, 4).component(7);
		if(HEALTHBAR.valid() && HEALTHBAR.visible()) {
			final String HEALTHTEXT = HEALTHBAR.text();
			final String[] CALCS = HEALTHTEXT.split("/");
			final double PERCENT = Double.parseDouble(CALCS[0]) / Double.parseDouble(CALCS[1]);
			return PERCENT;
		} else {
			return 1;
		}
	}

}
