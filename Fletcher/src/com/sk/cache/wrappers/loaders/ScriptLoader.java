package com.sk.cache.wrappers.loaders;

import com.sk.cache.fs.Archive;
import com.sk.cache.fs.CacheSystem;
import com.sk.cache.fs.FileData;
import com.sk.cache.wrappers.Script;

public class ScriptLoader extends WrapperLoader<Script> {

	private final Archive source;

	public ScriptLoader(CacheSystem cacheSystem) {
		super(cacheSystem);
		this.source = cacheSystem.getCacheSource().getCacheType(2).getArchive(69);
	}

	@Override
	public Script load(int id) {
		FileData data = source.getFile(id);
		if (data == null)
			throw new IllegalArgumentException("Bad script id");
		Script ret = new Script(this, id);
		ret.decode(data.getDataAsStream());
		return ret;
	}

	@Override
	public boolean canLoad(int id) {
		return source.getFile(id) != null;
	}

}
