package nayuki.nativehash;

import org.scijava.nativelib.NativeLibraryUtil;

public abstract class NativeBlockHasher extends BlockHasher {
	private static final boolean NATIVE_LIBS_LOADED;

	static {
		NATIVE_LIBS_LOADED = NativeLibraryUtil.loadNativeLibrary(
				NativeBlockHasher.class, "nayuki-native-hashes");
	}

	{
		if (!NATIVE_LIBS_LOADED) {
			throw new UnsupportedOperationException("Unable to load native libraries. "
					+ "Check the SLF4J logs for the root cause.");
		}
	}

	public NativeBlockHasher(int blockLen) {
		super(blockLen);
	}
}
