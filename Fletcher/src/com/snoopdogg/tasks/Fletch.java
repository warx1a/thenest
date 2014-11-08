package com.snoopdogg.tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.Item;

import com.snoopdogg.framework.Task;
import com.snoopdogg.variables.Constants;
import com.snoopdogg.variables.Items;

public class Fletch extends Task {
	
	private final Constants CONSTS;
	private boolean first = true;

	public Fletch(ClientContext arg0, final Constants consts) {
		super(arg0);
		this.CONSTS = consts;
	}

	@Override
	public boolean activate() {
		// the cancel button for the creating interface
		final Component CANCEL_BUTTON = CONSTS.CANCEL_BUTTON;
		return ctx.backpack.select().id(CONSTS.fletching.getId()).count() >= 1 && !CANCEL_BUTTON.valid() && !CANCEL_BUTTON.visible();
	}

	@Override
	public void execute() {
		// get components from constants class
		final Component KNIFE = CONSTS.KNIFE_CLICK;
		final Component FLETCH_BUTTON = CONSTS.FLETCH_BUTTON;
		// get random log from inventory
		final Item log = ctx.backpack.select().id(CONSTS.fletching.getId()).shuffle().poll();
		// interact with log
		if(log.interact("Craft")) {
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return !KNIFE.valid() && !KNIFE.visible();
				}
			},200,3);
			if(KNIFE.valid() && KNIFE.visible()) {
				// click on knife in popup menu
				if(KNIFE.click()) {
					// wait for fletch button to open up
					Condition.wait(new Callable<Boolean> () {

						@Override
						public Boolean call() throws Exception {
							return !FLETCH_BUTTON.visible() && !FLETCH_BUTTON.valid();
						}	
					});
					// if the buttons visible
					if(FLETCH_BUTTON.inViewport() && FLETCH_BUTTON.valid()) {
						// if it still needs to select the menu item
						if(first) {
							final Component itemchoice = evaluateIndexes(CONSTS.method);
							if(itemchoice.visible() && itemchoice.valid()) {
								itemchoice.click();
								first = false;
							}
						}
						if(CONSTS.FLETCH_BUTTON.click()) {
							// wait to fletch the logs
							Condition.wait(new Callable<Boolean>() {
								@Override
								public Boolean call() throws Exception {
									return ctx.backpack.select().id(CONSTS.fletching.getId()).isEmpty();
								}	
							});
						}
					}
				}
				// if it took us directly to the fletching interface
			} else if(FLETCH_BUTTON.inViewport() && FLETCH_BUTTON.valid()) {
				// if it still needs to select the menu item
				if(first) {
					final Component itemchoice = evaluateIndexes(CONSTS.method);
					if(itemchoice.visible() && itemchoice.valid()) {
						itemchoice.click();
						first = false;
					}
				}
				if(CONSTS.FLETCH_BUTTON.click()) {
					// wait to fletch the logs
					Condition.wait(new Callable<Boolean>() {
						@Override
						public Boolean call() throws Exception {
							return ctx.backpack.select().id(CONSTS.fletching.getId()).isEmpty();
						}	
					});
				}
			}
		}
	}
	/* get the component based on the method
	* wrote this way because we dont support java 7
	* regular logs have a different index than the rest of the other types of logs
	* */
	private Component evaluateIndexes(final String method) {
		if(method.equals("Shortbow")) {
			if(CONSTS.fletching.equals(Items.LOGS)) {
				return CONSTS.SECOND_INDEX;
			} else {
				return CONSTS.FIRST_INDEX;
			}
		} else if(method.equals("Stock")) {
			if(CONSTS.fletching.equals(Items.LOGS)) {
				return CONSTS.THIRD_INDEX;
			} else {
				return CONSTS.SECOND_INDEX;
			}
		} else if(method.equals("Shieldbow")) {
			if(CONSTS.fletching.equals(Items.LOGS)) {
				return CONSTS.FOURTH_INDEX;
			} else {
				return CONSTS.THIRD_INDEX;
			}
		} else {
			return CONSTS.FIRST_INDEX;
		}
	}

}
