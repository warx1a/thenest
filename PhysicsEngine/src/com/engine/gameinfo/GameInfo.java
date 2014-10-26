package com.engine.gameinfo;

import java.util.HashMap;

public interface GameInfo {
	
	public abstract String getVersion();
	
	public abstract String getAuthor();
	
	public abstract String[] getTags();
	
	public abstract String getName();
	
	public abstract String getDescription();
	
	public abstract HashMap<String,String> getProperties();

}
