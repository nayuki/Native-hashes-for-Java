/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Nayuki Minase
 * http://nayuki.eigenstate.org/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


final class Md5Java extends Md5 {
	
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
			
			a += (d ^ (b & (c ^ d))) + 0xD76AA478 + sch00;  a = b + (a <<  7 | a >>> 25);
			d += (c ^ (a & (b ^ c))) + 0xE8C7B756 + sch01;  d = a + (d << 12 | d >>> 20);
			c += (b ^ (d & (a ^ b))) + 0x242070DB + sch02;  c = d + (c << 17 | c >>> 15);
			b += (a ^ (c & (d ^ a))) + 0xC1BDCEEE + sch03;  b = c + (b << 22 | b >>> 10);
			a += (d ^ (b & (c ^ d))) + 0xF57C0FAF + sch04;  a = b + (a <<  7 | a >>> 25);
			d += (c ^ (a & (b ^ c))) + 0x4787C62A + sch05;  d = a + (d << 12 | d >>> 20);
			c += (b ^ (d & (a ^ b))) + 0xA8304613 + sch06;  c = d + (c << 17 | c >>> 15);
			b += (a ^ (c & (d ^ a))) + 0xFD469501 + sch07;  b = c + (b << 22 | b >>> 10);
			a += (d ^ (b & (c ^ d))) + 0x698098D8 + sch08;  a = b + (a <<  7 | a >>> 25);
			d += (c ^ (a & (b ^ c))) + 0x8B44F7AF + sch09;  d = a + (d << 12 | d >>> 20);
			c += (b ^ (d & (a ^ b))) + 0xFFFF5BB1 + sch10;  c = d + (c << 17 | c >>> 15);
			b += (a ^ (c & (d ^ a))) + 0x895CD7BE + sch11;  b = c + (b << 22 | b >>> 10);
			a += (d ^ (b & (c ^ d))) + 0x6B901122 + sch12;  a = b + (a <<  7 | a >>> 25);
			d += (c ^ (a & (b ^ c))) + 0xFD987193 + sch13;  d = a + (d << 12 | d >>> 20);
			c += (b ^ (d & (a ^ b))) + 0xA679438E + sch14;  c = d + (c << 17 | c >>> 15);
			b += (a ^ (c & (d ^ a))) + 0x49B40821 + sch15;  b = c + (b << 22 | b >>> 10);
			a += (c ^ (d & (b ^ c))) + 0xF61E2562 + sch01;  a = b + (a <<  5 | a >>> 27);
			d += (b ^ (c & (a ^ b))) + 0xC040B340 + sch06;  d = a + (d <<  9 | d >>> 23);
			c += (a ^ (b & (d ^ a))) + 0x265E5A51 + sch11;  c = d + (c << 14 | c >>> 18);
			b += (d ^ (a & (c ^ d))) + 0xE9B6C7AA + sch00;  b = c + (b << 20 | b >>> 12);
			a += (c ^ (d & (b ^ c))) + 0xD62F105D + sch05;  a = b + (a <<  5 | a >>> 27);
			d += (b ^ (c & (a ^ b))) + 0x02441453 + sch10;  d = a + (d <<  9 | d >>> 23);
			c += (a ^ (b & (d ^ a))) + 0xD8A1E681 + sch15;  c = d + (c << 14 | c >>> 18);
			b += (d ^ (a & (c ^ d))) + 0xE7D3FBC8 + sch04;  b = c + (b << 20 | b >>> 12);
			a += (c ^ (d & (b ^ c))) + 0x21E1CDE6 + sch09;  a = b + (a <<  5 | a >>> 27);
			d += (b ^ (c & (a ^ b))) + 0xC33707D6 + sch14;  d = a + (d <<  9 | d >>> 23);
			c += (a ^ (b & (d ^ a))) + 0xF4D50D87 + sch03;  c = d + (c << 14 | c >>> 18);
			b += (d ^ (a & (c ^ d))) + 0x455A14ED + sch08;  b = c + (b << 20 | b >>> 12);
			a += (c ^ (d & (b ^ c))) + 0xA9E3E905 + sch13;  a = b + (a <<  5 | a >>> 27);
			d += (b ^ (c & (a ^ b))) + 0xFCEFA3F8 + sch02;  d = a + (d <<  9 | d >>> 23);
			c += (a ^ (b & (d ^ a))) + 0x676F02D9 + sch07;  c = d + (c << 14 | c >>> 18);
			b += (d ^ (a & (c ^ d))) + 0x8D2A4C8A + sch12;  b = c + (b << 20 | b >>> 12);
			a += (b ^ c ^ d) + 0xFFFA3942 + sch05;  a = b + (a <<  4 | a >>> 28);
			d += (a ^ b ^ c) + 0x8771F681 + sch08;  d = a + (d << 11 | d >>> 21);
			c += (d ^ a ^ b) + 0x6D9D6122 + sch11;  c = d + (c << 16 | c >>> 16);
			b += (c ^ d ^ a) + 0xFDE5380C + sch14;  b = c + (b << 23 | b >>>  9);
			a += (b ^ c ^ d) + 0xA4BEEA44 + sch01;  a = b + (a <<  4 | a >>> 28);
			d += (a ^ b ^ c) + 0x4BDECFA9 + sch04;  d = a + (d << 11 | d >>> 21);
			c += (d ^ a ^ b) + 0xF6BB4B60 + sch07;  c = d + (c << 16 | c >>> 16);
			b += (c ^ d ^ a) + 0xBEBFBC70 + sch10;  b = c + (b << 23 | b >>>  9);
			a += (b ^ c ^ d) + 0x289B7EC6 + sch13;  a = b + (a <<  4 | a >>> 28);
			d += (a ^ b ^ c) + 0xEAA127FA + sch00;  d = a + (d << 11 | d >>> 21);
			c += (d ^ a ^ b) + 0xD4EF3085 + sch03;  c = d + (c << 16 | c >>> 16);
			b += (c ^ d ^ a) + 0x04881D05 + sch06;  b = c + (b << 23 | b >>>  9);
			a += (b ^ c ^ d) + 0xD9D4D039 + sch09;  a = b + (a <<  4 | a >>> 28);
			d += (a ^ b ^ c) + 0xE6DB99E5 + sch12;  d = a + (d << 11 | d >>> 21);
			c += (d ^ a ^ b) + 0x1FA27CF8 + sch15;  c = d + (c << 16 | c >>> 16);
			b += (c ^ d ^ a) + 0xC4AC5665 + sch02;  b = c + (b << 23 | b >>>  9);
			a += (c ^ (b | ~d)) + 0xF4292244 + sch00;  a = b + (a <<  6 | a >>> 26);
			d += (b ^ (a | ~c)) + 0x432AFF97 + sch07;  d = a + (d << 10 | d >>> 22);
			c += (a ^ (d | ~b)) + 0xAB9423A7 + sch14;  c = d + (c << 15 | c >>> 17);
			b += (d ^ (c | ~a)) + 0xFC93A039 + sch05;  b = c + (b << 21 | b >>> 11);
			a += (c ^ (b | ~d)) + 0x655B59C3 + sch12;  a = b + (a <<  6 | a >>> 26);
			d += (b ^ (a | ~c)) + 0x8F0CCC92 + sch03;  d = a + (d << 10 | d >>> 22);
			c += (a ^ (d | ~b)) + 0xFFEFF47D + sch10;  c = d + (c << 15 | c >>> 17);
			b += (d ^ (c | ~a)) + 0x85845DD1 + sch01;  b = c + (b << 21 | b >>> 11);
			a += (c ^ (b | ~d)) + 0x6FA87E4F + sch08;  a = b + (a <<  6 | a >>> 26);
			d += (b ^ (a | ~c)) + 0xFE2CE6E0 + sch15;  d = a + (d << 10 | d >>> 22);
			c += (a ^ (d | ~b)) + 0xA3014314 + sch06;  c = d + (c << 15 | c >>> 17);
			b += (d ^ (c | ~a)) + 0x4E0811A1 + sch13;  b = c + (b << 21 | b >>> 11);
			a += (c ^ (b | ~d)) + 0xF7537E82 + sch04;  a = b + (a <<  6 | a >>> 26);
			d += (b ^ (a | ~c)) + 0xBD3AF235 + sch11;  d = a + (d << 10 | d >>> 22);
			c += (a ^ (d | ~b)) + 0x2AD7D2BB + sch02;  c = d + (c << 15 | c >>> 17);
			b += (d ^ (c | ~a)) + 0xEB86D391 + sch09;  b = c + (b << 21 | b >>> 11);
			
			a = state[0] += a;
			b = state[1] += b;
			c = state[2] += c;
			d = state[3] += d;
		}
	}
	
}
