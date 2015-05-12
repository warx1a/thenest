package com.snoopdogg.blackjack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.snoopdogg.blackjack.variables.Locations;
import com.snoopdogg.blackjack.variables.Targets;
import com.snoopdogg.blackjack.variables.Variables;

public class GUI {
	
	private final Variables VARS;
	private final BlackjackMain MAIN;
	
	public GUI(final BlackjackMain MAIN, final Variables VARS) {
		this.MAIN = MAIN;
		this.VARS = VARS;
	}
	
	public void init() {
		final JFrame frame = new JFrame("Basic AIO Thiever");
		final JPanel panel = new JPanel();
		final JComboBox<Locations> LOCATIONS = new JComboBox<Locations>(Locations.values());
		final JComboBox<String> METHODS = new JComboBox<String>(Locations.NONE.getMethods());
		final JComboBox<Targets> TARGETS = new JComboBox<Targets>(Locations.NONE.getTargets());
		final JButton START = new JButton("Start");
		panel.setLayout(new BorderLayout());
		LOCATIONS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final Locations location = (Locations) LOCATIONS.getSelectedItem();
				METHODS.removeAllItems();
				for(String s: location.getMethods()) {
					METHODS.addItem(s);
				}
				TARGETS.removeAllItems();
				for(Targets t: location.getTargets()) {
					TARGETS.addItem(t);
				}
			}
		});
		START.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VARS.location = (Locations) LOCATIONS.getSelectedItem();
				final String _METHOD = (String) METHODS.getSelectedItem();
				final Targets TARG = (Targets) TARGETS.getSelectedItem();
				if(TARG.getMethod().equals(_METHOD)) {
					VARS.target_ids = TARG.getIds();
					if(_METHOD.equals("Blackjack")) {
						MAIN.addBlackjackTasks();
					} else if(_METHOD.equals("Pickpocket")) {
						MAIN.addPickpocketTasks();
					}
					MAIN.finished = true;
					frame.dispose();
				}
			}
		});
		LOCATIONS.setPreferredSize(new Dimension(80,25));
		METHODS.setPreferredSize(new Dimension(75,25));
		TARGETS.setPreferredSize(new Dimension(100,25));
		panel.add(LOCATIONS,BorderLayout.WEST);
		panel.add(METHODS,BorderLayout.CENTER);
		panel.add(TARGETS,BorderLayout.EAST);
		panel.add(START,BorderLayout.SOUTH);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
