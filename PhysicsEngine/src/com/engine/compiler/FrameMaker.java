package com.engine.compiler;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.engine.gameinfo.GameInfo;


/**
 * 
 * Creates a testing framework for autobuilding the games frame using JFrame(s)
 * 
 * @author Snoop Dogg
 *
 * @param <T>
 * 
 * @version 1.0.0
 * 
 * 
 * @see javax.swing.JFrame
 */
public class FrameMaker<T> {
	
	private final Class<T> clazz;
	
	private final JFrame frame = new JFrame();
	
	/**
	 * 
	 * Invokes constructor of class of type "T"
	 * 
	 * @param Class<T>
	 * 
	 * @version 1.0.0
	 */
	public FrameMaker(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * 
	 * Validates the class to make sure it implements GameInfo interface
	 * 
	 * @param none
	 * 
	 * @version 1.0.0
	 * 
	 * @return boolean
	 */
	public boolean validateMethods() {
		Class<?> c[] = clazz.getInterfaces(); 
		if(Arrays.asList(c).contains(GameInfo.class)) {
			return true;
		}
		return false;
	}
	
	public void addPanel(final JPanel panel) {
		frame.add(panel);
	}
	
	public void initFrame(HashMap<String,String> properties) {
		final int SCREEN_HEIGHT = Integer.parseInt(properties.get("Screen-height"));
		final int SCREEN_WIDTH = Integer.parseInt(properties.get("Screen-width"));
		final String FRAME_TITLE = properties.get("Window-name");
		frame.setTitle(FRAME_TITLE);
		frame.setSize(new Dimension(SCREEN_HEIGHT,SCREEN_WIDTH));
		frame.setVisible(true);
	}

}
