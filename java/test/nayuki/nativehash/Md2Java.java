package nayuki.nativehash;


final class Md2Java extends Md2 {
	
	protected void compress(byte[] msg, int off, int len) {
		int st00 = state[ 0] & 0xFF, st01 = state[ 1] & 0xFF, st02 = state[ 2] & 0xFF, st03 = state[ 3] & 0xFF;
		int st04 = state[ 4] & 0xFF, st05 = state[ 5] & 0xFF, st06 = state[ 6] & 0xFF, st07 = state[ 7] & 0xFF;
		int st08 = state[ 8] & 0xFF, st09 = state[ 9] & 0xFF, st10 = state[10] & 0xFF, st11 = state[11] & 0xFF;
		int st12 = state[12] & 0xFF, st13 = state[13] & 0xFF, st14 = state[14] & 0xFF, st15 = state[15] & 0xFF;
		int st16 = state[16] & 0xFF, st17 = state[17] & 0xFF, st18 = state[18] & 0xFF, st19 = state[19] & 0xFF;
		int st20 = state[20] & 0xFF, st21 = state[21] & 0xFF, st22 = state[22] & 0xFF, st23 = state[23] & 0xFF;
		int st24 = state[24] & 0xFF, st25 = state[25] & 0xFF, st26 = state[26] & 0xFF, st27 = state[27] & 0xFF;
		int st28 = state[28] & 0xFF, st29 = state[29] & 0xFF, st30 = state[30] & 0xFF, st31 = state[31] & 0xFF;
		int st32 = state[32] & 0xFF, st33 = state[33] & 0xFF, st34 = state[34] & 0xFF, st35 = state[35] & 0xFF;
		int st36 = state[36] & 0xFF, st37 = state[37] & 0xFF, st38 = state[38] & 0xFF, st39 = state[39] & 0xFF;
		int st40 = state[40] & 0xFF, st41 = state[41] & 0xFF, st42 = state[42] & 0xFF, st43 = state[43] & 0xFF;
		int st44 = state[44] & 0xFF, st45 = state[45] & 0xFF, st46 = state[46] & 0xFF, st47 = state[47] & 0xFF;
		int chk00 = checksum[ 0] & 0xFF, chk01 = checksum[ 1] & 0xFF, chk02 = checksum[ 2] & 0xFF, chk03 = checksum[ 3] & 0xFF;
		int chk04 = checksum[ 4] & 0xFF, chk05 = checksum[ 5] & 0xFF, chk06 = checksum[ 6] & 0xFF, chk07 = checksum[ 7] & 0xFF;
		int chk08 = checksum[ 8] & 0xFF, chk09 = checksum[ 9] & 0xFF, chk10 = checksum[10] & 0xFF, chk11 = checksum[11] & 0xFF;
		int chk12 = checksum[12] & 0xFF, chk13 = checksum[13] & 0xFF, chk14 = checksum[14] & 0xFF, chk15 = checksum[15] & 0xFF;
		
		for (int i = off, end = off + len; i < end; i += 16) {
			int msg00 = msg[i +  0] & 0xFF, msg01 = msg[i +  1] & 0xFF, msg02 = msg[i +  2] & 0xFF, msg03 = msg[i +  3] & 0xFF;
			int msg04 = msg[i +  4] & 0xFF, msg05 = msg[i +  5] & 0xFF, msg06 = msg[i +  6] & 0xFF, msg07 = msg[i +  7] & 0xFF;
			int msg08 = msg[i +  8] & 0xFF, msg09 = msg[i +  9] & 0xFF, msg10 = msg[i + 10] & 0xFF, msg11 = msg[i + 11] & 0xFF;
			int msg12 = msg[i + 12] & 0xFF, msg13 = msg[i + 13] & 0xFF, msg14 = msg[i + 14] & 0xFF, msg15 = msg[i + 15] & 0xFF;
			st16 = msg00;  st17 = msg01;  st18 = msg02;  st19 = msg03;
			st20 = msg04;  st21 = msg05;  st22 = msg06;  st23 = msg07;
			st24 = msg08;  st25 = msg09;  st26 = msg10;  st27 = msg11;
			st28 = msg12;  st29 = msg13;  st30 = msg14;  st31 = msg15;
			st32 = msg00 ^ st00;  st33 = msg01 ^ st01;  st34 = msg02 ^ st02;  st35 = msg03 ^ st03;
			st36 = msg04 ^ st04;  st37 = msg05 ^ st05;  st38 = msg06 ^ st06;  st39 = msg07 ^ st07;
			st40 = msg08 ^ st08;  st41 = msg09 ^ st09;  st42 = msg10 ^ st10;  st43 = msg11 ^ st11;
			st44 = msg12 ^ st12;  st45 = msg13 ^ st13;  st46 = msg14 ^ st14;  st47 = msg15 ^ st15;
			
			for (int j = 0, t = 0; j < 18; j++) {
				st00 ^= SBOX[t   ];  st01 ^= SBOX[st00];  st02 ^= SBOX[st01];  st03 ^= SBOX[st02];
				st04 ^= SBOX[st03];  st05 ^= SBOX[st04];  st06 ^= SBOX[st05];  st07 ^= SBOX[st06];
				st08 ^= SBOX[st07];  st09 ^= SBOX[st08];  st10 ^= SBOX[st09];  st11 ^= SBOX[st10];
				st12 ^= SBOX[st11];  st13 ^= SBOX[st12];  st14 ^= SBOX[st13];  st15 ^= SBOX[st14];
				st16 ^= SBOX[st15];  st17 ^= SBOX[st16];  st18 ^= SBOX[st17];  st19 ^= SBOX[st18];
				st20 ^= SBOX[st19];  st21 ^= SBOX[st20];  st22 ^= SBOX[st21];  st23 ^= SBOX[st22];
				st24 ^= SBOX[st23];  st25 ^= SBOX[st24];  st26 ^= SBOX[st25];  st27 ^= SBOX[st26];
				st28 ^= SBOX[st27];  st29 ^= SBOX[st28];  st30 ^= SBOX[st29];  st31 ^= SBOX[st30];
				st32 ^= SBOX[st31];  st33 ^= SBOX[st32];  st34 ^= SBOX[st33];  st35 ^= SBOX[st34];
				st36 ^= SBOX[st35];  st37 ^= SBOX[st36];  st38 ^= SBOX[st37];  st39 ^= SBOX[st38];
				st40 ^= SBOX[st39];  st41 ^= SBOX[st40];  st42 ^= SBOX[st41];  st43 ^= SBOX[st42];
				st44 ^= SBOX[st43];  st45 ^= SBOX[st44];  st46 ^= SBOX[st45];  st47 ^= SBOX[st46];
				t = (st47 + j) & 0xFF;
			}
			
			chk00 ^= SBOX[msg00 ^ chk15];  chk01 ^= SBOX[msg01 ^ chk00];  chk02 ^= SBOX[msg02 ^ chk01];  chk03 ^= SBOX[msg03 ^ chk02];
			chk04 ^= SBOX[msg04 ^ chk03];  chk05 ^= SBOX[msg05 ^ chk04];  chk06 ^= SBOX[msg06 ^ chk05];  chk07 ^= SBOX[msg07 ^ chk06];
			chk08 ^= SBOX[msg08 ^ chk07];  chk09 ^= SBOX[msg09 ^ chk08];  chk10 ^= SBOX[msg10 ^ chk09];  chk11 ^= SBOX[msg11 ^ chk10];
			chk12 ^= SBOX[msg12 ^ chk11];  chk13 ^= SBOX[msg13 ^ chk12];  chk14 ^= SBOX[msg14 ^ chk13];  chk15 ^= SBOX[msg15 ^ chk14];
		}
		
		state[ 0] = (byte)st00;  state[ 1] = (byte)st01;  state[ 2] = (byte)st02;  state[ 3] = (byte)st03;
		state[ 4] = (byte)st04;  state[ 5] = (byte)st05;  state[ 6] = (byte)st06;  state[ 7] = (byte)st07;
		state[ 8] = (byte)st08;  state[ 9] = (byte)st09;  state[10] = (byte)st10;  state[11] = (byte)st11;
		state[12] = (byte)st12;  state[13] = (byte)st13;  state[14] = (byte)st14;  state[15] = (byte)st15;
		state[16] = (byte)st16;  state[17] = (byte)st17;  state[18] = (byte)st18;  state[19] = (byte)st19;
		state[20] = (byte)st20;  state[21] = (byte)st21;  state[22] = (byte)st22;  state[23] = (byte)st23;
		state[24] = (byte)st24;  state[25] = (byte)st25;  state[26] = (byte)st26;  state[27] = (byte)st27;
		state[28] = (byte)st28;  state[29] = (byte)st29;  state[30] = (byte)st30;  state[31] = (byte)st31;
		state[32] = (byte)st32;  state[33] = (byte)st33;  state[34] = (byte)st34;  state[35] = (byte)st35;
		state[36] = (byte)st36;  state[37] = (byte)st37;  state[38] = (byte)st38;  state[39] = (byte)st39;
		state[40] = (byte)st40;  state[41] = (byte)st41;  state[42] = (byte)st42;  state[43] = (byte)st43;
		state[44] = (byte)st44;  state[45] = (byte)st45;  state[46] = (byte)st46;  state[47] = (byte)st47;
		checksum[ 0] = (byte)chk00;  checksum[ 1] = (byte)chk01;  checksum[ 2] = (byte)chk02;  checksum[ 3] = (byte)chk03;
		checksum[ 4] = (byte)chk04;  checksum[ 5] = (byte)chk05;  checksum[ 6] = (byte)chk06;  checksum[ 7] = (byte)chk07;
		checksum[ 8] = (byte)chk08;  checksum[ 9] = (byte)chk09;  checksum[10] = (byte)chk10;  checksum[11] = (byte)chk11;
		checksum[12] = (byte)chk12;  checksum[13] = (byte)chk13;  checksum[14] = (byte)chk14;  checksum[15] = (byte)chk15;
	}
	
	
	private static final int[] SBOX = {
		0x29, 0x2E, 0x43, 0xC9, 0xA2, 0xD8, 0x7C, 0x01, 0x3D, 0x36, 0x54, 0xA1, 0xEC, 0xF0, 0x06, 0x13,
		0x62, 0xA7, 0x05, 0xF3, 0xC0, 0xC7, 0x73, 0x8C, 0x98, 0x93, 0x2B, 0xD9, 0xBC, 0x4C, 0x82, 0xCA,
		0x1E, 0x9B, 0x57, 0x3C, 0xFD, 0xD4, 0xE0, 0x16, 0x67, 0x42, 0x6F, 0x18, 0x8A, 0x17, 0xE5, 0x12,
		0xBE, 0x4E, 0xC4, 0xD6, 0xDA, 0x9E, 0xDE, 0x49, 0xA0, 0xFB, 0xF5, 0x8E, 0xBB, 0x2F, 0xEE, 0x7A,
		0xA9, 0x68, 0x79, 0x91, 0x15, 0xB2, 0x07, 0x3F, 0x94, 0xC2, 0x10, 0x89, 0x0B, 0x22, 0x5F, 0x21,
		0x80, 0x7F, 0x5D, 0x9A, 0x5A, 0x90, 0x32, 0x27, 0x35, 0x3E, 0xCC, 0xE7, 0xBF, 0xF7, 0x97, 0x03,
		0xFF, 0x19, 0x30, 0xB3, 0x48, 0xA5, 0xB5, 0xD1, 0xD7, 0x5E, 0x92, 0x2A, 0xAC, 0x56, 0xAA, 0xC6,
		0x4F, 0xB8, 0x38, 0xD2, 0x96, 0xA4, 0x7D, 0xB6, 0x76, 0xFC, 0x6B, 0xE2, 0x9C, 0x74, 0x04, 0xF1,
		0x45, 0x9D, 0x70, 0x59, 0x64, 0x71, 0x87, 0x20, 0x86, 0x5B, 0xCF, 0x65, 0xE6, 0x2D, 0xA8, 0x02,
		0x1B, 0x60, 0x25, 0xAD, 0xAE, 0xB0, 0xB9, 0xF6, 0x1C, 0x46, 0x61, 0x69, 0x34, 0x40, 0x7E, 0x0F,
		0x55, 0x47, 0xA3, 0x23, 0xDD, 0x51, 0xAF, 0x3A, 0xC3, 0x5C, 0xF9, 0xCE, 0xBA, 0xC5, 0xEA, 0x26,
		0x2C, 0x53, 0x0D, 0x6E, 0x85, 0x28, 0x84, 0x09, 0xD3, 0xDF, 0xCD, 0xF4, 0x41, 0x81, 0x4D, 0x52,
		0x6A, 0xDC, 0x37, 0xC8, 0x6C, 0xC1, 0xAB, 0xFA, 0x24, 0xE1, 0x7B, 0x08, 0x0C, 0xBD, 0xB1, 0x4A,
		0x78, 0x88, 0x95, 0x8B, 0xE3, 0x63, 0xE8, 0x6D, 0xE9, 0xCB, 0xD5, 0xFE, 0x3B, 0x00, 0x1D, 0x39,
		0xF2, 0xEF, 0xB7, 0x0E, 0x66, 0x58, 0xD0, 0xE4, 0xA6, 0x77, 0x72, 0xF8, 0xEB, 0x75, 0x4B, 0x0A,
		0x31, 0x44, 0x50, 0xB4, 0x8F, 0xED, 0x1F, 0x1A, 0xDB, 0x99, 0x8D, 0x33, 0x9F, 0x11, 0x83, 0x14,
	};
	
}
