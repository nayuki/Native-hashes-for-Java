/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki. (MIT License)
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import java.util.Random;


public class BenchmarkHashes {
	
	public static void main(String[] args) {
		BlockHasher[] hashers;
		if (args[0].equals("java")) {
			hashers = new BlockHasher[] {
				new Md2Java(),
				new Md4Java(),
				new Md5Java(),
				new Ripemd128Java(),
				new Ripemd160Java(),
				new Ripemd256Java(),
				new Ripemd320Java(),
				new Sha1Java(),
				new Sha224Java(),
				new Sha256Java(),
				new Sha384Java(),
				new Sha512Java(),
				new TigerJava(),
				new Tiger2Java(),
				new WhirlpoolJava(),
			};
		} else if (args[0].equals("native")) {
			hashers = new BlockHasher[] {
				new Md2(),
				new Md4(),
				new Md5(),
				new Ripemd128(),
				new Ripemd160(),
				new Ripemd256(),
				new Ripemd320(),
				new Sha1(),
				new Sha224(),
				new Sha256(),
				new Sha384(),
				new Sha512(),
				new Tiger(),
				new Tiger2(),
				new Whirlpool(),
			};
		} else
			throw new IllegalArgumentException();
		
		int trials = 1;
		if (args.length == 2)
			trials = Integer.parseInt(args[1]);
		
		byte[] buffer = new byte[64 * 1024 * 1024];
		new Random().nextBytes(buffer);
		for (BlockHasher hasher : hashers) {
			long len = 1000;
			while (getTime(hasher, buffer, len) < 1000000000)  // Warm up the JIT
				len *= 4;
			
			long time = getTime(hasher, buffer, len);
			for (int i = 0; i < trials; i++) {
				len = Math.round(len * 1e9 / time);  // Try to target 1.0 second
				time = getTime(hasher, buffer, len);
				System.out.printf("%-35s  %12d B  %12d ns  %8.2f MB/s%n", hasher.getClass().getName(), len, time, len * 1e9 / time / 1e6);
			}
			if (trials > 1)
				System.out.println();
		}
	}
	
	
	// Returns nanoseconds
	private static long getTime(BlockHasher hasher, byte[] buffer, long len) {
		long startTime = System.nanoTime();
		while (len > 0) {
			int n = (int)Math.min(len, buffer.length);
			hasher.update(buffer, 0, n);
			len -= n;
		}
		return System.nanoTime() - startTime;
	}
	
}
