/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki. (MIT License)
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


final class WhirlpoolJava extends Whirlpool {
	
	protected void compress(byte[] msg, int off, int len) {
		long[] tempblock = new long[8];
		long[] tempstate = new long[8];
		
		for (int i = off, end = off + len; i < end; i += 64) {
			for (int j = 0, k = 0; j < 8; j++, k += 8) {
				tempblock[j] = (
					   msg[i + k + 0] & 0xFFL) << 56
					| (msg[i + k + 1] & 0xFFL) << 48
					| (msg[i + k + 2] & 0xFFL) << 40
					| (msg[i + k + 3] & 0xFFL) << 32
					| (msg[i + k + 4] & 0xFFL) << 24
					| (msg[i + k + 5] & 0xFFL) << 16
					| (msg[i + k + 6] & 0xFFL) <<  8
					| (msg[i + k + 7] & 0xFFL) <<  0;
				tempstate[j] = (
					   state[k + 0] & 0xFFL) << 56
					| (state[k + 1] & 0xFFL) << 48
					| (state[k + 2] & 0xFFL) << 40
					| (state[k + 3] & 0xFFL) << 32
					| (state[k + 4] & 0xFFL) << 24
					| (state[k + 5] & 0xFFL) << 16
					| (state[k + 6] & 0xFFL) <<  8
					| (state[k + 7] & 0xFFL) <<  0;
			}
			
			encrypt(tempblock, tempstate);
			for (int j = 0; j < 64; j++)
				state[j] ^= (int)(tempblock[j >>> 3] >>> ((7 - (j & 7)) << 3)) ^ msg[i + j];
		}
	}
	
	
	private static void encrypt(long[] message, long[] key) {
		for (int i = 0; i < 8; i++)
			message[i] ^= key[i];
		
		for (int i = 0; i < RCON.length; i++) {
			round(key, RCON[i]);
			round(message, key);
		}
	}
	
	
	private static void round(long[] msg, long[] key) {
		final int M = 0xFF;
		long m0 = msg[0], m1 = msg[1], m2 = msg[2], m3 = msg[3], m4 = msg[4], m5 = msg[5], m6 = msg[6], m7 = msg[7];
		msg[0] = MUL[(int)(m0 >>> 56)] ^ MUL[0x100 | (int)(m7 >>> 48) & M] ^ MUL[0x200 | (int)(m6 >>> 40) & M] ^ MUL[0x300 | (int)(m5 >>> 32) & M] ^ MUL[0x400 | (int)m4 >>> 24] ^ MUL[0x500 | (int)m3 >>> 16 & M] ^ MUL[0x600 | (int)m2 >>> 8 & M] ^ MUL[0x700 | (int)m1 & M] ^ key[0];
		msg[1] = MUL[(int)(m1 >>> 56)] ^ MUL[0x100 | (int)(m0 >>> 48) & M] ^ MUL[0x200 | (int)(m7 >>> 40) & M] ^ MUL[0x300 | (int)(m6 >>> 32) & M] ^ MUL[0x400 | (int)m5 >>> 24] ^ MUL[0x500 | (int)m4 >>> 16 & M] ^ MUL[0x600 | (int)m3 >>> 8 & M] ^ MUL[0x700 | (int)m2 & M] ^ key[1];
		msg[2] = MUL[(int)(m2 >>> 56)] ^ MUL[0x100 | (int)(m1 >>> 48) & M] ^ MUL[0x200 | (int)(m0 >>> 40) & M] ^ MUL[0x300 | (int)(m7 >>> 32) & M] ^ MUL[0x400 | (int)m6 >>> 24] ^ MUL[0x500 | (int)m5 >>> 16 & M] ^ MUL[0x600 | (int)m4 >>> 8 & M] ^ MUL[0x700 | (int)m3 & M] ^ key[2];
		msg[3] = MUL[(int)(m3 >>> 56)] ^ MUL[0x100 | (int)(m2 >>> 48) & M] ^ MUL[0x200 | (int)(m1 >>> 40) & M] ^ MUL[0x300 | (int)(m0 >>> 32) & M] ^ MUL[0x400 | (int)m7 >>> 24] ^ MUL[0x500 | (int)m6 >>> 16 & M] ^ MUL[0x600 | (int)m5 >>> 8 & M] ^ MUL[0x700 | (int)m4 & M] ^ key[3];
		msg[4] = MUL[(int)(m4 >>> 56)] ^ MUL[0x100 | (int)(m3 >>> 48) & M] ^ MUL[0x200 | (int)(m2 >>> 40) & M] ^ MUL[0x300 | (int)(m1 >>> 32) & M] ^ MUL[0x400 | (int)m0 >>> 24] ^ MUL[0x500 | (int)m7 >>> 16 & M] ^ MUL[0x600 | (int)m6 >>> 8 & M] ^ MUL[0x700 | (int)m5 & M] ^ key[4];
		msg[5] = MUL[(int)(m5 >>> 56)] ^ MUL[0x100 | (int)(m4 >>> 48) & M] ^ MUL[0x200 | (int)(m3 >>> 40) & M] ^ MUL[0x300 | (int)(m2 >>> 32) & M] ^ MUL[0x400 | (int)m1 >>> 24] ^ MUL[0x500 | (int)m0 >>> 16 & M] ^ MUL[0x600 | (int)m7 >>> 8 & M] ^ MUL[0x700 | (int)m6 & M] ^ key[5];
		msg[6] = MUL[(int)(m6 >>> 56)] ^ MUL[0x100 | (int)(m5 >>> 48) & M] ^ MUL[0x200 | (int)(m4 >>> 40) & M] ^ MUL[0x300 | (int)(m3 >>> 32) & M] ^ MUL[0x400 | (int)m2 >>> 24] ^ MUL[0x500 | (int)m1 >>> 16 & M] ^ MUL[0x600 | (int)m0 >>> 8 & M] ^ MUL[0x700 | (int)m7 & M] ^ key[6];
		msg[7] = MUL[(int)(m7 >>> 56)] ^ MUL[0x100 | (int)(m6 >>> 48) & M] ^ MUL[0x200 | (int)(m5 >>> 40) & M] ^ MUL[0x300 | (int)(m4 >>> 32) & M] ^ MUL[0x400 | (int)m3 >>> 24] ^ MUL[0x500 | (int)m2 >>> 16 & M] ^ MUL[0x600 | (int)m1 >>> 8 & M] ^ MUL[0x700 | (int)m0 & M] ^ key[7];
	}
	
	
	private static final int ROUNDS = 10;
	private static long[][] RCON;
	private static long[] MUL;
	
	
	static {
		// S-box
		int[] SBOX = new int[256];
		int[] e = {0x1, 0xB, 0x9, 0xC, 0xD, 0x6, 0xF, 0x3, 0xE, 0x8, 0x7, 0x4, 0xA, 0x2, 0x5, 0x0};
		int[] r = {0x7, 0xC, 0xB, 0xD, 0xE, 0x4, 0x9, 0xF, 0x6, 0x3, 0x8, 0xA, 0x2, 0x5, 0x1, 0x0};
		int[] einv = new int[16];
		for (int i = 0; i < e.length; i++)
			einv[e[i]] = i;
		for (int i = 0; i < SBOX.length; i++) {
			int left = e[i >>> 4];
			int right = einv[i & 0xF];
			int temp = r[left ^ right];
			SBOX[i] = (e[left ^ temp] << 4 | einv[right ^ temp]);
		}
		
		// Round constants
		RCON = new long[ROUNDS][8];
		for (int i = 0; i < RCON.length; i++) {
			for (int j = 0; j < 8; j++)
				RCON[i][0] |= (long)SBOX[8 * i + j] << ((7 - j) * 8);
			for (int j = 1; j < 8; j++)
				RCON[i][j] = 0;
		}
		
		// Multiplication tables
		int[] FACTORS = {0x01, 0x01, 0x04, 0x01, 0x08, 0x05, 0x02, 0x09};  // Pseudo-reversed
		MUL = new long[256 * 8];
		for (int i = 0; i < 256; i++) {
			long vector = 0;
			for (int j = 0; j < 8; j++)
				vector |= multiply(SBOX[i], FACTORS[j]) << ((7 - j) * 8);
			for (int j = 0; j < 8; j++)
				MUL[j * 256 + i] = Long.rotateRight(vector, j * 8);
		}
	}
	
	
	private static long multiply(int x, int y) {
		if ((x & 0xFF) != x || (y & 0xFF) != y)
			throw new IllegalArgumentException();
		int z = 0;
		for (; y != 0; y >>>= 1) {
			z ^= (y & 1) * x;
			x = (x << 1) ^ ((x >>> 7) * 0x11D);
		}
		return z;
	}
	
}
