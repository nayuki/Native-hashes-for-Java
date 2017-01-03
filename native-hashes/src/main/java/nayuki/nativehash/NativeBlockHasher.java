package nayuki.nativehash;

import org.scijava.nativelib.NativeLibraryUtil;

public abstract class NativeBlockHasher extends BlockHasher {
	static {
		NativeLibraryUtil.loadNativeLibrary(Md5.class, "nayuki-native-hashes");

	}

	public NativeBlockHasher(int blockLen) {
		super(blockLen);
	}
}
