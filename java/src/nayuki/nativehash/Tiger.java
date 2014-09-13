package nayuki.nativehash;

import java.util.Arrays;


public class Tiger extends BlockHasher {
	
	protected long[] state;
	
	
	
	public Tiger() {
		super(64);
		state = new long[]{0x0123456789ABCDEFL, 0xFEDCBA9876543210L, 0xF096A5B4C3B2E187L};
	}
	
	
	
	protected void compress(byte[] msg, int off, int len) {
		if (!compress(state, msg, off, len))
			throw new RuntimeException("Native call failed");
	}
	
	
	protected byte[] getHashDestructively() {
		block[blockFilled] = (byte)0x01;
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
		
		byte[] result = new byte[state.length * 8];
		for (int i = 0; i < result.length; i++)
			result[i] = (byte)(state[i / 8] >>> (i % 8 * 8));
		return result;
	}
	
	
	private static native boolean compress(long[] state, byte[] msg, int off, int len);
	
	
	static {
		System.loadLibrary("nayuki-native-hashes");
	}
	
}
