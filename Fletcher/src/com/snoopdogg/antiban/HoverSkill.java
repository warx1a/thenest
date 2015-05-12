package com.snoopdogg.antiban;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

public class HoverSkill extends ClientAccessor {
	
	private final Component INDICE;
	private boolean finished;
	private final Component HERO_WIDGET = ctx.widgets.component(1431, 7);
	private final Component SKILLS_WIDGET = ctx.widgets.component(1432, 4).component(0);
	
	public HoverSkill(final ClientContext ctx, final SkillsIndice SKILL) {
		super(ctx);
		INDICE = ctx.widgets.component(1466, 7).component(SKILL.ordinal());
	}
	
	// actual hovering method
	public void hover(final long TIMEOUT) {
		finished = false;
		// if the nested hero widget is valid
		if(HERO_WIDGET.valid() && HERO_WIDGET.visible()) {
			if(HERO_WIDGET.hover()) {
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return SKILLS_WIDGET.visible() && SKILLS_WIDGET.valid();
					}
				});
				// click on the open skills tab widget
				if(SKILLS_WIDGET.click()) {
					Condition.wait(new Callable<Boolean>() {
						@Override
						public Boolean call() throws Exception {
							return INDICE.valid() && INDICE.visible();
						}
					});
					// hover the indice
					if(INDICE.hover()) {
						final long HOVEREND = TIMEOUT + System.currentTimeMillis();
						Condition.wait(new Callable<Boolean>() {
							@Override
							public Boolean call() throws Exception {
								return System.currentTimeMillis() < HOVEREND;
							}
						});
						finished = true;
					}
				}
			}
		}
	}
	
	// activation class for it
	public boolean activate() {
		return false;
	}
	
	// check when its done
	public boolean finished() {
		return finished;
	}

}
