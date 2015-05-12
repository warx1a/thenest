package com.snoopdogg.antiban;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;

import com.snoopdogg.antiban.breakhandler.BreakHandler;

@Script.Manifest(name="Tester", description = "Test.", properties = "hidden=true;client=6;")
public class AntibanTester extends PollingScript<ClientContext>{
	
	private final BreakHandler handler = BreakHandler.getBreakHandler(ctx);

	@Override
	public void poll() {
	}

}
