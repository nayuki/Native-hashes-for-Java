package nayuki.nativehash;

import java.util.Arrays;


public class Sha256 extends BlockHasher {
	
	protected int[] state;
	
	
	
	public Sha256() {
		super(64);
		state = new int[]{0x6A09E667, 0xBB67AE85, 0x3C6EF372, 0xA54FF53A, 0x510E527F, 0x9B05688C, 0x1F83D9AB, 0x5BE0CD19};
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
			block[block.length - 1 - i] = (byte)(length >>> (i * 8));
		compress(block, 0, block.length);
		
		byte[] result = new byte[state.length * 4];
		for (int i = 0; i < result.length; i++)
			result[i] = (byte)(state[i / 4] >>> (24 - i % 4 * 8));
		return result;
	}
	
	
	private static native boolean compress(int[] state, byte[] msg, int off, int len);
	
	
	static {
		System.loadLibrary("nayuki-native-hashes");
	}
	
}
