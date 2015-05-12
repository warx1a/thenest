package com.snoopdogg.thiever.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.Npc;

import com.snoopdogg.blackjack.BlackjackMain;
import com.snoopdogg.blackjack.variables.Variables;
import com.snoopdogg.framework.Task;

public class Pickpocket extends Task {
	
	private final BlackjackMain MAIN;
	private final Variables VARS;

	public Pickpocket(final ClientContext arg0,final BlackjackMain MAIN,final Variables VARS) {
		super(arg0);
		this.MAIN = MAIN;
		this.VARS = VARS;
	}

	@Override
	public boolean activate() {
		final int bit = ctx.varpbits.varpbit(659,0x23284651);
		return bit != 589841985 && ctx.players.local().animation() != 424;
	}

	@Override
	public void execute() {
		final Npc VOLUNTEER = ctx.npcs.select().id(VARS.target_ids).nearest().poll();
		if(!VOLUNTEER.inViewport()) {
			ctx.movement.step(VOLUNTEER);
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return !ctx.players.local().inMotion();
				}
			});
		}
		MAIN.status = "Pickpocketing";
		if(VOLUNTEER.interact("Pickpocket")) {
			System.out.println(ctx.players.local().healthPercent());
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return ctx.players.local().idle() && ctx.players.local().healthPercent() == 0;
				}
			});
		}
	}

}
