/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * http://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import java.util.Arrays;


public class Md2 extends BlockHasher {
	
	protected byte[] state;
	protected byte[] checksum;
	
	
	
	public Md2() {
		super(16);
		state = new byte[48];
		checksum = new byte[16];
	}
	
	
	
	protected void compress(byte[] msg, int off, int len) {
		if (!compress(state, checksum, msg, off, len))
			throw new RuntimeException("Native call failed");
	}
	
	
	protected byte[] getHashDestructively() {
		for (int i = blockFilled; i < block.length; i++)
			block[i] = (byte)(block.length - blockFilled);
		compress(block, 0, block.length);
		
		for (int i = 0; i < 16; i++)
			block[i] = checksum[i];
		compress(block, 0, block.length);
		
		return Arrays.copyOf(state, 16);
	}
	
	
	private static native boolean compress(byte[] state, byte[] checksum, byte[] msg, int off, int len);
	
	
	static {
		System.loadLibrary("nayuki-native-hashes");
	}
	
}
