package com.snoopdogg.development;

import java.util.ArrayList;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

public class GetQuests extends ClientAccessor {
	
	final Component QUESTLIST = ctx.widgets.component(190, 17);

	public GetQuests(ClientContext arg0) {
		super(arg0);
	}
	
	public ArrayList<String> getCompleted() {
		final ArrayList<String> COMPLETED = new ArrayList<String>();
		final Component[] QUESTS = QUESTLIST.components();
		for(final Component QUEST: QUESTS) {
			if(!QUEST.text().equals("") && QUEST.textColor() == 65280) {
				COMPLETED.add(QUEST.text());
			}
		}
		return COMPLETED;
	}

}
