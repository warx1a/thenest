package com.snoopdogg.blackjack.tasks;

/*
 * Blackjacker for thieves guild
 */

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.Npc;

import com.snoopdogg.blackjack.BlackjackMain;
import com.snoopdogg.blackjack.variables.Variables;
import com.snoopdogg.framework.Task;

public class BlackjackThievesGuild extends Task {
	
	private final BlackjackMain MAIN;
	private final Variables VARS;
	
	private final Component CONTINUE = ctx.widgets.component(1191, 8);
	private final Component SECOND_CONTINUE = ctx.widgets.component(1184, 11);

	public BlackjackThievesGuild(final ClientContext arg0, final BlackjackMain MAIN, final Variables VARS) {
		super(arg0);
		this.MAIN = MAIN;
		this.VARS = VARS;
	}

	@Override
	public boolean activate() {
		final int bit = ctx.varpbits.varpbit(659,0x23284651);
		return bit != 589841985;
	}

	@Override
	public void execute() {
		final Npc VOLUNTEER = ctx.npcs.select().id(VARS.target_ids).nearest().poll();
		if(VOLUNTEER.inViewport()) {
			if(VOLUNTEER.interact("Lure")) {
				MAIN.status = "Luring volunteer";
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return !ctx.players.local().inMotion() && CONTINUE.visible() && CONTINUE.valid();
					}
				});
				if(CONTINUE.visible() && CONTINUE.valid()) {
					MAIN.status = "Going through dialog";
					if(ctx.input.send(" ")) {
						Condition.wait(new Callable<Boolean>() {
							@Override
							public Boolean call() throws Exception {
								return SECOND_CONTINUE.valid() && SECOND_CONTINUE.visible();
							}		
						});
						if(ctx.input.send(" ")) {
							if(VOLUNTEER.interact("Knock-out")) {
								MAIN.status = "Knocking him out and looting him";
								Condition.wait(new Callable<Boolean>() {
									@Override
									public Boolean call() throws Exception {
										return VOLUNTEER.animation() == 12413;
									}
								});
								while(VOLUNTEER.animation() == 12413) {
									if(VOLUNTEER.interact("Loot")) {
										Condition.sleep(Random.nextInt(100, 150));
									}
								}
								MAIN.status = "Finding new target";
							}
						}
					}
				}
			}
		} else {
			ctx.movement.step(VOLUNTEER);
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return ctx.players.local().idle();
				}
			});
		}
	}
}
