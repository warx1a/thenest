package com.sk.cache.dist.pack;

import com.sk.cache.dist.unpack.PackedRegion;
import com.sk.cache.wrappers.loaders.RegionLoader;
import com.sk.cache.wrappers.region.Region;

public class RegionPacker extends ProtocolPacker<PackedRegion> {

	public RegionPacker(RegionLoader loader) {
		super(loader, SanitizedRegion.class, PackedRegion.class, 0x7fff);
	}

	@Override
	public SanitizedRegion sanitize(Object o) {
		if (o == null || !(o instanceof Region))
			return null;
		return new SanitizedRegion((Region) o);
	}
}
