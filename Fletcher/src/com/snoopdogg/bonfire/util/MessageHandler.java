package com.snoopdogg.bonfire.util;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.bonfire.variables.Constants;

public class MessageHandler extends ClientAccessor {
	
	private Constants CONSTS;
	
	public MessageHandler(final ClientContext ctx,final Constants CONSTS) {
		super(ctx);
		this.CONSTS = CONSTS;
	}
	
	public void handleMessage(final MessageEvent message) {
		if(message.source().equals("")) {
			if(message.text().equals("You can't light a fire here.")) {
				final Tile CURRENT_LOC = ctx.players.local().tile();
				if(CONSTS.ACCEPTABLE_TILES.size() > 0) {
					ctx.movement.step(CONSTS.ACCEPTABLE_TILES.get(Random.nextInt(0, CONSTS.ACCEPTABLE_TILES.size() - 1)));
					Condition.wait(new Callable<Boolean>() {
						@Override
						public Boolean call() throws Exception {
							return ctx.players.local().idle();
						}
					});
				} else {
					final Tile CHOSEN_LOC = new Tile(CURRENT_LOC.x() + Random.nextInt(3, 7),CURRENT_LOC.y() + Random.nextInt(3, 7));
					ctx.movement.step(CHOSEN_LOC);
				}
			}
			if(message.text().equals("You attempt to light the logs.")) {
				CONSTS.ACCEPTABLE_TILES.add(ctx.players.local().tile());
			}
			if(message.text().equals("You add a log to the fire.")) {
				CONSTS.bonfire_fire += 1;
			}
			if(message.text().equals("The fire catches and the logs begin to burn.")) {
				CONSTS.regular_fire += 1;
			}
		}
	}

}
