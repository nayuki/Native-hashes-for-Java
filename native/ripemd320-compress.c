#include <stdint.h>
#include <jni.h>


void ripemd320_compress_block(const jbyte *block, uint32_t state[8]) {
	#define LOADSCHEDULE(i)  \
		schedule[i] =                                    \
			  (uint32_t)(uint8_t)block[i * 4 + 0]        \
			| (uint32_t)(uint8_t)block[i * 4 + 1] <<  8  \
			| (uint32_t)(uint8_t)block[i * 4 + 2] << 16  \
			| (uint32_t)(uint8_t)block[i * 4 + 3] << 24;
	
	uint32_t schedule[16];
	LOADSCHEDULE( 0)
	LOADSCHEDULE( 1)
	LOADSCHEDULE( 2)
	LOADSCHEDULE( 3)
	LOADSCHEDULE( 4)
	LOADSCHEDULE( 5)
	LOADSCHEDULE( 6)
	LOADSCHEDULE( 7)
	LOADSCHEDULE( 8)
	LOADSCHEDULE( 9)
	LOADSCHEDULE(10)
	LOADSCHEDULE(11)
	LOADSCHEDULE(12)
	LOADSCHEDULE(13)
	LOADSCHEDULE(14)
	LOADSCHEDULE(15)
	
	#define ROTL32(x, n)  (((x) << (n)) | ((x) >> (32 - (n))))  // Assumes that x is uint32_t and 0 < n < 32
	#define ROUND0(a, b, c, d, e, k, r, s)  ROUND(a, b, c, d, e, k, r, s, (b ^ c ^ d))
	#define ROUND1(a, b, c, d, e, k, r, s)  ROUND(a, b, c, d, e, k, r, s, ((b & c) | (~b & d)))
	#define ROUND2(a, b, c, d, e, k, r, s)  ROUND(a, b, c, d, e, k, r, s, ((b | ~c) ^ d))
	#define ROUND3(a, b, c, d, e, k, r, s)  ROUND(a, b, c, d, e, k, r, s, ((b & d) | (c & ~d)))
	#define ROUND4(a, b, c, d, e, k, r, s)  ROUND(a, b, c, d, e, k, r, s, (b ^ (c | ~d)))
	
	#define ROUND(a, b, c, d, e, k, r, s, f)  \
		a = a + f + schedule[r] + UINT32_C(k);  \
		a = ROTL32(a, s) + e;  \
		c = ROTL32(c, 10);
	
	uint32_t al = state[0], ar = state[5];
	uint32_t bl = state[1], br = state[6];
	uint32_t cl = state[2], cr = state[7];
	uint32_t dl = state[3], dr = state[8];
	uint32_t el = state[4], er = state[9];
	
	ROUND0(al, bl, cl, dl, el, 0x00000000,  0, 11)  ROUND4(ar, br, cr, dr, er, 0x50A28BE6,  5,  8)
	ROUND0(el, al, bl, cl, dl, 0x00000000,  1, 14)  ROUND4(er, ar, br, cr, dr, 0x50A28BE6, 14,  9)
	ROUND0(dl, el, al, bl, cl, 0x00000000,  2, 15)  ROUND4(dr, er, ar, br, cr, 0x50A28BE6,  7,  9)
	ROUND0(cl, dl, el, al, bl, 0x00000000,  3, 12)  ROUND4(cr, dr, er, ar, br, 0x50A28BE6,  0, 11)
	ROUND0(bl, cl, dl, el, al, 0x00000000,  4,  5)  ROUND4(br, cr, dr, er, ar, 0x50A28BE6,  9, 13)
	ROUND0(al, bl, cl, dl, el, 0x00000000,  5,  8)  ROUND4(ar, br, cr, dr, er, 0x50A28BE6,  2, 15)
	ROUND0(el, al, bl, cl, dl, 0x00000000,  6,  7)  ROUND4(er, ar, br, cr, dr, 0x50A28BE6, 11, 15)
	ROUND0(dl, el, al, bl, cl, 0x00000000,  7,  9)  ROUND4(dr, er, ar, br, cr, 0x50A28BE6,  4,  5)
	ROUND0(cl, dl, el, al, bl, 0x00000000,  8, 11)  ROUND4(cr, dr, er, ar, br, 0x50A28BE6, 13,  7)
	ROUND0(bl, cl, dl, el, al, 0x00000000,  9, 13)  ROUND4(br, cr, dr, er, ar, 0x50A28BE6,  6,  7)
	ROUND0(al, bl, cl, dl, el, 0x00000000, 10, 14)  ROUND4(ar, br, cr, dr, er, 0x50A28BE6, 15,  8)
	ROUND0(el, al, bl, cl, dl, 0x00000000, 11, 15)  ROUND4(er, ar, br, cr, dr, 0x50A28BE6,  8, 11)
	ROUND0(dl, el, al, bl, cl, 0x00000000, 12,  6)  ROUND4(dr, er, ar, br, cr, 0x50A28BE6,  1, 14)
	ROUND0(cl, dl, el, al, bl, 0x00000000, 13,  7)  ROUND4(cr, dr, er, ar, br, 0x50A28BE6, 10, 14)
	ROUND0(bl, cl, dl, el, al, 0x00000000, 14,  9)  ROUND4(br, cr, dr, er, ar, 0x50A28BE6,  3, 12)
	ROUND0(al, bl, cl, dl, el, 0x00000000, 15,  8)  ROUND4(ar, br, cr, dr, er, 0x50A28BE6, 12,  6)
	ROUND1(el, ar, bl, cl, dl, 0x5A827999,  7,  7)  ROUND3(er, al, br, cr, dr, 0x5C4DD124,  6,  9)
	ROUND1(dl, el, ar, bl, cl, 0x5A827999,  4,  6)  ROUND3(dr, er, al, br, cr, 0x5C4DD124, 11, 13)
	ROUND1(cl, dl, el, ar, bl, 0x5A827999, 13,  8)  ROUND3(cr, dr, er, al, br, 0x5C4DD124,  3, 15)
	ROUND1(bl, cl, dl, el, ar, 0x5A827999,  1, 13)  ROUND3(br, cr, dr, er, al, 0x5C4DD124,  7,  7)
	ROUND1(ar, bl, cl, dl, el, 0x5A827999, 10, 11)  ROUND3(al, br, cr, dr, er, 0x5C4DD124,  0, 12)
	ROUND1(el, ar, bl, cl, dl, 0x5A827999,  6,  9)  ROUND3(er, al, br, cr, dr, 0x5C4DD124, 13,  8)
	ROUND1(dl, el, ar, bl, cl, 0x5A827999, 15,  7)  ROUND3(dr, er, al, br, cr, 0x5C4DD124,  5,  9)
	ROUND1(cl, dl, el, ar, bl, 0x5A827999,  3, 15)  ROUND3(cr, dr, er, al, br, 0x5C4DD124, 10, 11)
	ROUND1(bl, cl, dl, el, ar, 0x5A827999, 12,  7)  ROUND3(br, cr, dr, er, al, 0x5C4DD124, 14,  7)
	ROUND1(ar, bl, cl, dl, el, 0x5A827999,  0, 12)  ROUND3(al, br, cr, dr, er, 0x5C4DD124, 15,  7)
	ROUND1(el, ar, bl, cl, dl, 0x5A827999,  9, 15)  ROUND3(er, al, br, cr, dr, 0x5C4DD124,  8, 12)
	ROUND1(dl, el, ar, bl, cl, 0x5A827999,  5,  9)  ROUND3(dr, er, al, br, cr, 0x5C4DD124, 12,  7)
	ROUND1(cl, dl, el, ar, bl, 0x5A827999,  2, 11)  ROUND3(cr, dr, er, al, br, 0x5C4DD124,  4,  6)
	ROUND1(bl, cl, dl, el, ar, 0x5A827999, 14,  7)  ROUND3(br, cr, dr, er, al, 0x5C4DD124,  9, 15)
	ROUND1(ar, bl, cl, dl, el, 0x5A827999, 11, 13)  ROUND3(al, br, cr, dr, er, 0x5C4DD124,  1, 13)
	ROUND1(el, ar, bl, cl, dl, 0x5A827999,  8, 12)  ROUND3(er, al, br, cr, dr, 0x5C4DD124,  2, 11)
	ROUND2(dl, el, ar, br, cl, 0x6ED9EBA1,  3, 11)  ROUND2(dr, er, al, bl, cr, 0x6D703EF3, 15,  9)
	ROUND2(cl, dl, el, ar, br, 0x6ED9EBA1, 10, 13)  ROUND2(cr, dr, er, al, bl, 0x6D703EF3,  5,  7)
	ROUND2(br, cl, dl, el, ar, 0x6ED9EBA1, 14,  6)  ROUND2(bl, cr, dr, er, al, 0x6D703EF3,  1, 15)
	ROUND2(ar, br, cl, dl, el, 0x6ED9EBA1,  4,  7)  ROUND2(al, bl, cr, dr, er, 0x6D703EF3,  3, 11)
	ROUND2(el, ar, br, cl, dl, 0x6ED9EBA1,  9, 14)  ROUND2(er, al, bl, cr, dr, 0x6D703EF3,  7,  8)
	ROUND2(dl, el, ar, br, cl, 0x6ED9EBA1, 15,  9)  ROUND2(dr, er, al, bl, cr, 0x6D703EF3, 14,  6)
	ROUND2(cl, dl, el, ar, br, 0x6ED9EBA1,  8, 13)  ROUND2(cr, dr, er, al, bl, 0x6D703EF3,  6,  6)
	ROUND2(br, cl, dl, el, ar, 0x6ED9EBA1,  1, 15)  ROUND2(bl, cr, dr, er, al, 0x6D703EF3,  9, 14)
	ROUND2(ar, br, cl, dl, el, 0x6ED9EBA1,  2, 14)  ROUND2(al, bl, cr, dr, er, 0x6D703EF3, 11, 12)
	ROUND2(el, ar, br, cl, dl, 0x6ED9EBA1,  7,  8)  ROUND2(er, al, bl, cr, dr, 0x6D703EF3,  8, 13)
	ROUND2(dl, el, ar, br, cl, 0x6ED9EBA1,  0, 13)  ROUND2(dr, er, al, bl, cr, 0x6D703EF3, 12,  5)
	ROUND2(cl, dl, el, ar, br, 0x6ED9EBA1,  6,  6)  ROUND2(cr, dr, er, al, bl, 0x6D703EF3,  2, 14)
	ROUND2(br, cl, dl, el, ar, 0x6ED9EBA1, 13,  5)  ROUND2(bl, cr, dr, er, al, 0x6D703EF3, 10, 13)
	ROUND2(ar, br, cl, dl, el, 0x6ED9EBA1, 11, 12)  ROUND2(al, bl, cr, dr, er, 0x6D703EF3,  0, 13)
	ROUND2(el, ar, br, cl, dl, 0x6ED9EBA1,  5,  7)  ROUND2(er, al, bl, cr, dr, 0x6D703EF3,  4,  7)
	ROUND2(dl, el, ar, br, cl, 0x6ED9EBA1, 12,  5)  ROUND2(dr, er, al, bl, cr, 0x6D703EF3, 13,  5)
	ROUND3(cr, dl, el, ar, br, 0x8F1BBCDC,  1, 11)  ROUND1(cl, dr, er, al, bl, 0x7A6D76E9,  8, 15)
	ROUND3(br, cr, dl, el, ar, 0x8F1BBCDC,  9, 12)  ROUND1(bl, cl, dr, er, al, 0x7A6D76E9,  6,  5)
	ROUND3(ar, br, cr, dl, el, 0x8F1BBCDC, 11, 14)  ROUND1(al, bl, cl, dr, er, 0x7A6D76E9,  4,  8)
	ROUND3(el, ar, br, cr, dl, 0x8F1BBCDC, 10, 15)  ROUND1(er, al, bl, cl, dr, 0x7A6D76E9,  1, 11)
	ROUND3(dl, el, ar, br, cr, 0x8F1BBCDC,  0, 14)  ROUND1(dr, er, al, bl, cl, 0x7A6D76E9,  3, 14)
	ROUND3(cr, dl, el, ar, br, 0x8F1BBCDC,  8, 15)  ROUND1(cl, dr, er, al, bl, 0x7A6D76E9, 11, 14)
	ROUND3(br, cr, dl, el, ar, 0x8F1BBCDC, 12,  9)  ROUND1(bl, cl, dr, er, al, 0x7A6D76E9, 15,  6)
	ROUND3(ar, br, cr, dl, el, 0x8F1BBCDC,  4,  8)  ROUND1(al, bl, cl, dr, er, 0x7A6D76E9,  0, 14)
	ROUND3(el, ar, br, cr, dl, 0x8F1BBCDC, 13,  9)  ROUND1(er, al, bl, cl, dr, 0x7A6D76E9,  5,  6)
	ROUND3(dl, el, ar, br, cr, 0x8F1BBCDC,  3, 14)  ROUND1(dr, er, al, bl, cl, 0x7A6D76E9, 12,  9)
	ROUND3(cr, dl, el, ar, br, 0x8F1BBCDC,  7,  5)  ROUND1(cl, dr, er, al, bl, 0x7A6D76E9,  2, 12)
	ROUND3(br, cr, dl, el, ar, 0x8F1BBCDC, 15,  6)  ROUND1(bl, cl, dr, er, al, 0x7A6D76E9, 13,  9)
	ROUND3(ar, br, cr, dl, el, 0x8F1BBCDC, 14,  8)  ROUND1(al, bl, cl, dr, er, 0x7A6D76E9,  9, 12)
	ROUND3(el, ar, br, cr, dl, 0x8F1BBCDC,  5,  6)  ROUND1(er, al, bl, cl, dr, 0x7A6D76E9,  7,  5)
	ROUND3(dl, el, ar, br, cr, 0x8F1BBCDC,  6,  5)  ROUND1(dr, er, al, bl, cl, 0x7A6D76E9, 10, 15)
	ROUND3(cr, dl, el, ar, br, 0x8F1BBCDC,  2, 12)  ROUND1(cl, dr, er, al, bl, 0x7A6D76E9, 14,  8)
	ROUND4(br, cr, dr, el, ar, 0xA953FD4E,  4,  9)  ROUND0(bl, cl, dl, er, al, 0x00000000, 12,  8)
	ROUND4(ar, br, cr, dr, el, 0xA953FD4E,  0, 15)  ROUND0(al, bl, cl, dl, er, 0x00000000, 15,  5)
	ROUND4(el, ar, br, cr, dr, 0xA953FD4E,  5,  5)  ROUND0(er, al, bl, cl, dl, 0x00000000, 10, 12)
	ROUND4(dr, el, ar, br, cr, 0xA953FD4E,  9, 11)  ROUND0(dl, er, al, bl, cl, 0x00000000,  4,  9)
	ROUND4(cr, dr, el, ar, br, 0xA953FD4E,  7,  6)  ROUND0(cl, dl, er, al, bl, 0x00000000,  1, 12)
	ROUND4(br, cr, dr, el, ar, 0xA953FD4E, 12,  8)  ROUND0(bl, cl, dl, er, al, 0x00000000,  5,  5)
	ROUND4(ar, br, cr, dr, el, 0xA953FD4E,  2, 13)  ROUND0(al, bl, cl, dl, er, 0x00000000,  8, 14)
	ROUND4(el, ar, br, cr, dr, 0xA953FD4E, 10, 12)  ROUND0(er, al, bl, cl, dl, 0x00000000,  7,  6)
	ROUND4(dr, el, ar, br, cr, 0xA953FD4E, 14,  5)  ROUND0(dl, er, al, bl, cl, 0x00000000,  6,  8)
	ROUND4(cr, dr, el, ar, br, 0xA953FD4E,  1, 12)  ROUND0(cl, dl, er, al, bl, 0x00000000,  2, 13)
	ROUND4(br, cr, dr, el, ar, 0xA953FD4E,  3, 13)  ROUND0(bl, cl, dl, er, al, 0x00000000, 13,  6)
	ROUND4(ar, br, cr, dr, el, 0xA953FD4E,  8, 14)  ROUND0(al, bl, cl, dl, er, 0x00000000, 14,  5)
	ROUND4(el, ar, br, cr, dr, 0xA953FD4E, 11, 11)  ROUND0(er, al, bl, cl, dl, 0x00000000,  0, 15)
	ROUND4(dr, el, ar, br, cr, 0xA953FD4E,  6,  8)  ROUND0(dl, er, al, bl, cl, 0x00000000,  3, 13)
	ROUND4(cr, dr, el, ar, br, 0xA953FD4E, 15,  5)  ROUND0(cl, dl, er, al, bl, 0x00000000,  9, 11)
	ROUND4(br, cr, dr, el, ar, 0xA953FD4E, 13,  6)  ROUND0(bl, cl, dl, er, al, 0x00000000, 11, 11)
	
	state[0] += ar;
	state[1] += br;
	state[2] += cr;
	state[3] += dr;
	state[4] += er;
	state[5] += al;
	state[6] += bl;
	state[7] += cl;
	state[8] += dl;
	state[9] += el;
}
