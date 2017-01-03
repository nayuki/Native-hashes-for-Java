/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import java.util.Arrays;


public class Sha512 extends NativeBlockHasher {
	
	protected long[] state;
	
	
	
	public Sha512() {
		super(128);
		state = new long[]{0x6A09E667F3BCC908L, 0xBB67AE8584CAA73BL, 0x3C6EF372FE94F82BL, 0xA54FF53A5F1D36F1L, 0x510E527FADE682D1L, 0x9B05688C2B3E6C1FL, 0x1F83D9ABFB41BD6BL, 0x5BE0CD19137E2179L};
	}
	
	
	
	protected void compress(byte[] msg, int off, int len) {
		if (!compress(state, msg, off, len))
			throw new RuntimeException("Native call failed");
	}
	
	
	protected byte[] getHashDestructively() {
		block[blockFilled] = (byte)0x80;
		blockFilled++;
		Arrays.fill(block, blockFilled, block.length, (byte)0);
		if (blockFilled + 16 > block.length) {
			compress(block, 0, block.length);
			Arrays.fill(block, (byte)0);
		}
		length = length << 3;
		for (int i = 0; i < 8; i++)
			block[block.length - 1 - i] = (byte)(length >>> (i * 8));
		compress(block, 0, block.length);
		
		byte[] result = new byte[state.length * 8];
		for (int i = 0; i < result.length; i++)
			result[i] = (byte)(state[i / 8] >>> (56 - i % 8 * 8));
		return result;
	}
	
	
	private static native boolean compress(long[] state, byte[] msg, int off, int len);
}
