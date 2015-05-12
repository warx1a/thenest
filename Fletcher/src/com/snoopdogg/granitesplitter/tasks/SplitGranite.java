package com.snoopdogg.granitesplitter.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.Item;

import com.snoopdogg.framework.Task;
import com.snoopdogg.granitesplitter.SplitterMain;

public class SplitGranite extends Task {

	private final SplitterMain MAIN;
	
	//private final Component FIRST_INDEX = ctx.widgets.component(1371, 44).component(1);
	private final Component CUT_BUTTON = ctx.widgets.component(1370, 20);
	private final Component CANCEL_BUTTON = ctx.widgets.component(1251, 19);

	public SplitGranite(ClientContext arg0, SplitterMain MAIN) {
		super(arg0);
		this.MAIN = MAIN;
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().id(MAIN.GRANITE_2KG_ID).count() >= 1;
	}

	@Override
	public void execute() {
		MAIN.status = "Splitting";
		final Item GRANITE = ctx.backpack.id(MAIN.GRANITE_2KG_ID).poll();
		if(GRANITE.interact("Craft")) {
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return CUT_BUTTON.valid() && CUT_BUTTON.visible();
				}
			});
			if(CUT_BUTTON.valid() && CUT_BUTTON.visible()) {
				CUT_BUTTON.click();
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return !CANCEL_BUTTON.valid() && !CANCEL_BUTTON.visible();
					}
				});
			}
		}
	}

}
