package com.snoopdogg.antiban;

import java.awt.Point;
import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Identifiable;
import org.powerbot.script.Locatable;
import org.powerbot.script.Nameable;
import org.powerbot.script.Random;
import org.powerbot.script.Validatable;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Game;
import org.powerbot.script.rt6.Interactive;

public class Interaction<T extends Interactive & Nameable & Locatable & Identifiable & Validatable> extends ClientAccessor {
	
	private final T object;
	
	public Interaction(ClientContext ctx, T object) {
		super(ctx);
		this.object = object;
	}
	
	public boolean interact(final int derivation, final int tries) {
		for(int i = 0; i < tries; i++) {
			final Point centerpoint = object.centerPoint();
			final int random_x = Random.nextInt((int) centerpoint.getX() - derivation,(int) centerpoint.getX() + derivation);
			final int random_y = Random.nextInt((int) centerpoint.getY() - derivation,(int) centerpoint.getY() + derivation);
			final Point derivative = new Point(random_x,random_y);
			if(ctx.mouse.move(derivative)) {
				if(ctx.game.crosshair() == Game.Crosshair.ACTION) {
					if(ctx.mouse.click(true)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
