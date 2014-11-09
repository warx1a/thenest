package com.snoopdogg.util;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.variables.Constants;

public class MessageHandler extends ClientAccessor {
	
	private Constants CONSTS;
	
	public MessageHandler(final ClientContext ctx,final Constants CONSTS) {
		super(ctx);
		this.CONSTS = CONSTS;
	}
	
	public void handleMessage(final MessageEvent message) {
		if(message.text().equals("You can't light a fire here.") && message.source().equals("")) {
			final Tile CURRENT_LOC = ctx.players.local().tile();
			final Tile CHOSEN_LOC = new Tile(CURRENT_LOC.x() + Random.nextInt(3, 7),CURRENT_LOC.y() + Random.nextInt(3, 7));
			ctx.movement.step(CHOSEN_LOC);
		}
		if(message.text().equals("You attempt to light the logs.") && message.source().equals("")) {
			CONSTS.ACCEPTABLE_TILES.add(ctx.players.local().tile());
		}
		if(message.text().equals("You add a log to the fire.") && message.source().equals("")) {
			CONSTS.bonfire_fire += 1;
		}
		if(message.text().equals("The fire catches and the logs begin to burn.") && message.source().equals("")) {
			CONSTS.regular_fire += 1;
		}
		if(message.text().contains("cut") && message.source().equals("")) {
			CONSTS.cut += 1;
		}
	}

}
