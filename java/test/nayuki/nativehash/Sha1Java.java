package nayuki.nativehash;


final class Sha1Java extends Sha1 {
	
	private static final int K0 = 0x5A827999;
	private static final int K1 = 0x6ED9EBA1;
	private static final int K2 = 0x8F1BBCDC;
	private static final int K3 = 0xCA62C1D6;
	
	
	protected void compress(byte[] msg, int off, int len) {
		int a = state[0];
		int b = state[1];
		int c = state[2];
		int d = state[3];
		int e = state[4];
		
		for (int i = off, end = off + len; i < end; i += 64) {
			int sch00 = msg[i +  0] << 24 | (msg[i +  1] & 0xFF) << 16 | (msg[i +  2] & 0xFF) << 8 | (msg[i +  3] & 0xFF);
			int sch01 = msg[i +  4] << 24 | (msg[i +  5] & 0xFF) << 16 | (msg[i +  6] & 0xFF) << 8 | (msg[i +  7] & 0xFF);
			int sch02 = msg[i +  8] << 24 | (msg[i +  9] & 0xFF) << 16 | (msg[i + 10] & 0xFF) << 8 | (msg[i + 11] & 0xFF);
			int sch03 = msg[i + 12] << 24 | (msg[i + 13] & 0xFF) << 16 | (msg[i + 14] & 0xFF) << 8 | (msg[i + 15] & 0xFF);
			int sch04 = msg[i + 16] << 24 | (msg[i + 17] & 0xFF) << 16 | (msg[i + 18] & 0xFF) << 8 | (msg[i + 19] & 0xFF);
			int sch05 = msg[i + 20] << 24 | (msg[i + 21] & 0xFF) << 16 | (msg[i + 22] & 0xFF) << 8 | (msg[i + 23] & 0xFF);
			int sch06 = msg[i + 24] << 24 | (msg[i + 25] & 0xFF) << 16 | (msg[i + 26] & 0xFF) << 8 | (msg[i + 27] & 0xFF);
			int sch07 = msg[i + 28] << 24 | (msg[i + 29] & 0xFF) << 16 | (msg[i + 30] & 0xFF) << 8 | (msg[i + 31] & 0xFF);
			int sch08 = msg[i + 32] << 24 | (msg[i + 33] & 0xFF) << 16 | (msg[i + 34] & 0xFF) << 8 | (msg[i + 35] & 0xFF);
			int sch09 = msg[i + 36] << 24 | (msg[i + 37] & 0xFF) << 16 | (msg[i + 38] & 0xFF) << 8 | (msg[i + 39] & 0xFF);
			int sch10 = msg[i + 40] << 24 | (msg[i + 41] & 0xFF) << 16 | (msg[i + 42] & 0xFF) << 8 | (msg[i + 43] & 0xFF);
			int sch11 = msg[i + 44] << 24 | (msg[i + 45] & 0xFF) << 16 | (msg[i + 46] & 0xFF) << 8 | (msg[i + 47] & 0xFF);
			int sch12 = msg[i + 48] << 24 | (msg[i + 49] & 0xFF) << 16 | (msg[i + 50] & 0xFF) << 8 | (msg[i + 51] & 0xFF);
			int sch13 = msg[i + 52] << 24 | (msg[i + 53] & 0xFF) << 16 | (msg[i + 54] & 0xFF) << 8 | (msg[i + 55] & 0xFF);
			int sch14 = msg[i + 56] << 24 | (msg[i + 57] & 0xFF) << 16 | (msg[i + 58] & 0xFF) << 8 | (msg[i + 59] & 0xFF);
			int sch15 = msg[i + 60] << 24 | (msg[i + 61] & 0xFF) << 16 | (msg[i + 62] & 0xFF) << 8 | (msg[i + 63] & 0xFF);
			
			int t;
			e += (a << 5 | a >>> 27) + (d ^ (b & (c ^ d))) + sch00 + K0;  b = b << 30 | b >>> 2;
			d += (e << 5 | e >>> 27) + (c ^ (a & (b ^ c))) + sch01 + K0;  a = a << 30 | a >>> 2;
			c += (d << 5 | d >>> 27) + (b ^ (e & (a ^ b))) + sch02 + K0;  e = e << 30 | e >>> 2;
			b += (c << 5 | c >>> 27) + (a ^ (d & (e ^ a))) + sch03 + K0;  d = d << 30 | d >>> 2;
			a += (b << 5 | b >>> 27) + (e ^ (c & (d ^ e))) + sch04 + K0;  c = c << 30 | c >>> 2;
			e += (a << 5 | a >>> 27) + (d ^ (b & (c ^ d))) + sch05 + K0;  b = b << 30 | b >>> 2;
			d += (e << 5 | e >>> 27) + (c ^ (a & (b ^ c))) + sch06 + K0;  a = a << 30 | a >>> 2;
			c += (d << 5 | d >>> 27) + (b ^ (e & (a ^ b))) + sch07 + K0;  e = e << 30 | e >>> 2;
			b += (c << 5 | c >>> 27) + (a ^ (d & (e ^ a))) + sch08 + K0;  d = d << 30 | d >>> 2;
			a += (b << 5 | b >>> 27) + (e ^ (c & (d ^ e))) + sch09 + K0;  c = c << 30 | c >>> 2;
			e += (a << 5 | a >>> 27) + (d ^ (b & (c ^ d))) + sch10 + K0;  b = b << 30 | b >>> 2;
			d += (e << 5 | e >>> 27) + (c ^ (a & (b ^ c))) + sch11 + K0;  a = a << 30 | a >>> 2;
			c += (d << 5 | d >>> 27) + (b ^ (e & (a ^ b))) + sch12 + K0;  e = e << 30 | e >>> 2;
			b += (c << 5 | c >>> 27) + (a ^ (d & (e ^ a))) + sch13 + K0;  d = d << 30 | d >>> 2;
			a += (b << 5 | b >>> 27) + (e ^ (c & (d ^ e))) + sch14 + K0;  c = c << 30 | c >>> 2;
			e += (a << 5 | a >>> 27) + (d ^ (b & (c ^ d))) + sch15 + K0;  b = b << 30 | b >>> 2;
			t = sch13 ^ sch08 ^ sch02 ^ sch00;  sch00 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + (c ^ (a & (b ^ c))) + sch00 + K0;  a = a << 30 | a >>> 2;
			t = sch14 ^ sch09 ^ sch03 ^ sch01;  sch01 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + (b ^ (e & (a ^ b))) + sch01 + K0;  e = e << 30 | e >>> 2;
			t = sch15 ^ sch10 ^ sch04 ^ sch02;  sch02 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + (a ^ (d & (e ^ a))) + sch02 + K0;  d = d << 30 | d >>> 2;
			t = sch00 ^ sch11 ^ sch05 ^ sch03;  sch03 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + (e ^ (c & (d ^ e))) + sch03 + K0;  c = c << 30 | c >>> 2;
			t = sch01 ^ sch12 ^ sch06 ^ sch04;  sch04 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + (b ^ c ^ d) + sch04 + K1;  b = b << 30 | b >>> 2;
			t = sch02 ^ sch13 ^ sch07 ^ sch05;  sch05 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + (a ^ b ^ c) + sch05 + K1;  a = a << 30 | a >>> 2;
			t = sch03 ^ sch14 ^ sch08 ^ sch06;  sch06 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + (e ^ a ^ b) + sch06 + K1;  e = e << 30 | e >>> 2;
			t = sch04 ^ sch15 ^ sch09 ^ sch07;  sch07 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + (d ^ e ^ a) + sch07 + K1;  d = d << 30 | d >>> 2;
			t = sch05 ^ sch00 ^ sch10 ^ sch08;  sch08 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + (c ^ d ^ e) + sch08 + K1;  c = c << 30 | c >>> 2;
			t = sch06 ^ sch01 ^ sch11 ^ sch09;  sch09 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + (b ^ c ^ d) + sch09 + K1;  b = b << 30 | b >>> 2;
			t = sch07 ^ sch02 ^ sch12 ^ sch10;  sch10 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + (a ^ b ^ c) + sch10 + K1;  a = a << 30 | a >>> 2;
			t = sch08 ^ sch03 ^ sch13 ^ sch11;  sch11 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + (e ^ a ^ b) + sch11 + K1;  e = e << 30 | e >>> 2;
			t = sch09 ^ sch04 ^ sch14 ^ sch12;  sch12 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + (d ^ e ^ a) + sch12 + K1;  d = d << 30 | d >>> 2;
			t = sch10 ^ sch05 ^ sch15 ^ sch13;  sch13 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + (c ^ d ^ e) + sch13 + K1;  c = c << 30 | c >>> 2;
			t = sch11 ^ sch06 ^ sch00 ^ sch14;  sch14 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + (b ^ c ^ d) + sch14 + K1;  b = b << 30 | b >>> 2;
			t = sch12 ^ sch07 ^ sch01 ^ sch15;  sch15 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + (a ^ b ^ c) + sch15 + K1;  a = a << 30 | a >>> 2;
			t = sch13 ^ sch08 ^ sch02 ^ sch00;  sch00 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + (e ^ a ^ b) + sch00 + K1;  e = e << 30 | e >>> 2;
			t = sch14 ^ sch09 ^ sch03 ^ sch01;  sch01 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + (d ^ e ^ a) + sch01 + K1;  d = d << 30 | d >>> 2;
			t = sch15 ^ sch10 ^ sch04 ^ sch02;  sch02 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + (c ^ d ^ e) + sch02 + K1;  c = c << 30 | c >>> 2;
			t = sch00 ^ sch11 ^ sch05 ^ sch03;  sch03 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + (b ^ c ^ d) + sch03 + K1;  b = b << 30 | b >>> 2;
			t = sch01 ^ sch12 ^ sch06 ^ sch04;  sch04 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + (a ^ b ^ c) + sch04 + K1;  a = a << 30 | a >>> 2;
			t = sch02 ^ sch13 ^ sch07 ^ sch05;  sch05 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + (e ^ a ^ b) + sch05 + K1;  e = e << 30 | e >>> 2;
			t = sch03 ^ sch14 ^ sch08 ^ sch06;  sch06 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + (d ^ e ^ a) + sch06 + K1;  d = d << 30 | d >>> 2;
			t = sch04 ^ sch15 ^ sch09 ^ sch07;  sch07 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + (c ^ d ^ e) + sch07 + K1;  c = c << 30 | c >>> 2;
			t = sch05 ^ sch00 ^ sch10 ^ sch08;  sch08 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + ((b & (c | d)) | (c & d)) + sch08 + K2;  b = b << 30 | b >>> 2;
			t = sch06 ^ sch01 ^ sch11 ^ sch09;  sch09 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + ((a & (b | c)) | (b & c)) + sch09 + K2;  a = a << 30 | a >>> 2;
			t = sch07 ^ sch02 ^ sch12 ^ sch10;  sch10 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + ((e & (a | b)) | (a & b)) + sch10 + K2;  e = e << 30 | e >>> 2;
			t = sch08 ^ sch03 ^ sch13 ^ sch11;  sch11 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + ((d & (e | a)) | (e & a)) + sch11 + K2;  d = d << 30 | d >>> 2;
			t = sch09 ^ sch04 ^ sch14 ^ sch12;  sch12 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + ((c & (d | e)) | (d & e)) + sch12 + K2;  c = c << 30 | c >>> 2;
			t = sch10 ^ sch05 ^ sch15 ^ sch13;  sch13 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + ((b & (c | d)) | (c & d)) + sch13 + K2;  b = b << 30 | b >>> 2;
			t = sch11 ^ sch06 ^ sch00 ^ sch14;  sch14 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + ((a & (b | c)) | (b & c)) + sch14 + K2;  a = a << 30 | a >>> 2;
			t = sch12 ^ sch07 ^ sch01 ^ sch15;  sch15 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + ((e & (a | b)) | (a & b)) + sch15 + K2;  e = e << 30 | e >>> 2;
			t = sch13 ^ sch08 ^ sch02 ^ sch00;  sch00 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + ((d & (e | a)) | (e & a)) + sch00 + K2;  d = d << 30 | d >>> 2;
			t = sch14 ^ sch09 ^ sch03 ^ sch01;  sch01 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + ((c & (d | e)) | (d & e)) + sch01 + K2;  c = c << 30 | c >>> 2;
			t = sch15 ^ sch10 ^ sch04 ^ sch02;  sch02 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + ((b & (c | d)) | (c & d)) + sch02 + K2;  b = b << 30 | b >>> 2;
			t = sch00 ^ sch11 ^ sch05 ^ sch03;  sch03 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + ((a & (b | c)) | (b & c)) + sch03 + K2;  a = a << 30 | a >>> 2;
			t = sch01 ^ sch12 ^ sch06 ^ sch04;  sch04 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + ((e & (a | b)) | (a & b)) + sch04 + K2;  e = e << 30 | e >>> 2;
			t = sch02 ^ sch13 ^ sch07 ^ sch05;  sch05 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + ((d & (e | a)) | (e & a)) + sch05 + K2;  d = d << 30 | d >>> 2;
			t = sch03 ^ sch14 ^ sch08 ^ sch06;  sch06 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + ((c & (d | e)) | (d & e)) + sch06 + K2;  c = c << 30 | c >>> 2;
			t = sch04 ^ sch15 ^ sch09 ^ sch07;  sch07 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + ((b & (c | d)) | (c & d)) + sch07 + K2;  b = b << 30 | b >>> 2;
			t = sch05 ^ sch00 ^ sch10 ^ sch08;  sch08 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + ((a & (b | c)) | (b & c)) + sch08 + K2;  a = a << 30 | a >>> 2;
			t = sch06 ^ sch01 ^ sch11 ^ sch09;  sch09 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + ((e & (a | b)) | (a & b)) + sch09 + K2;  e = e << 30 | e >>> 2;
			t = sch07 ^ sch02 ^ sch12 ^ sch10;  sch10 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + ((d & (e | a)) | (e & a)) + sch10 + K2;  d = d << 30 | d >>> 2;
			t = sch08 ^ sch03 ^ sch13 ^ sch11;  sch11 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + ((c & (d | e)) | (d & e)) + sch11 + K2;  c = c << 30 | c >>> 2;
			t = sch09 ^ sch04 ^ sch14 ^ sch12;  sch12 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + (b ^ c ^ d) + sch12 + K3;  b = b << 30 | b >>> 2;
			t = sch10 ^ sch05 ^ sch15 ^ sch13;  sch13 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + (a ^ b ^ c) + sch13 + K3;  a = a << 30 | a >>> 2;
			t = sch11 ^ sch06 ^ sch00 ^ sch14;  sch14 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + (e ^ a ^ b) + sch14 + K3;  e = e << 30 | e >>> 2;
			t = sch12 ^ sch07 ^ sch01 ^ sch15;  sch15 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + (d ^ e ^ a) + sch15 + K3;  d = d << 30 | d >>> 2;
			t = sch13 ^ sch08 ^ sch02 ^ sch00;  sch00 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + (c ^ d ^ e) + sch00 + K3;  c = c << 30 | c >>> 2;
			t = sch14 ^ sch09 ^ sch03 ^ sch01;  sch01 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + (b ^ c ^ d) + sch01 + K3;  b = b << 30 | b >>> 2;
			t = sch15 ^ sch10 ^ sch04 ^ sch02;  sch02 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + (a ^ b ^ c) + sch02 + K3;  a = a << 30 | a >>> 2;
			t = sch00 ^ sch11 ^ sch05 ^ sch03;  sch03 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + (e ^ a ^ b) + sch03 + K3;  e = e << 30 | e >>> 2;
			t = sch01 ^ sch12 ^ sch06 ^ sch04;  sch04 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + (d ^ e ^ a) + sch04 + K3;  d = d << 30 | d >>> 2;
			t = sch02 ^ sch13 ^ sch07 ^ sch05;  sch05 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + (c ^ d ^ e) + sch05 + K3;  c = c << 30 | c >>> 2;
			t = sch03 ^ sch14 ^ sch08 ^ sch06;  sch06 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + (b ^ c ^ d) + sch06 + K3;  b = b << 30 | b >>> 2;
			t = sch04 ^ sch15 ^ sch09 ^ sch07;  sch07 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + (a ^ b ^ c) + sch07 + K3;  a = a << 30 | a >>> 2;
			t = sch05 ^ sch00 ^ sch10 ^ sch08;  sch08 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + (e ^ a ^ b) + sch08 + K3;  e = e << 30 | e >>> 2;
			t = sch06 ^ sch01 ^ sch11 ^ sch09;  sch09 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + (d ^ e ^ a) + sch09 + K3;  d = d << 30 | d >>> 2;
			t = sch07 ^ sch02 ^ sch12 ^ sch10;  sch10 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + (c ^ d ^ e) + sch10 + K3;  c = c << 30 | c >>> 2;
			t = sch08 ^ sch03 ^ sch13 ^ sch11;  sch11 = t << 1 | t >>> 31;  e += (a << 5 | a >>> 27) + (b ^ c ^ d) + sch11 + K3;  b = b << 30 | b >>> 2;
			t = sch09 ^ sch04 ^ sch14 ^ sch12;  sch12 = t << 1 | t >>> 31;  d += (e << 5 | e >>> 27) + (a ^ b ^ c) + sch12 + K3;  a = a << 30 | a >>> 2;
			t = sch10 ^ sch05 ^ sch15 ^ sch13;  sch13 = t << 1 | t >>> 31;  c += (d << 5 | d >>> 27) + (e ^ a ^ b) + sch13 + K3;  e = e << 30 | e >>> 2;
			t = sch11 ^ sch06 ^ sch00 ^ sch14;  sch14 = t << 1 | t >>> 31;  b += (c << 5 | c >>> 27) + (d ^ e ^ a) + sch14 + K3;  d = d << 30 | d >>> 2;
			t = sch12 ^ sch07 ^ sch01 ^ sch15;  sch15 = t << 1 | t >>> 31;  a += (b << 5 | b >>> 27) + (c ^ d ^ e) + sch15 + K3;  c = c << 30 | c >>> 2;
			
			a = state[0] += a;
			b = state[1] += b;
			c = state[2] += c;
			d = state[3] += d;
			e = state[4] += e;
		}
	}
	
}
