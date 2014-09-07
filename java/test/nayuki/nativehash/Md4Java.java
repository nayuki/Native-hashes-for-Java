package nayuki.nativehash;


final class Md4Java extends Md4 {
	
	protected void compress(byte[] msg, int off, int len) {
		int a = state[0];
		int b = state[1];
		int c = state[2];
		int d = state[3];
		
		for (int i = off, end = off + len; i < end; i += 64) {
			int sch00 = (msg[i +  0] & 0xFF) | (msg[i +  1] & 0xFF) << 8 | (msg[i +  2] & 0xFF) << 16 | msg[i +  3] << 24;
			int sch01 = (msg[i +  4] & 0xFF) | (msg[i +  5] & 0xFF) << 8 | (msg[i +  6] & 0xFF) << 16 | msg[i +  7] << 24;
			int sch02 = (msg[i +  8] & 0xFF) | (msg[i +  9] & 0xFF) << 8 | (msg[i + 10] & 0xFF) << 16 | msg[i + 11] << 24;
			int sch03 = (msg[i + 12] & 0xFF) | (msg[i + 13] & 0xFF) << 8 | (msg[i + 14] & 0xFF) << 16 | msg[i + 15] << 24;
			int sch04 = (msg[i + 16] & 0xFF) | (msg[i + 17] & 0xFF) << 8 | (msg[i + 18] & 0xFF) << 16 | msg[i + 19] << 24;
			int sch05 = (msg[i + 20] & 0xFF) | (msg[i + 21] & 0xFF) << 8 | (msg[i + 22] & 0xFF) << 16 | msg[i + 23] << 24;
			int sch06 = (msg[i + 24] & 0xFF) | (msg[i + 25] & 0xFF) << 8 | (msg[i + 26] & 0xFF) << 16 | msg[i + 27] << 24;
			int sch07 = (msg[i + 28] & 0xFF) | (msg[i + 29] & 0xFF) << 8 | (msg[i + 30] & 0xFF) << 16 | msg[i + 31] << 24;
			int sch08 = (msg[i + 32] & 0xFF) | (msg[i + 33] & 0xFF) << 8 | (msg[i + 34] & 0xFF) << 16 | msg[i + 35] << 24;
			int sch09 = (msg[i + 36] & 0xFF) | (msg[i + 37] & 0xFF) << 8 | (msg[i + 38] & 0xFF) << 16 | msg[i + 39] << 24;
			int sch10 = (msg[i + 40] & 0xFF) | (msg[i + 41] & 0xFF) << 8 | (msg[i + 42] & 0xFF) << 16 | msg[i + 43] << 24;
			int sch11 = (msg[i + 44] & 0xFF) | (msg[i + 45] & 0xFF) << 8 | (msg[i + 46] & 0xFF) << 16 | msg[i + 47] << 24;
			int sch12 = (msg[i + 48] & 0xFF) | (msg[i + 49] & 0xFF) << 8 | (msg[i + 50] & 0xFF) << 16 | msg[i + 51] << 24;
			int sch13 = (msg[i + 52] & 0xFF) | (msg[i + 53] & 0xFF) << 8 | (msg[i + 54] & 0xFF) << 16 | msg[i + 55] << 24;
			int sch14 = (msg[i + 56] & 0xFF) | (msg[i + 57] & 0xFF) << 8 | (msg[i + 58] & 0xFF) << 16 | msg[i + 59] << 24;
			int sch15 = (msg[i + 60] & 0xFF) | (msg[i + 61] & 0xFF) << 8 | (msg[i + 62] & 0xFF) << 16 | msg[i + 63] << 24;
			
			a += (d ^ (b & (c ^ d))) + sch00;  a = a <<  3 | a >>> 29;
			d += (c ^ (a & (b ^ c))) + sch01;  d = d <<  7 | d >>> 25;
			c += (b ^ (d & (a ^ b))) + sch02;  c = c << 11 | c >>> 21;
			b += (a ^ (c & (d ^ a))) + sch03;  b = b << 19 | b >>> 13;
			a += (d ^ (b & (c ^ d))) + sch04;  a = a <<  3 | a >>> 29;
			d += (c ^ (a & (b ^ c))) + sch05;  d = d <<  7 | d >>> 25;
			c += (b ^ (d & (a ^ b))) + sch06;  c = c << 11 | c >>> 21;
			b += (a ^ (c & (d ^ a))) + sch07;  b = b << 19 | b >>> 13;
			a += (d ^ (b & (c ^ d))) + sch08;  a = a <<  3 | a >>> 29;
			d += (c ^ (a & (b ^ c))) + sch09;  d = d <<  7 | d >>> 25;
			c += (b ^ (d & (a ^ b))) + sch10;  c = c << 11 | c >>> 21;
			b += (a ^ (c & (d ^ a))) + sch11;  b = b << 19 | b >>> 13;
			a += (d ^ (b & (c ^ d))) + sch12;  a = a <<  3 | a >>> 29;
			d += (c ^ (a & (b ^ c))) + sch13;  d = d <<  7 | d >>> 25;
			c += (b ^ (d & (a ^ b))) + sch14;  c = c << 11 | c >>> 21;
			b += (a ^ (c & (d ^ a))) + sch15;  b = b << 19 | b >>> 13;
			a += ((b & c) | (d & (b | c))) + sch00 + 0x5A827999;  a = a <<  3 | a >>> 29;
			d += ((a & b) | (c & (a | b))) + sch04 + 0x5A827999;  d = d <<  5 | d >>> 27;
			c += ((d & a) | (b & (d | a))) + sch08 + 0x5A827999;  c = c <<  9 | c >>> 23;
			b += ((c & d) | (a & (c | d))) + sch12 + 0x5A827999;  b = b << 13 | b >>> 19;
			a += ((b & c) | (d & (b | c))) + sch01 + 0x5A827999;  a = a <<  3 | a >>> 29;
			d += ((a & b) | (c & (a | b))) + sch05 + 0x5A827999;  d = d <<  5 | d >>> 27;
			c += ((d & a) | (b & (d | a))) + sch09 + 0x5A827999;  c = c <<  9 | c >>> 23;
			b += ((c & d) | (a & (c | d))) + sch13 + 0x5A827999;  b = b << 13 | b >>> 19;
			a += ((b & c) | (d & (b | c))) + sch02 + 0x5A827999;  a = a <<  3 | a >>> 29;
			d += ((a & b) | (c & (a | b))) + sch06 + 0x5A827999;  d = d <<  5 | d >>> 27;
			c += ((d & a) | (b & (d | a))) + sch10 + 0x5A827999;  c = c <<  9 | c >>> 23;
			b += ((c & d) | (a & (c | d))) + sch14 + 0x5A827999;  b = b << 13 | b >>> 19;
			a += ((b & c) | (d & (b | c))) + sch03 + 0x5A827999;  a = a <<  3 | a >>> 29;
			d += ((a & b) | (c & (a | b))) + sch07 + 0x5A827999;  d = d <<  5 | d >>> 27;
			c += ((d & a) | (b & (d | a))) + sch11 + 0x5A827999;  c = c <<  9 | c >>> 23;
			b += ((c & d) | (a & (c | d))) + sch15 + 0x5A827999;  b = b << 13 | b >>> 19;
			a += (b ^ c ^ d) + sch00 + 0x6ED9EBA1;  a = a <<  3 | a >>> 29;
			d += (a ^ b ^ c) + sch08 + 0x6ED9EBA1;  d = d <<  9 | d >>> 23;
			c += (d ^ a ^ b) + sch04 + 0x6ED9EBA1;  c = c << 11 | c >>> 21;
			b += (c ^ d ^ a) + sch12 + 0x6ED9EBA1;  b = b << 15 | b >>> 17;
			a += (b ^ c ^ d) + sch02 + 0x6ED9EBA1;  a = a <<  3 | a >>> 29;
			d += (a ^ b ^ c) + sch10 + 0x6ED9EBA1;  d = d <<  9 | d >>> 23;
			c += (d ^ a ^ b) + sch06 + 0x6ED9EBA1;  c = c << 11 | c >>> 21;
			b += (c ^ d ^ a) + sch14 + 0x6ED9EBA1;  b = b << 15 | b >>> 17;
			a += (b ^ c ^ d) + sch01 + 0x6ED9EBA1;  a = a <<  3 | a >>> 29;
			d += (a ^ b ^ c) + sch09 + 0x6ED9EBA1;  d = d <<  9 | d >>> 23;
			c += (d ^ a ^ b) + sch05 + 0x6ED9EBA1;  c = c << 11 | c >>> 21;
			b += (c ^ d ^ a) + sch13 + 0x6ED9EBA1;  b = b << 15 | b >>> 17;
			a += (b ^ c ^ d) + sch03 + 0x6ED9EBA1;  a = a <<  3 | a >>> 29;
			d += (a ^ b ^ c) + sch11 + 0x6ED9EBA1;  d = d <<  9 | d >>> 23;
			c += (d ^ a ^ b) + sch07 + 0x6ED9EBA1;  c = c << 11 | c >>> 21;
			b += (c ^ d ^ a) + sch15 + 0x6ED9EBA1;  b = b << 15 | b >>> 17;
			
			a = state[0] += a;
			b = state[1] += b;
			c = state[2] += c;
			d = state[3] += d;
		}
	}
	
}
