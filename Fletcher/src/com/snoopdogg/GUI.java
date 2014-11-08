package com.snoopdogg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.snoopdogg.variables.Constants;
import com.snoopdogg.variables.Items;

public class GUI {
	
	public boolean finished = false;
	
	private Items[] LOG_TYPES = Items.values();
	
	public GUI(final Constants consts) {
		final JFrame FRAME = new JFrame("Snoop Dogg's AIO Fletcher");
		final JPanel PANEL = new JPanel();
		final JComboBox<Items> LOGS = new JComboBox<Items>(LOG_TYPES);
		final JComboBox<String> METHODS = new JComboBox<String>(new String[]{"Shortbow","Stock","Shieldbow","Arrow shaft"});
		final JButton START = new JButton("Start");
		PANEL.setLayout(new BorderLayout());
		LOGS.setSelectedIndex(0);
		METHODS.setSelectedIndex(0);
		LOGS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LOGS.getSelectedItem().toString().equals("Logs")) {
					METHODS.addItem("Arrow shaft");
				} else {
					METHODS.removeItem("Arrow shaft");
				}
			}			
		});
		START.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final Items LOG = (Items) LOGS.getSelectedItem();
				final String METHOD = (String) METHODS.getSelectedItem();
				consts.fletching = LOG;
				consts.method = METHOD;
				finished = true;
				FRAME.dispose();
			}
		});
		PANEL.add(LOGS,BorderLayout.WEST);
		PANEL.add(METHODS,BorderLayout.EAST);
		PANEL.add(START,BorderLayout.SOUTH);
		FRAME.add(PANEL);
		FRAME.pack();
		FRAME.setVisible(true);
	}

}
