package com.engine;

import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.Callable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.engine.compiler.Loader;
import com.engine.gameinfo.GameInfo;

public class TestGame implements GameInfo {
	
	private final String author = "Snoop dogg";
	private final String[] tags = new String[]{"Test","Testing"};
	private final String app_name = "Testing platform";
	private final String description = "testing platform";
	private final String version = "1.0.0";
	private final JLabel time_clock = new JLabel();
	
	private final HashMap<String,String> properties = new HashMap<>();

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public String[] getTags() {
		return tags;
	}

	@Override
	public String getName() {
		return app_name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public HashMap<String, String> getProperties() {
		return properties;
	}
	
	private class Test implements Runnable {
		
		final JFrame frame = new JFrame("Test");
		final JPanel panel = new JPanel();
		final JLabel time = new JLabel();

		@Override
		public void run() {
			panel.setLayout(new BorderLayout());
			while(true) {
				final Calendar cal = Calendar.getInstance();
				time.setText(cal.getTime().toString());
				panel.add(time,BorderLayout.NORTH);
				frame.add(panel);
				frame.pack();
				frame.setVisible(true);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	private class Test1 implements Runnable {
		

		@Override
		public void run() {
		}
	}
	
	public static void main(String[] args) {
		final TestGame testgame = new TestGame();
		final Class<TestGame> test_class = (Class<TestGame>) testgame.getClass();
		final Loader<TestGame> loader = new Loader<TestGame>(test_class,testgame.properties);
		loader.onPreExecute(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				testgame.properties.put("Window-name",testgame.getName());
				testgame.properties.put("Screen-width", "500");
				testgame.properties.put("Screen-height", "500");
				return null;
			}
		});
		loader.offerRunnable(new Runnable() {
			@Override
			public void run() {
				while(true) {
					final Calendar cal = Calendar.getInstance();
					testgame.time_clock.setText(cal.getTime().toString());
					loader.panel.add(testgame.time_clock,BorderLayout.CENTER);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}	
			}
		});
		// must offer it a class or an inner anon class that implements runnable
		//loader.offerRunnable(testgame.new Test());
		loader.execute();
	}

}
