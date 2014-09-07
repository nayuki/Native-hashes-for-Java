package nayuki.nativehash;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;


abstract class HashTest {
	
	protected static Random random = new Random();
	
	
	protected abstract BlockHasher newHasher(boolean useNative);
	
	protected abstract String[][] getTestVectors();
	
	
	public void run() {
		selfCheck();
		benchmark();
	}
	
	
	public void selfCheck() {
		// Check test vectors
		for (String[] testCase : getTestVectors()) {
			String message = testCase[1];
			String expectHash = testCase[0];
			if (!toHex(getHash(message, false)).equals(expectHash)) {
				String errorMsg = String.format("Self-check failed: %s(%s) != %s", newHasher(false).getClass().getName(), message, expectHash);
				throw new AssertionError(errorMsg);
			}
		}
		
		// Randomized test
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int len = r.nextInt(10000);
			BlockHasher h0 = newHasher(false);
			BlockHasher h1 = newHasher(true);
			while (len > 0) {
				int n = r.nextInt(len) + 1;
				byte[] b = new byte[n];
				r.nextBytes(b);
				h0.update(b);
				h1.update(b);
				len -= n;
			}
			if (!Arrays.equals(h0.getHash(), h1.getHash()))
				throw new AssertionError("Native/Java hash mismatch");
		}
	}
	
	
	public void benchmark() {
		byte[] b = new byte[1 << 27];  // 128 MiB
		new Random().nextBytes(b);
		
		BlockHasher hashJava = newHasher(false);
		BlockHasher hashNative = newHasher(true);
		
		// Warm up the JIT
		for (int i = 16; i <= 24; i++)
			hashJava.update(b, 0, 1 << i);
		
		System.out.println("Block size    Java impl    Native impl");
		while (true) {
			for (int i = 12; i <= 27; i++) {
				int len = 1 << i;
				long startTime;
				
				startTime = System.nanoTime();
				hashJava.update(b, 0, len);
				double speed0 = len / 1048576.0 * 1e9 / (System.nanoTime() - startTime);
				
				startTime = System.nanoTime();
				hashNative.update(b, 0, len);
				double speed1 = len / 1048576.0 * 1e9 / (System.nanoTime() - startTime);
				
				System.out.printf("%10d B  %5.1f MiB/s  %5.1f MiB/s%n", len, speed0, speed1);
			}
		}
	}
	
	
	protected byte[] getHash(String msg, boolean useNative) {
		try {
			BlockHasher h = newHasher(useNative);
			h.update(msg.getBytes("US-ASCII"));
			return h.getHash();
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError(e);
		}
	}
	
	
	protected static String toHex(byte[] hash) {
		String result = "";
		for (byte b : hash)
			result += String.format("%02X", b & 0xFF);
		return result;
	}
	
}
