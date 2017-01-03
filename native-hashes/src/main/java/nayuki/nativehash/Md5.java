/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import java.util.Arrays;


public class Md5 extends NativeBlockHasher {
	
	protected int[] state;
	
	
	
	public Md5() {
		super(64);
		state = new int[]{0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476};
	}
	
	
	
	protected void compress(byte[] msg, int off, int len) {
		if (!compress(state, msg, off, len))
			throw new RuntimeException("Native call failed");
	}
	
	
	protected byte[] getHashDestructively() {
		block[blockFilled] = (byte)0x80;
		blockFilled++;
		Arrays.fill(block, blockFilled, block.length, (byte)0);
		if (blockFilled + 8 > block.length) {
			compress(block, 0, block.length);
			Arrays.fill(block, (byte)0);
		}
		length = length << 3;
		for (int i = 0; i < 8; i++)
			block[block.length - 8 + i] = (byte)(length >>> (i * 8));
		compress(block, 0, block.length);
		
		byte[] result = new byte[state.length * 4];
		for (int i = 0; i < result.length; i++)
			result[i] = (byte)(state[i / 4] >>> (i % 4 * 8));
		return result;
	}
	
	
	private static native boolean compress(int[] state, byte[] msg, int off, int len);
}
