package nayuki.nativehash;

import static java.lang.Integer.rotateRight;


final class Sha256Java extends Sha256 {
	
	protected void compress(byte[] msg, int off, int len) {
		int a = state[0];
		int b = state[1];
		int c = state[2];
		int d = state[3];
		int e = state[4];
		int f = state[5];
		int g = state[6];
		int h = state[7];
		
		int[] sch = new int[64];
		for (int i = off, end = off + len; i < end; ) {
			for (int j = 0; j < 16; j++, i += 4)
				sch[j] = msg[i] << 24 | (msg[i + 1] & 0xFF) << 16 | (msg[i + 2] & 0xFF) << 8 | (msg[i + 3] & 0xFF);
			
			for (int j = 16; j < 64; j++) {
				int x = sch[j - 15];
				int y = sch[j - 2];
				sch[j] = sch[j - 16] + sch[j - 7] + (rotateRight(x, 7) ^ rotateRight(x, 18) ^ (x >>> 3)) + (rotateRight(y, 17) ^ rotateRight(y, 19) ^ (y >>> 10));
			}
			
			for (int j = 0; j < 64; j += 8) {
				h += (rotateRight(e, 6) ^ rotateRight(e, 11) ^ rotateRight(e, 25)) + (g ^ (e & (f ^ g))) + K[j + 0] + sch[j + 0];  d += h;  h += (rotateRight(a, 2) ^ rotateRight(a, 13) ^ rotateRight(a, 22)) + ((a & (b | c)) | (b & c));
				g += (rotateRight(d, 6) ^ rotateRight(d, 11) ^ rotateRight(d, 25)) + (f ^ (d & (e ^ f))) + K[j + 1] + sch[j + 1];  c += g;  g += (rotateRight(h, 2) ^ rotateRight(h, 13) ^ rotateRight(h, 22)) + ((h & (a | b)) | (a & b));
				f += (rotateRight(c, 6) ^ rotateRight(c, 11) ^ rotateRight(c, 25)) + (e ^ (c & (d ^ e))) + K[j + 2] + sch[j + 2];  b += f;  f += (rotateRight(g, 2) ^ rotateRight(g, 13) ^ rotateRight(g, 22)) + ((g & (h | a)) | (h & a));
				e += (rotateRight(b, 6) ^ rotateRight(b, 11) ^ rotateRight(b, 25)) + (d ^ (b & (c ^ d))) + K[j + 3] + sch[j + 3];  a += e;  e += (rotateRight(f, 2) ^ rotateRight(f, 13) ^ rotateRight(f, 22)) + ((f & (g | h)) | (g & h));
				d += (rotateRight(a, 6) ^ rotateRight(a, 11) ^ rotateRight(a, 25)) + (c ^ (a & (b ^ c))) + K[j + 4] + sch[j + 4];  h += d;  d += (rotateRight(e, 2) ^ rotateRight(e, 13) ^ rotateRight(e, 22)) + ((e & (f | g)) | (f & g));
				c += (rotateRight(h, 6) ^ rotateRight(h, 11) ^ rotateRight(h, 25)) + (b ^ (h & (a ^ b))) + K[j + 5] + sch[j + 5];  g += c;  c += (rotateRight(d, 2) ^ rotateRight(d, 13) ^ rotateRight(d, 22)) + ((d & (e | f)) | (e & f));
				b += (rotateRight(g, 6) ^ rotateRight(g, 11) ^ rotateRight(g, 25)) + (a ^ (g & (h ^ a))) + K[j + 6] + sch[j + 6];  f += b;  b += (rotateRight(c, 2) ^ rotateRight(c, 13) ^ rotateRight(c, 22)) + ((c & (d | e)) | (d & e));
				a += (rotateRight(f, 6) ^ rotateRight(f, 11) ^ rotateRight(f, 25)) + (h ^ (f & (g ^ h))) + K[j + 7] + sch[j + 7];  e += a;  a += (rotateRight(b, 2) ^ rotateRight(b, 13) ^ rotateRight(b, 22)) + ((b & (c | d)) | (c & d));
			}
			a = state[0] += a;
			b = state[1] += b;
			c = state[2] += c;
			d = state[3] += d;
			e = state[4] += e;
			f = state[5] += f;
			g = state[6] += g;
			h = state[7] += h;
		}
	}
	
	
	private static final int[] K = {
		0x428A2F98, 0x71374491, 0xB5C0FBCF, 0xE9B5DBA5,
		0x3956C25B, 0x59F111F1, 0x923F82A4, 0xAB1C5ED5,
		0xD807AA98, 0x12835B01, 0x243185BE, 0x550C7DC3,
		0x72BE5D74, 0x80DEB1FE, 0x9BDC06A7, 0xC19BF174,
		0xE49B69C1, 0xEFBE4786, 0x0FC19DC6, 0x240CA1CC,
		0x2DE92C6F, 0x4A7484AA, 0x5CB0A9DC, 0x76F988DA,
		0x983E5152, 0xA831C66D, 0xB00327C8, 0xBF597FC7,
		0xC6E00BF3, 0xD5A79147, 0x06CA6351, 0x14292967,
		0x27B70A85, 0x2E1B2138, 0x4D2C6DFC, 0x53380D13,
		0x650A7354, 0x766A0ABB, 0x81C2C92E, 0x92722C85,
		0xA2BFE8A1, 0xA81A664B, 0xC24B8B70, 0xC76C51A3,
		0xD192E819, 0xD6990624, 0xF40E3585, 0x106AA070,
		0x19A4C116, 0x1E376C08, 0x2748774C, 0x34B0BCB5,
		0x391C0CB3, 0x4ED8AA4A, 0x5B9CCA4F, 0x682E6FF3,
		0x748F82EE, 0x78A5636F, 0x84C87814, 0x8CC70208,
		0x90BEFFFA, 0xA4506CEB, 0xBEF9A3F7, 0xC67178F2
	};
	
}
