package com.snoopdogg.dungeoneering;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

import com.snoopdogg.dungeoneering.sharedvars.DungeonLayout;
import com.snoopdogg.dungeoneering.tasks.RoomFiller;
import com.snoopdogg.framework.Task;

@Script.Manifest(name="BasicDungeon", description = "Dungeoneers", properties = "topic=1228656;hidden=false;client=6;")
public class DungeonMain extends PollingScript<ClientContext> implements PaintListener, MessageListener, MouseListener {
	
	private final DungeonLayout layout = new DungeonLayout(ctx);
	private final ArrayList<Task> tasks = new ArrayList<Task>();

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messaged(MessageEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repaint(Graphics arg0) {
		arg0.setColor(Color.RED);
		final Iterator<GameObject> iter = layout.valid_tiles_entities.iterator();
		while(iter.hasNext()) {
			final GameObject failed_tile = iter.next();
			arg0.drawPolygon(failed_tile.tile().matrix(ctx).bounds());
		}
		arg0.setColor(Color.GREEN);
		final Iterator<Tile> _iter = layout.reachables.iterator();
		while(_iter.hasNext()) {
			final Tile true_tile = _iter.next();
			arg0.drawPolygon(true_tile.matrix(ctx).bounds());
		}
	}
	
	@Override
	public void start() {
		tasks.add(new RoomFiller(layout,ctx));
	}

	@Override
	public void poll() {
		for(Task t: tasks) {
			if(t.activate()) {
				t.execute();
			}
		}
	}
}