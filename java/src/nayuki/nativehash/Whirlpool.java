/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Nayuki Minase
 * http://nayuki.eigenstate.org/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import java.util.Arrays;


public class Whirlpool extends BlockHasher {
	
	protected byte[] state;
	
	
	
	public Whirlpool() {
		super(64);
		state = new byte[64];
	}
	
	
	
	protected void compress(byte[] msg, int off, int len) {
		if (!compress(state, msg, off, len))
			throw new RuntimeException("Native call failed");
	}
	
	
	protected byte[] getHashDestructively() {
		block[blockFilled] = (byte)0x80;
		blockFilled++;
		Arrays.fill(block, blockFilled, block.length, (byte)0);
		if (blockFilled + 32 > block.length) {
			compress(block, 0, block.length);
			Arrays.fill(block, (byte)0);
		}
		length = length << 3;
		for (int i = 0; i < 8; i++)
			block[block.length - 1 - i] = (byte)(length >>> (i * 8));
		compress(block, 0, block.length);
		
		return state;
	}
	
	
	private static native boolean compress(byte[] state, byte[] msg, int off, int len);
	
	
	static {
		System.loadLibrary("nayuki-native-hashes");
	}
	
}
