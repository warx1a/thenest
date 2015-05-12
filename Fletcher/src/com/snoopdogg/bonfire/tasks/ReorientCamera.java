package com.snoopdogg.bonfire.tasks;

import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.framework.Task;

public class ReorientCamera extends Task {

	public ReorientCamera(ClientContext arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return ctx.camera.pitch() <= 40;
	}

	@Override
	public void execute() {
		ctx.camera.pitch(Random.nextInt(60, 70));
	}

}
