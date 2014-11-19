/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * http://www.nayuki.io/page/native-hash-functions-for-java
 */

#include <stdint.h>
#include <jni.h>


static uint8_t SBOX[256] = {
	UINT8_C(0x29), UINT8_C(0x2E), UINT8_C(0x43), UINT8_C(0xC9), UINT8_C(0xA2), UINT8_C(0xD8), UINT8_C(0x7C), UINT8_C(0x01),
	UINT8_C(0x3D), UINT8_C(0x36), UINT8_C(0x54), UINT8_C(0xA1), UINT8_C(0xEC), UINT8_C(0xF0), UINT8_C(0x06), UINT8_C(0x13),
	UINT8_C(0x62), UINT8_C(0xA7), UINT8_C(0x05), UINT8_C(0xF3), UINT8_C(0xC0), UINT8_C(0xC7), UINT8_C(0x73), UINT8_C(0x8C),
	UINT8_C(0x98), UINT8_C(0x93), UINT8_C(0x2B), UINT8_C(0xD9), UINT8_C(0xBC), UINT8_C(0x4C), UINT8_C(0x82), UINT8_C(0xCA),
	UINT8_C(0x1E), UINT8_C(0x9B), UINT8_C(0x57), UINT8_C(0x3C), UINT8_C(0xFD), UINT8_C(0xD4), UINT8_C(0xE0), UINT8_C(0x16),
	UINT8_C(0x67), UINT8_C(0x42), UINT8_C(0x6F), UINT8_C(0x18), UINT8_C(0x8A), UINT8_C(0x17), UINT8_C(0xE5), UINT8_C(0x12),
	UINT8_C(0xBE), UINT8_C(0x4E), UINT8_C(0xC4), UINT8_C(0xD6), UINT8_C(0xDA), UINT8_C(0x9E), UINT8_C(0xDE), UINT8_C(0x49),
	UINT8_C(0xA0), UINT8_C(0xFB), UINT8_C(0xF5), UINT8_C(0x8E), UINT8_C(0xBB), UINT8_C(0x2F), UINT8_C(0xEE), UINT8_C(0x7A),
	UINT8_C(0xA9), UINT8_C(0x68), UINT8_C(0x79), UINT8_C(0x91), UINT8_C(0x15), UINT8_C(0xB2), UINT8_C(0x07), UINT8_C(0x3F),
	UINT8_C(0x94), UINT8_C(0xC2), UINT8_C(0x10), UINT8_C(0x89), UINT8_C(0x0B), UINT8_C(0x22), UINT8_C(0x5F), UINT8_C(0x21),
	UINT8_C(0x80), UINT8_C(0x7F), UINT8_C(0x5D), UINT8_C(0x9A), UINT8_C(0x5A), UINT8_C(0x90), UINT8_C(0x32), UINT8_C(0x27),
	UINT8_C(0x35), UINT8_C(0x3E), UINT8_C(0xCC), UINT8_C(0xE7), UINT8_C(0xBF), UINT8_C(0xF7), UINT8_C(0x97), UINT8_C(0x03),
	UINT8_C(0xFF), UINT8_C(0x19), UINT8_C(0x30), UINT8_C(0xB3), UINT8_C(0x48), UINT8_C(0xA5), UINT8_C(0xB5), UINT8_C(0xD1),
	UINT8_C(0xD7), UINT8_C(0x5E), UINT8_C(0x92), UINT8_C(0x2A), UINT8_C(0xAC), UINT8_C(0x56), UINT8_C(0xAA), UINT8_C(0xC6),
	UINT8_C(0x4F), UINT8_C(0xB8), UINT8_C(0x38), UINT8_C(0xD2), UINT8_C(0x96), UINT8_C(0xA4), UINT8_C(0x7D), UINT8_C(0xB6),
	UINT8_C(0x76), UINT8_C(0xFC), UINT8_C(0x6B), UINT8_C(0xE2), UINT8_C(0x9C), UINT8_C(0x74), UINT8_C(0x04), UINT8_C(0xF1),
	UINT8_C(0x45), UINT8_C(0x9D), UINT8_C(0x70), UINT8_C(0x59), UINT8_C(0x64), UINT8_C(0x71), UINT8_C(0x87), UINT8_C(0x20),
	UINT8_C(0x86), UINT8_C(0x5B), UINT8_C(0xCF), UINT8_C(0x65), UINT8_C(0xE6), UINT8_C(0x2D), UINT8_C(0xA8), UINT8_C(0x02),
	UINT8_C(0x1B), UINT8_C(0x60), UINT8_C(0x25), UINT8_C(0xAD), UINT8_C(0xAE), UINT8_C(0xB0), UINT8_C(0xB9), UINT8_C(0xF6),
	UINT8_C(0x1C), UINT8_C(0x46), UINT8_C(0x61), UINT8_C(0x69), UINT8_C(0x34), UINT8_C(0x40), UINT8_C(0x7E), UINT8_C(0x0F),
	UINT8_C(0x55), UINT8_C(0x47), UINT8_C(0xA3), UINT8_C(0x23), UINT8_C(0xDD), UINT8_C(0x51), UINT8_C(0xAF), UINT8_C(0x3A),
	UINT8_C(0xC3), UINT8_C(0x5C), UINT8_C(0xF9), UINT8_C(0xCE), UINT8_C(0xBA), UINT8_C(0xC5), UINT8_C(0xEA), UINT8_C(0x26),
	UINT8_C(0x2C), UINT8_C(0x53), UINT8_C(0x0D), UINT8_C(0x6E), UINT8_C(0x85), UINT8_C(0x28), UINT8_C(0x84), UINT8_C(0x09),
	UINT8_C(0xD3), UINT8_C(0xDF), UINT8_C(0xCD), UINT8_C(0xF4), UINT8_C(0x41), UINT8_C(0x81), UINT8_C(0x4D), UINT8_C(0x52),
	UINT8_C(0x6A), UINT8_C(0xDC), UINT8_C(0x37), UINT8_C(0xC8), UINT8_C(0x6C), UINT8_C(0xC1), UINT8_C(0xAB), UINT8_C(0xFA),
	UINT8_C(0x24), UINT8_C(0xE1), UINT8_C(0x7B), UINT8_C(0x08), UINT8_C(0x0C), UINT8_C(0xBD), UINT8_C(0xB1), UINT8_C(0x4A),
	UINT8_C(0x78), UINT8_C(0x88), UINT8_C(0x95), UINT8_C(0x8B), UINT8_C(0xE3), UINT8_C(0x63), UINT8_C(0xE8), UINT8_C(0x6D),
	UINT8_C(0xE9), UINT8_C(0xCB), UINT8_C(0xD5), UINT8_C(0xFE), UINT8_C(0x3B), UINT8_C(0x00), UINT8_C(0x1D), UINT8_C(0x39),
	UINT8_C(0xF2), UINT8_C(0xEF), UINT8_C(0xB7), UINT8_C(0x0E), UINT8_C(0x66), UINT8_C(0x58), UINT8_C(0xD0), UINT8_C(0xE4),
	UINT8_C(0xA6), UINT8_C(0x77), UINT8_C(0x72), UINT8_C(0xF8), UINT8_C(0xEB), UINT8_C(0x75), UINT8_C(0x4B), UINT8_C(0x0A),
	UINT8_C(0x31), UINT8_C(0x44), UINT8_C(0x50), UINT8_C(0xB4), UINT8_C(0x8F), UINT8_C(0xED), UINT8_C(0x1F), UINT8_C(0x1A),
	UINT8_C(0xDB), UINT8_C(0x99), UINT8_C(0x8D), UINT8_C(0x33), UINT8_C(0x9F), UINT8_C(0x11), UINT8_C(0x83), UINT8_C(0x14),
};


void md2_compress_block(const jbyte *block, uint8_t state[48], uint8_t checksum[16]) {
	state[16] = (uint8_t)block[ 0];  state[17] = (uint8_t)block[ 1];  state[18] = (uint8_t)block[ 2];  state[19] = (uint8_t)block[ 3];
	state[20] = (uint8_t)block[ 4];  state[21] = (uint8_t)block[ 5];  state[22] = (uint8_t)block[ 6];  state[23] = (uint8_t)block[ 7];
	state[24] = (uint8_t)block[ 8];  state[25] = (uint8_t)block[ 9];  state[26] = (uint8_t)block[10];  state[27] = (uint8_t)block[11];
	state[28] = (uint8_t)block[12];  state[29] = (uint8_t)block[13];  state[30] = (uint8_t)block[14];  state[31] = (uint8_t)block[15];
	state[32] = (uint8_t)block[ 0] ^ state[ 0];  state[33] = (uint8_t)block[ 1] ^ state[ 1];  state[34] = (uint8_t)block[ 2] ^ state[ 2];  state[35] = (uint8_t)block[ 3] ^ state[ 3];
	state[36] = (uint8_t)block[ 4] ^ state[ 4];  state[37] = (uint8_t)block[ 5] ^ state[ 5];  state[38] = (uint8_t)block[ 6] ^ state[ 6];  state[39] = (uint8_t)block[ 7] ^ state[ 7];
	state[40] = (uint8_t)block[ 8] ^ state[ 8];  state[41] = (uint8_t)block[ 9] ^ state[ 9];  state[42] = (uint8_t)block[10] ^ state[10];  state[43] = (uint8_t)block[11] ^ state[11];
	state[44] = (uint8_t)block[12] ^ state[12];  state[45] = (uint8_t)block[13] ^ state[13];  state[46] = (uint8_t)block[14] ^ state[14];  state[47] = (uint8_t)block[15] ^ state[15];
	
	uint8_t t = 0;
	int i;
	for (i = 0; i < 18; i++) {
		state[ 0] ^= SBOX[t        ];  state[ 1] ^= SBOX[state[ 0]];  state[ 2] ^= SBOX[state[ 1]];  state[ 3] ^= SBOX[state[ 2]];
		state[ 4] ^= SBOX[state[ 3]];  state[ 5] ^= SBOX[state[ 4]];  state[ 6] ^= SBOX[state[ 5]];  state[ 7] ^= SBOX[state[ 6]];
		state[ 8] ^= SBOX[state[ 7]];  state[ 9] ^= SBOX[state[ 8]];  state[10] ^= SBOX[state[ 9]];  state[11] ^= SBOX[state[10]];
		state[12] ^= SBOX[state[11]];  state[13] ^= SBOX[state[12]];  state[14] ^= SBOX[state[13]];  state[15] ^= SBOX[state[14]];
		state[16] ^= SBOX[state[15]];  state[17] ^= SBOX[state[16]];  state[18] ^= SBOX[state[17]];  state[19] ^= SBOX[state[18]];
		state[20] ^= SBOX[state[19]];  state[21] ^= SBOX[state[20]];  state[22] ^= SBOX[state[21]];  state[23] ^= SBOX[state[22]];
		state[24] ^= SBOX[state[23]];  state[25] ^= SBOX[state[24]];  state[26] ^= SBOX[state[25]];  state[27] ^= SBOX[state[26]];
		state[28] ^= SBOX[state[27]];  state[29] ^= SBOX[state[28]];  state[30] ^= SBOX[state[29]];  state[31] ^= SBOX[state[30]];
		state[32] ^= SBOX[state[31]];  state[33] ^= SBOX[state[32]];  state[34] ^= SBOX[state[33]];  state[35] ^= SBOX[state[34]];
		state[36] ^= SBOX[state[35]];  state[37] ^= SBOX[state[36]];  state[38] ^= SBOX[state[37]];  state[39] ^= SBOX[state[38]];
		state[40] ^= SBOX[state[39]];  state[41] ^= SBOX[state[40]];  state[42] ^= SBOX[state[41]];  state[43] ^= SBOX[state[42]];
		state[44] ^= SBOX[state[43]];  state[45] ^= SBOX[state[44]];  state[46] ^= SBOX[state[45]];  state[47] ^= SBOX[state[46]];
		t = state[47] + i;
	}
	
	checksum[ 0] ^= SBOX[(uint8_t)block[ 0] ^ checksum[15]];
	checksum[ 1] ^= SBOX[(uint8_t)block[ 1] ^ checksum[ 0]];
	checksum[ 2] ^= SBOX[(uint8_t)block[ 2] ^ checksum[ 1]];
	checksum[ 3] ^= SBOX[(uint8_t)block[ 3] ^ checksum[ 2]];
	checksum[ 4] ^= SBOX[(uint8_t)block[ 4] ^ checksum[ 3]];
	checksum[ 5] ^= SBOX[(uint8_t)block[ 5] ^ checksum[ 4]];
	checksum[ 6] ^= SBOX[(uint8_t)block[ 6] ^ checksum[ 5]];
	checksum[ 7] ^= SBOX[(uint8_t)block[ 7] ^ checksum[ 6]];
	checksum[ 8] ^= SBOX[(uint8_t)block[ 8] ^ checksum[ 7]];
	checksum[ 9] ^= SBOX[(uint8_t)block[ 9] ^ checksum[ 8]];
	checksum[10] ^= SBOX[(uint8_t)block[10] ^ checksum[ 9]];
	checksum[11] ^= SBOX[(uint8_t)block[11] ^ checksum[10]];
	checksum[12] ^= SBOX[(uint8_t)block[12] ^ checksum[11]];
	checksum[13] ^= SBOX[(uint8_t)block[13] ^ checksum[12]];
	checksum[14] ^= SBOX[(uint8_t)block[14] ^ checksum[13]];
	checksum[15] ^= SBOX[(uint8_t)block[15] ^ checksum[14]];
}
