package com.snoopdogg.bonfire;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.powerbot.script.rt6.GeItem;

import com.snoopdogg.bonfire.variables.Constants;
import com.snoopdogg.bonfire.variables.Items;

public class GUI {
	
	public boolean finished = false;
	
	private Items[] LOG_TYPES = Items.values();
	
	public GUI(final BonfireMain main, final Constants CONSTS) {
		final JFrame FRAME = new JFrame("Snoop Dogg's AIO Fletcher");
		final JPanel PANEL = new JPanel();
		final JComboBox<Items> LOGS = new JComboBox<Items>(LOG_TYPES);
		final JButton START = new JButton("Start");
		PANEL.setLayout(new BorderLayout());
		LOGS.setSelectedIndex(0);
		START.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CONSTS.fletching = (Items) LOGS.getSelectedItem();
				CONSTS.costs_per_log = GeItem.price(CONSTS.fletching.getId());
				if(CONSTS.fletching.equals(Items.OTHER)) {
					main.addJadinkoTasks();
				} else {
					main.addFiremakingTasks();
				}
				finished = true;
				FRAME.dispose();
			}
		});
		PANEL.add(LOGS,BorderLayout.CENTER);
		PANEL.add(START,BorderLayout.SOUTH);
		FRAME.add(PANEL);
		FRAME.pack();
		FRAME.setVisible(true);
	}

}
