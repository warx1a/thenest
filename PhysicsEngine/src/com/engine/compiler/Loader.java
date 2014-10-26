package com.engine.compiler;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.engine.gameinfo.GameInfo;

public class Loader<T> {
	
	private final Class<T> clazz;
	private final HashMap<String,String> properties;
	public volatile JFrame frame;
	public volatile JPanel panel = new JPanel();
	
	public Loader(final Class<T> clazz,final HashMap<String,String> properties) {
		this.clazz = clazz;
		this.properties = properties;
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
	
	public void initFrame(HashMap<String,String> properties) {
		final int SCREEN_HEIGHT = Integer.parseInt(properties.get("Screen-height"));
		final int SCREEN_WIDTH = Integer.parseInt(properties.get("Screen-width"));
		final String FRAME_TITLE = properties.get("Window-name");
		frame = new JFrame(FRAME_TITLE);
		frame.setSize(new Dimension(SCREEN_HEIGHT,SCREEN_WIDTH));
		if(panel != null) {
			panel.setSize(frame.getSize());
			frame.add(panel);
		}
		frame.setVisible(true);
	}
	
	public synchronized void offerRunnable(final Runnable runnable) {
		final Thread testing = new Thread(runnable);
		testing.start();
	}
	
	public synchronized void onPreExecute(Callable<Void> _void) {
		try {
			_void.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void execute() {
		if(validateMethods()) {
			initFrame(properties);
		} else {
			throw new RuntimeException();
		}
	}

}
