package com.sk.cache.dist.pack;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sk.cache.dist.unpack.Packed;
import com.sk.cache.dist.unpack.ProtocolField;
import com.sk.cache.dist.unpack.ProtocolType;
import com.sk.cache.wrappers.loaders.WrapperLoader;

public class ProtocolPacker<T extends Packed> extends Packer<T> {

	private final List<ProtocolField> booleans;
	private final List<ProtocolField> fields;
	private final Map<String, Field> sourceFields;

	public ProtocolPacker(WrapperLoader<?> loader, Class<?> source, Class<T> storage, int endId) {
		super(loader, source, storage, endId);
		this.sourceFields = new HashMap<String, Field>();
		for (Field f : source.getFields()) {
			sourceFields.put(f.getName(), f);
		}
		this.fields = ProtocolType.extractAllFields(storage, false);
		this.booleans = ProtocolType.BOOLEAN.extractFields(storage);
		Collections.reverse(this.booleans);

	}

	public ProtocolPacker(WrapperLoader<?> loader, Class<?> source, Class<T> storage) {
		this(loader, source, storage, -1);
	}

	@Override
	public int pack(Object input, OutputStream output) throws IOException {
		int size = 0;
		try {
			if (booleans.size() > 0) {
				size += packBooleans(input, output);
			}
			for (ProtocolField f : fields) {
				size += packField(input, output, f);
			}
		} catch (IllegalAccessException ignored) {
			ignored.printStackTrace();
		}
		return size;
	}

	private int packField(Object input, OutputStream out, ProtocolField field) throws IllegalArgumentException,
			IllegalAccessException, IOException {
		Object value = getFromSource(input, field.getField());
		return packField(out, value);
	}

	private int packField(OutputStream out, Object value) throws IOException {
		int ret = 0;
		if (value == null) {
			ret += writeValue(out, 0);
		} else {
			Class<?> type = value.getClass();
			if (ProtocolType.BYTE.isType(type)) {
				out.write((Byte) value);
				ret++;
			} else if (ProtocolType.INTEGER.isType(type)) {
				ret += writeValue(out, ((Number) value).longValue());
			} else if (ProtocolType.ARRAY.isType(type)) {
				int len = Array.getLength(value);
				ret += writeValue(out, Array.getLength(value));
				for (int i = 0; i < len; ++i) {
					ret += packField(out, Array.get(value, i));
				}
			} else if (ProtocolType.STRING.isType(type)) {
				ret += writeString(out, String.valueOf(value));
			}
		}
		return ret;
	}

	private Object getFromSource(Object source, Field field) throws IllegalArgumentException,
			IllegalAccessException {
		Field sourceField = sourceFields.get(field.getName());
		Object value = sourceField.get(source);
		if (value == null) {
			return "";
		}
		if (ProtocolType.INTEGER.isType(value.getClass())) {
			if (Number.class.isAssignableFrom(value.getClass())) {
				value = ((Number) value).longValue();
			}
			value = (Long) value;
		}
		return value;
	}

	private int packBooleans(Object input, OutputStream out) throws IllegalArgumentException,
			IllegalAccessException, IOException {
		int packed = 0, ret = 0, count = 0;
		for (ProtocolField f : booleans) {
			boolean value = (Boolean) getFromSource(input, f.getField());
			packed <<= 1;
			packed |= value ? 1 : 0;
			if (count++ >= 30) {
				ret += writeValue(out, packed);
				packed = 0;
				count = 0;
			}
		}
		if (count > 0) {
			ret += writeValue(out, packed);
		}
		return ret;
	}
}
