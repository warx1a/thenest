package com.snoopdogg.development;

import java.io.File;
import java.io.FileNotFoundException;

import com.sk.cache.DataSource;

public class SneakyQuests {
	
	private final DataSource source;
	
	SneakyQuests(final File rs_files) throws FileNotFoundException {
		source = new DataSource(rs_files);
	}

}
