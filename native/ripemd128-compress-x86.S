/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * http://www.nayuki.io/page/native-hash-functions-for-java
 */


/* void ripemd128_compress_block(const uint8_t block[64], uint32_t state[4]) */
.globl ripemd128_compress_block
ripemd128_compress_block:
	/* 
	 * Storage usage:
	 *   Bytes  Location  Description
	 *       4  eax       RIPEMD-128 state variable A
	 *       4  ebx       RIPEMD-128 state variable B
	 *       4  ecx       RIPEMD-128 state variable C
	 *       4  edx       RIPEMD-128 state variable D
	 *       4  edi       Temporary for calculation per round
	 *       4  esi       Base address of block array argument (read-only)
	 *       4  esp       x86 stack pointer
	 *       4  [esp+ 0]  Caller's value of ebx
	 *       4  [esp+ 4]  Caller's value of esi
	 *       4  [esp+ 8]  Caller's value of edi
	 *      16  [esp+12]  RIPEMD-128 state variables AL, BL, CL, DL
	 */
	
	#define ROUND0(a, b, c, d, k, r, s)  \
		movl  %b, %edi;  \
		xorl  %c, %edi;  \
		xorl  %d, %edi;  \
		ROUNDTAIL(a, k, r, s)
	
	#define ROUND1(a, b, c, d, k, r, s)  \
		movl  %c, %edi;  \
		xorl  %d, %edi;  \
		andl  %b, %edi;  \
		xorl  %d, %edi;  \
		ROUNDTAIL(a, k, r, s)
	
	#define ROUND2(a, b, c, d, k, r, s)  \
		movl  %c, %edi;  \
		notl  %edi;      \
		orl   %b, %edi;  \
		xorl  %d, %edi;  \
		ROUNDTAIL(a, k, r, s)
	
	#define ROUND3(a, b, c, d, k, r, s)  \
		movl  %b, %edi;  \
		xorl  %c, %edi;  \
		andl  %d, %edi;  \
		xorl  %c, %edi;  \
		ROUNDTAIL(a, k, r, s)
	
	#define ROUNDTAIL(a, k, r, s)  \
		leal  k(%a, %edi), %a;  \
		addl  (r*4)(%esi), %a;  \
		roll  $s, %a;
	
	/* Save registers */
	subl  $28, %esp
	movl  %ebx,  0(%esp)
	movl  %esi,  4(%esp)
	movl  %edi,  8(%esp)
	
	/* Load arguments */
	movl  32(%esp), %esi  /* block */
	movl  36(%esp), %edi  /* state */
	movl   0(%edi), %eax  /* a */
	movl   4(%edi), %ebx  /* b */
	movl   8(%edi), %ecx  /* c */
	movl  12(%edi), %edx  /* d */
	
	/* 64 rounds of left-side hashing */
	ROUND0(eax, ebx, ecx, edx, 0x00000000,  0, 11)
	ROUND0(edx, eax, ebx, ecx, 0x00000000,  1, 14)
	ROUND0(ecx, edx, eax, ebx, 0x00000000,  2, 15)
	ROUND0(ebx, ecx, edx, eax, 0x00000000,  3, 12)
	ROUND0(eax, ebx, ecx, edx, 0x00000000,  4,  5)
	ROUND0(edx, eax, ebx, ecx, 0x00000000,  5,  8)
	ROUND0(ecx, edx, eax, ebx, 0x00000000,  6,  7)
	ROUND0(ebx, ecx, edx, eax, 0x00000000,  7,  9)
	ROUND0(eax, ebx, ecx, edx, 0x00000000,  8, 11)
	ROUND0(edx, eax, ebx, ecx, 0x00000000,  9, 13)
	ROUND0(ecx, edx, eax, ebx, 0x00000000, 10, 14)
	ROUND0(ebx, ecx, edx, eax, 0x00000000, 11, 15)
	ROUND0(eax, ebx, ecx, edx, 0x00000000, 12,  6)
	ROUND0(edx, eax, ebx, ecx, 0x00000000, 13,  7)
	ROUND0(ecx, edx, eax, ebx, 0x00000000, 14,  9)
	ROUND0(ebx, ecx, edx, eax, 0x00000000, 15,  8)
	ROUND1(eax, ebx, ecx, edx, 0x5A827999,  7,  7)
	ROUND1(edx, eax, ebx, ecx, 0x5A827999,  4,  6)
	ROUND1(ecx, edx, eax, ebx, 0x5A827999, 13,  8)
	ROUND1(ebx, ecx, edx, eax, 0x5A827999,  1, 13)
	ROUND1(eax, ebx, ecx, edx, 0x5A827999, 10, 11)
	ROUND1(edx, eax, ebx, ecx, 0x5A827999,  6,  9)
	ROUND1(ecx, edx, eax, ebx, 0x5A827999, 15,  7)
	ROUND1(ebx, ecx, edx, eax, 0x5A827999,  3, 15)
	ROUND1(eax, ebx, ecx, edx, 0x5A827999, 12,  7)
	ROUND1(edx, eax, ebx, ecx, 0x5A827999,  0, 12)
	ROUND1(ecx, edx, eax, ebx, 0x5A827999,  9, 15)
	ROUND1(ebx, ecx, edx, eax, 0x5A827999,  5,  9)
	ROUND1(eax, ebx, ecx, edx, 0x5A827999,  2, 11)
	ROUND1(edx, eax, ebx, ecx, 0x5A827999, 14,  7)
	ROUND1(ecx, edx, eax, ebx, 0x5A827999, 11, 13)
	ROUND1(ebx, ecx, edx, eax, 0x5A827999,  8, 12)
	ROUND2(eax, ebx, ecx, edx, 0x6ED9EBA1,  3, 11)
	ROUND2(edx, eax, ebx, ecx, 0x6ED9EBA1, 10, 13)
	ROUND2(ecx, edx, eax, ebx, 0x6ED9EBA1, 14,  6)
	ROUND2(ebx, ecx, edx, eax, 0x6ED9EBA1,  4,  7)
	ROUND2(eax, ebx, ecx, edx, 0x6ED9EBA1,  9, 14)
	ROUND2(edx, eax, ebx, ecx, 0x6ED9EBA1, 15,  9)
	ROUND2(ecx, edx, eax, ebx, 0x6ED9EBA1,  8, 13)
	ROUND2(ebx, ecx, edx, eax, 0x6ED9EBA1,  1, 15)
	ROUND2(eax, ebx, ecx, edx, 0x6ED9EBA1,  2, 14)
	ROUND2(edx, eax, ebx, ecx, 0x6ED9EBA1,  7,  8)
	ROUND2(ecx, edx, eax, ebx, 0x6ED9EBA1,  0, 13)
	ROUND2(ebx, ecx, edx, eax, 0x6ED9EBA1,  6,  6)
	ROUND2(eax, ebx, ecx, edx, 0x6ED9EBA1, 13,  5)
	ROUND2(edx, eax, ebx, ecx, 0x6ED9EBA1, 11, 12)
	ROUND2(ecx, edx, eax, ebx, 0x6ED9EBA1,  5,  7)
	ROUND2(ebx, ecx, edx, eax, 0x6ED9EBA1, 12,  5)
	ROUND3(eax, ebx, ecx, edx, 0x8F1BBCDC,  1, 11)
	ROUND3(edx, eax, ebx, ecx, 0x8F1BBCDC,  9, 12)
	ROUND3(ecx, edx, eax, ebx, 0x8F1BBCDC, 11, 14)
	ROUND3(ebx, ecx, edx, eax, 0x8F1BBCDC, 10, 15)
	ROUND3(eax, ebx, ecx, edx, 0x8F1BBCDC,  0, 14)
	ROUND3(edx, eax, ebx, ecx, 0x8F1BBCDC,  8, 15)
	ROUND3(ecx, edx, eax, ebx, 0x8F1BBCDC, 12,  9)
	ROUND3(ebx, ecx, edx, eax, 0x8F1BBCDC,  4,  8)
	ROUND3(eax, ebx, ecx, edx, 0x8F1BBCDC, 13,  9)
	ROUND3(edx, eax, ebx, ecx, 0x8F1BBCDC,  3, 14)
	ROUND3(ecx, edx, eax, ebx, 0x8F1BBCDC,  7,  5)
	ROUND3(ebx, ecx, edx, eax, 0x8F1BBCDC, 15,  6)
	ROUND3(eax, ebx, ecx, edx, 0x8F1BBCDC, 14,  8)
	ROUND3(edx, eax, ebx, ecx, 0x8F1BBCDC,  5,  6)
	ROUND3(ecx, edx, eax, ebx, 0x8F1BBCDC,  6,  5)
	ROUND3(ebx, ecx, edx, eax, 0x8F1BBCDC,  2, 12)
	
	/* Save left-side state */
	movl  %eax, 12(%esp)
	movl  %ebx, 16(%esp)
	movl  %ecx, 20(%esp)
	movl  %edx, 24(%esp)
	
	/* Load state */
	movl  36(%esp), %edi  /* state */
	movl   0(%edi), %eax  /* a */
	movl   4(%edi), %ebx  /* b */
	movl   8(%edi), %ecx  /* c */
	movl  12(%edi), %edx  /* d */
	
	/* 64 rounds of right-side hashing */
	ROUND3(eax, ebx, ecx, edx, 0x50A28BE6,  5,  8)
	ROUND3(edx, eax, ebx, ecx, 0x50A28BE6, 14,  9)
	ROUND3(ecx, edx, eax, ebx, 0x50A28BE6,  7,  9)
	ROUND3(ebx, ecx, edx, eax, 0x50A28BE6,  0, 11)
	ROUND3(eax, ebx, ecx, edx, 0x50A28BE6,  9, 13)
	ROUND3(edx, eax, ebx, ecx, 0x50A28BE6,  2, 15)
	ROUND3(ecx, edx, eax, ebx, 0x50A28BE6, 11, 15)
	ROUND3(ebx, ecx, edx, eax, 0x50A28BE6,  4,  5)
	ROUND3(eax, ebx, ecx, edx, 0x50A28BE6, 13,  7)
	ROUND3(edx, eax, ebx, ecx, 0x50A28BE6,  6,  7)
	ROUND3(ecx, edx, eax, ebx, 0x50A28BE6, 15,  8)
	ROUND3(ebx, ecx, edx, eax, 0x50A28BE6,  8, 11)
	ROUND3(eax, ebx, ecx, edx, 0x50A28BE6,  1, 14)
	ROUND3(edx, eax, ebx, ecx, 0x50A28BE6, 10, 14)
	ROUND3(ecx, edx, eax, ebx, 0x50A28BE6,  3, 12)
	ROUND3(ebx, ecx, edx, eax, 0x50A28BE6, 12,  6)
	ROUND2(eax, ebx, ecx, edx, 0x5C4DD124,  6,  9)
	ROUND2(edx, eax, ebx, ecx, 0x5C4DD124, 11, 13)
	ROUND2(ecx, edx, eax, ebx, 0x5C4DD124,  3, 15)
	ROUND2(ebx, ecx, edx, eax, 0x5C4DD124,  7,  7)
	ROUND2(eax, ebx, ecx, edx, 0x5C4DD124,  0, 12)
	ROUND2(edx, eax, ebx, ecx, 0x5C4DD124, 13,  8)
	ROUND2(ecx, edx, eax, ebx, 0x5C4DD124,  5,  9)
	ROUND2(ebx, ecx, edx, eax, 0x5C4DD124, 10, 11)
	ROUND2(eax, ebx, ecx, edx, 0x5C4DD124, 14,  7)
	ROUND2(edx, eax, ebx, ecx, 0x5C4DD124, 15,  7)
	ROUND2(ecx, edx, eax, ebx, 0x5C4DD124,  8, 12)
	ROUND2(ebx, ecx, edx, eax, 0x5C4DD124, 12,  7)
	ROUND2(eax, ebx, ecx, edx, 0x5C4DD124,  4,  6)
	ROUND2(edx, eax, ebx, ecx, 0x5C4DD124,  9, 15)
	ROUND2(ecx, edx, eax, ebx, 0x5C4DD124,  1, 13)
	ROUND2(ebx, ecx, edx, eax, 0x5C4DD124,  2, 11)
	ROUND1(eax, ebx, ecx, edx, 0x6D703EF3, 15,  9)
	ROUND1(edx, eax, ebx, ecx, 0x6D703EF3,  5,  7)
	ROUND1(ecx, edx, eax, ebx, 0x6D703EF3,  1, 15)
	ROUND1(ebx, ecx, edx, eax, 0x6D703EF3,  3, 11)
	ROUND1(eax, ebx, ecx, edx, 0x6D703EF3,  7,  8)
	ROUND1(edx, eax, ebx, ecx, 0x6D703EF3, 14,  6)
	ROUND1(ecx, edx, eax, ebx, 0x6D703EF3,  6,  6)
	ROUND1(ebx, ecx, edx, eax, 0x6D703EF3,  9, 14)
	ROUND1(eax, ebx, ecx, edx, 0x6D703EF3, 11, 12)
	ROUND1(edx, eax, ebx, ecx, 0x6D703EF3,  8, 13)
	ROUND1(ecx, edx, eax, ebx, 0x6D703EF3, 12,  5)
	ROUND1(ebx, ecx, edx, eax, 0x6D703EF3,  2, 14)
	ROUND1(eax, ebx, ecx, edx, 0x6D703EF3, 10, 13)
	ROUND1(edx, eax, ebx, ecx, 0x6D703EF3,  0, 13)
	ROUND1(ecx, edx, eax, ebx, 0x6D703EF3,  4,  7)
	ROUND1(ebx, ecx, edx, eax, 0x6D703EF3, 13,  5)
	ROUND0(eax, ebx, ecx, edx, 0x00000000,  8, 15)
	ROUND0(edx, eax, ebx, ecx, 0x00000000,  6,  5)
	ROUND0(ecx, edx, eax, ebx, 0x00000000,  4,  8)
	ROUND0(ebx, ecx, edx, eax, 0x00000000,  1, 11)
	ROUND0(eax, ebx, ecx, edx, 0x00000000,  3, 14)
	ROUND0(edx, eax, ebx, ecx, 0x00000000, 11, 14)
	ROUND0(ecx, edx, eax, ebx, 0x00000000, 15,  6)
	ROUND0(ebx, ecx, edx, eax, 0x00000000,  0, 14)
	ROUND0(eax, ebx, ecx, edx, 0x00000000,  5,  6)
	ROUND0(edx, eax, ebx, ecx, 0x00000000, 12,  9)
	ROUND0(ecx, edx, eax, ebx, 0x00000000,  2, 12)
	ROUND0(ebx, ecx, edx, eax, 0x00000000, 13,  9)
	ROUND0(eax, ebx, ecx, edx, 0x00000000,  9, 12)
	ROUND0(edx, eax, ebx, ecx, 0x00000000,  7,  5)
	ROUND0(ecx, edx, eax, ebx, 0x00000000, 10, 15)
	ROUND0(ebx, ecx, edx, eax, 0x00000000, 14,  8)
	
	/* Update state and save */
	addl  24(%esp), %eax
	addl  12(%esp), %ebx
	addl  16(%esp), %ecx
	addl  20(%esp), %edx
	movl  36(%esp), %esi
	addl   8(%esi), %eax
	addl  12(%esi), %ebx
	addl   0(%esi), %ecx
	addl   4(%esi), %edx
	movl  %eax,  4(%esi)
	movl  %ebx,  8(%esi)
	movl  %ecx, 12(%esi)
	movl  %edx,  0(%esi)
	
	/* Restore registers */
	movl   0(%esp), %ebx
	movl   4(%esp), %esi
	movl   8(%esp), %edi
	addl  $28, %esp
	retl
