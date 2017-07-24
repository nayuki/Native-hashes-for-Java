/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki. (MIT License)
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */


/* void ripemd160_compress_block(const uint8_t block[64], uint32_t state[5]) */
.globl ripemd160_compress_block
ripemd160_compress_block:
	/* 
	 * Storage usage:
	 *   Bytes  Location  Description
	 *       4  eax       RIPEMD-160 state variable A
	 *       4  ebx       RIPEMD-160 state variable B
	 *       4  ecx       RIPEMD-160 state variable C
	 *       4  edx       RIPEMD-160 state variable D
	 *       4  ebp       RIPEMD-160 state variable E
	 *       4  edi       Temporary for calculation per round
	 *       4  esi       Base address of block array argument (read-only)
	 *       4  esp       x86 stack pointer
	 *       4  [esp+ 0]  Caller's value of ebx
	 *       4  [esp+ 4]  Caller's value of esi
	 *       4  [esp+ 8]  Caller's value of edi
	 *       4  [esp+12]  Caller's value of ebp
	 *      20  [esp+16]  RIPEMD-160 state variables AL, BL, CL, DL, EL
	 */
	
	#define ROUND0(a, b, c, d, e, k, r, s)  \
		movl  %b, %edi;  \
		xorl  %c, %edi;  \
		xorl  %d, %edi;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUND1(a, b, c, d, e, k, r, s)  \
		movl  %c, %edi;  \
		xorl  %d, %edi;  \
		andl  %b, %edi;  \
		xorl  %d, %edi;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUND2(a, b, c, d, e, k, r, s)  \
		movl  %c, %edi;  \
		notl  %edi;      \
		orl   %b, %edi;  \
		xorl  %d, %edi;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUND3(a, b, c, d, e, k, r, s)  \
		movl  %b, %edi;  \
		xorl  %c, %edi;  \
		andl  %d, %edi;  \
		xorl  %c, %edi;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUND4(a, b, c, d, e, k, r, s)  \
		movl  %d, %edi;  \
		notl  %edi;      \
		orl   %c, %edi;  \
		xorl  %b, %edi;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUNDTAIL(a, c, e, k, r, s)  \
		leal  k(%a, %edi), %a;  \
		addl  (r*4)(%esi), %a;  \
		roll  $s, %a;           \
		addl  %e, %a;           \
		roll  $10, %c;
	
	/* Save registers */
	subl  $36, %esp
	movl  %ebx,  0(%esp)
	movl  %esi,  4(%esp)
	movl  %edi,  8(%esp)
	movl  %ebp, 12(%esp)
	
	/* Load arguments */
	movl  40(%esp), %esi  /* block */
	movl  44(%esp), %edi  /* state */
	movl   0(%edi), %eax  /* a */
	movl   4(%edi), %ebx  /* b */
	movl   8(%edi), %ecx  /* c */
	movl  12(%edi), %edx  /* d */
	movl  16(%edi), %ebp  /* e */
	
	/* 80 rounds of left-side hashing */
	ROUND0(eax, ebx, ecx, edx, ebp, 0x00000000,  0, 11)
	ROUND0(ebp, eax, ebx, ecx, edx, 0x00000000,  1, 14)
	ROUND0(edx, ebp, eax, ebx, ecx, 0x00000000,  2, 15)
	ROUND0(ecx, edx, ebp, eax, ebx, 0x00000000,  3, 12)
	ROUND0(ebx, ecx, edx, ebp, eax, 0x00000000,  4,  5)
	ROUND0(eax, ebx, ecx, edx, ebp, 0x00000000,  5,  8)
	ROUND0(ebp, eax, ebx, ecx, edx, 0x00000000,  6,  7)
	ROUND0(edx, ebp, eax, ebx, ecx, 0x00000000,  7,  9)
	ROUND0(ecx, edx, ebp, eax, ebx, 0x00000000,  8, 11)
	ROUND0(ebx, ecx, edx, ebp, eax, 0x00000000,  9, 13)
	ROUND0(eax, ebx, ecx, edx, ebp, 0x00000000, 10, 14)
	ROUND0(ebp, eax, ebx, ecx, edx, 0x00000000, 11, 15)
	ROUND0(edx, ebp, eax, ebx, ecx, 0x00000000, 12,  6)
	ROUND0(ecx, edx, ebp, eax, ebx, 0x00000000, 13,  7)
	ROUND0(ebx, ecx, edx, ebp, eax, 0x00000000, 14,  9)
	ROUND0(eax, ebx, ecx, edx, ebp, 0x00000000, 15,  8)
	ROUND1(ebp, eax, ebx, ecx, edx, 0x5A827999,  7,  7)
	ROUND1(edx, ebp, eax, ebx, ecx, 0x5A827999,  4,  6)
	ROUND1(ecx, edx, ebp, eax, ebx, 0x5A827999, 13,  8)
	ROUND1(ebx, ecx, edx, ebp, eax, 0x5A827999,  1, 13)
	ROUND1(eax, ebx, ecx, edx, ebp, 0x5A827999, 10, 11)
	ROUND1(ebp, eax, ebx, ecx, edx, 0x5A827999,  6,  9)
	ROUND1(edx, ebp, eax, ebx, ecx, 0x5A827999, 15,  7)
	ROUND1(ecx, edx, ebp, eax, ebx, 0x5A827999,  3, 15)
	ROUND1(ebx, ecx, edx, ebp, eax, 0x5A827999, 12,  7)
	ROUND1(eax, ebx, ecx, edx, ebp, 0x5A827999,  0, 12)
	ROUND1(ebp, eax, ebx, ecx, edx, 0x5A827999,  9, 15)
	ROUND1(edx, ebp, eax, ebx, ecx, 0x5A827999,  5,  9)
	ROUND1(ecx, edx, ebp, eax, ebx, 0x5A827999,  2, 11)
	ROUND1(ebx, ecx, edx, ebp, eax, 0x5A827999, 14,  7)
	ROUND1(eax, ebx, ecx, edx, ebp, 0x5A827999, 11, 13)
	ROUND1(ebp, eax, ebx, ecx, edx, 0x5A827999,  8, 12)
	ROUND2(edx, ebp, eax, ebx, ecx, 0x6ED9EBA1,  3, 11)
	ROUND2(ecx, edx, ebp, eax, ebx, 0x6ED9EBA1, 10, 13)
	ROUND2(ebx, ecx, edx, ebp, eax, 0x6ED9EBA1, 14,  6)
	ROUND2(eax, ebx, ecx, edx, ebp, 0x6ED9EBA1,  4,  7)
	ROUND2(ebp, eax, ebx, ecx, edx, 0x6ED9EBA1,  9, 14)
	ROUND2(edx, ebp, eax, ebx, ecx, 0x6ED9EBA1, 15,  9)
	ROUND2(ecx, edx, ebp, eax, ebx, 0x6ED9EBA1,  8, 13)
	ROUND2(ebx, ecx, edx, ebp, eax, 0x6ED9EBA1,  1, 15)
	ROUND2(eax, ebx, ecx, edx, ebp, 0x6ED9EBA1,  2, 14)
	ROUND2(ebp, eax, ebx, ecx, edx, 0x6ED9EBA1,  7,  8)
	ROUND2(edx, ebp, eax, ebx, ecx, 0x6ED9EBA1,  0, 13)
	ROUND2(ecx, edx, ebp, eax, ebx, 0x6ED9EBA1,  6,  6)
	ROUND2(ebx, ecx, edx, ebp, eax, 0x6ED9EBA1, 13,  5)
	ROUND2(eax, ebx, ecx, edx, ebp, 0x6ED9EBA1, 11, 12)
	ROUND2(ebp, eax, ebx, ecx, edx, 0x6ED9EBA1,  5,  7)
	ROUND2(edx, ebp, eax, ebx, ecx, 0x6ED9EBA1, 12,  5)
	ROUND3(ecx, edx, ebp, eax, ebx, 0x8F1BBCDC,  1, 11)
	ROUND3(ebx, ecx, edx, ebp, eax, 0x8F1BBCDC,  9, 12)
	ROUND3(eax, ebx, ecx, edx, ebp, 0x8F1BBCDC, 11, 14)
	ROUND3(ebp, eax, ebx, ecx, edx, 0x8F1BBCDC, 10, 15)
	ROUND3(edx, ebp, eax, ebx, ecx, 0x8F1BBCDC,  0, 14)
	ROUND3(ecx, edx, ebp, eax, ebx, 0x8F1BBCDC,  8, 15)
	ROUND3(ebx, ecx, edx, ebp, eax, 0x8F1BBCDC, 12,  9)
	ROUND3(eax, ebx, ecx, edx, ebp, 0x8F1BBCDC,  4,  8)
	ROUND3(ebp, eax, ebx, ecx, edx, 0x8F1BBCDC, 13,  9)
	ROUND3(edx, ebp, eax, ebx, ecx, 0x8F1BBCDC,  3, 14)
	ROUND3(ecx, edx, ebp, eax, ebx, 0x8F1BBCDC,  7,  5)
	ROUND3(ebx, ecx, edx, ebp, eax, 0x8F1BBCDC, 15,  6)
	ROUND3(eax, ebx, ecx, edx, ebp, 0x8F1BBCDC, 14,  8)
	ROUND3(ebp, eax, ebx, ecx, edx, 0x8F1BBCDC,  5,  6)
	ROUND3(edx, ebp, eax, ebx, ecx, 0x8F1BBCDC,  6,  5)
	ROUND3(ecx, edx, ebp, eax, ebx, 0x8F1BBCDC,  2, 12)
	ROUND4(ebx, ecx, edx, ebp, eax, 0xA953FD4E,  4,  9)
	ROUND4(eax, ebx, ecx, edx, ebp, 0xA953FD4E,  0, 15)
	ROUND4(ebp, eax, ebx, ecx, edx, 0xA953FD4E,  5,  5)
	ROUND4(edx, ebp, eax, ebx, ecx, 0xA953FD4E,  9, 11)
	ROUND4(ecx, edx, ebp, eax, ebx, 0xA953FD4E,  7,  6)
	ROUND4(ebx, ecx, edx, ebp, eax, 0xA953FD4E, 12,  8)
	ROUND4(eax, ebx, ecx, edx, ebp, 0xA953FD4E,  2, 13)
	ROUND4(ebp, eax, ebx, ecx, edx, 0xA953FD4E, 10, 12)
	ROUND4(edx, ebp, eax, ebx, ecx, 0xA953FD4E, 14,  5)
	ROUND4(ecx, edx, ebp, eax, ebx, 0xA953FD4E,  1, 12)
	ROUND4(ebx, ecx, edx, ebp, eax, 0xA953FD4E,  3, 13)
	ROUND4(eax, ebx, ecx, edx, ebp, 0xA953FD4E,  8, 14)
	ROUND4(ebp, eax, ebx, ecx, edx, 0xA953FD4E, 11, 11)
	ROUND4(edx, ebp, eax, ebx, ecx, 0xA953FD4E,  6,  8)
	ROUND4(ecx, edx, ebp, eax, ebx, 0xA953FD4E, 15,  5)
	ROUND4(ebx, ecx, edx, ebp, eax, 0xA953FD4E, 13,  6)
	
	/* Save left-side state */
	movl  %eax, 16(%esp)
	movl  %ebx, 20(%esp)
	movl  %ecx, 24(%esp)
	movl  %edx, 28(%esp)
	movl  %ebp, 32(%esp)
	
	/* Load state */
	movl  44(%esp), %edi  /* state */
	movl   0(%edi), %eax  /* a */
	movl   4(%edi), %ebx  /* b */
	movl   8(%edi), %ecx  /* c */
	movl  12(%edi), %edx  /* d */
	movl  16(%edi), %ebp  /* e */
	
	/* 80 rounds of right-side hashing */
	ROUND4(eax, ebx, ecx, edx, ebp, 0x50A28BE6,  5,  8)
	ROUND4(ebp, eax, ebx, ecx, edx, 0x50A28BE6, 14,  9)
	ROUND4(edx, ebp, eax, ebx, ecx, 0x50A28BE6,  7,  9)
	ROUND4(ecx, edx, ebp, eax, ebx, 0x50A28BE6,  0, 11)
	ROUND4(ebx, ecx, edx, ebp, eax, 0x50A28BE6,  9, 13)
	ROUND4(eax, ebx, ecx, edx, ebp, 0x50A28BE6,  2, 15)
	ROUND4(ebp, eax, ebx, ecx, edx, 0x50A28BE6, 11, 15)
	ROUND4(edx, ebp, eax, ebx, ecx, 0x50A28BE6,  4,  5)
	ROUND4(ecx, edx, ebp, eax, ebx, 0x50A28BE6, 13,  7)
	ROUND4(ebx, ecx, edx, ebp, eax, 0x50A28BE6,  6,  7)
	ROUND4(eax, ebx, ecx, edx, ebp, 0x50A28BE6, 15,  8)
	ROUND4(ebp, eax, ebx, ecx, edx, 0x50A28BE6,  8, 11)
	ROUND4(edx, ebp, eax, ebx, ecx, 0x50A28BE6,  1, 14)
	ROUND4(ecx, edx, ebp, eax, ebx, 0x50A28BE6, 10, 14)
	ROUND4(ebx, ecx, edx, ebp, eax, 0x50A28BE6,  3, 12)
	ROUND4(eax, ebx, ecx, edx, ebp, 0x50A28BE6, 12,  6)
	ROUND3(ebp, eax, ebx, ecx, edx, 0x5C4DD124,  6,  9)
	ROUND3(edx, ebp, eax, ebx, ecx, 0x5C4DD124, 11, 13)
	ROUND3(ecx, edx, ebp, eax, ebx, 0x5C4DD124,  3, 15)
	ROUND3(ebx, ecx, edx, ebp, eax, 0x5C4DD124,  7,  7)
	ROUND3(eax, ebx, ecx, edx, ebp, 0x5C4DD124,  0, 12)
	ROUND3(ebp, eax, ebx, ecx, edx, 0x5C4DD124, 13,  8)
	ROUND3(edx, ebp, eax, ebx, ecx, 0x5C4DD124,  5,  9)
	ROUND3(ecx, edx, ebp, eax, ebx, 0x5C4DD124, 10, 11)
	ROUND3(ebx, ecx, edx, ebp, eax, 0x5C4DD124, 14,  7)
	ROUND3(eax, ebx, ecx, edx, ebp, 0x5C4DD124, 15,  7)
	ROUND3(ebp, eax, ebx, ecx, edx, 0x5C4DD124,  8, 12)
	ROUND3(edx, ebp, eax, ebx, ecx, 0x5C4DD124, 12,  7)
	ROUND3(ecx, edx, ebp, eax, ebx, 0x5C4DD124,  4,  6)
	ROUND3(ebx, ecx, edx, ebp, eax, 0x5C4DD124,  9, 15)
	ROUND3(eax, ebx, ecx, edx, ebp, 0x5C4DD124,  1, 13)
	ROUND3(ebp, eax, ebx, ecx, edx, 0x5C4DD124,  2, 11)
	ROUND2(edx, ebp, eax, ebx, ecx, 0x6D703EF3, 15,  9)
	ROUND2(ecx, edx, ebp, eax, ebx, 0x6D703EF3,  5,  7)
	ROUND2(ebx, ecx, edx, ebp, eax, 0x6D703EF3,  1, 15)
	ROUND2(eax, ebx, ecx, edx, ebp, 0x6D703EF3,  3, 11)
	ROUND2(ebp, eax, ebx, ecx, edx, 0x6D703EF3,  7,  8)
	ROUND2(edx, ebp, eax, ebx, ecx, 0x6D703EF3, 14,  6)
	ROUND2(ecx, edx, ebp, eax, ebx, 0x6D703EF3,  6,  6)
	ROUND2(ebx, ecx, edx, ebp, eax, 0x6D703EF3,  9, 14)
	ROUND2(eax, ebx, ecx, edx, ebp, 0x6D703EF3, 11, 12)
	ROUND2(ebp, eax, ebx, ecx, edx, 0x6D703EF3,  8, 13)
	ROUND2(edx, ebp, eax, ebx, ecx, 0x6D703EF3, 12,  5)
	ROUND2(ecx, edx, ebp, eax, ebx, 0x6D703EF3,  2, 14)
	ROUND2(ebx, ecx, edx, ebp, eax, 0x6D703EF3, 10, 13)
	ROUND2(eax, ebx, ecx, edx, ebp, 0x6D703EF3,  0, 13)
	ROUND2(ebp, eax, ebx, ecx, edx, 0x6D703EF3,  4,  7)
	ROUND2(edx, ebp, eax, ebx, ecx, 0x6D703EF3, 13,  5)
	ROUND1(ecx, edx, ebp, eax, ebx, 0x7A6D76E9,  8, 15)
	ROUND1(ebx, ecx, edx, ebp, eax, 0x7A6D76E9,  6,  5)
	ROUND1(eax, ebx, ecx, edx, ebp, 0x7A6D76E9,  4,  8)
	ROUND1(ebp, eax, ebx, ecx, edx, 0x7A6D76E9,  1, 11)
	ROUND1(edx, ebp, eax, ebx, ecx, 0x7A6D76E9,  3, 14)
	ROUND1(ecx, edx, ebp, eax, ebx, 0x7A6D76E9, 11, 14)
	ROUND1(ebx, ecx, edx, ebp, eax, 0x7A6D76E9, 15,  6)
	ROUND1(eax, ebx, ecx, edx, ebp, 0x7A6D76E9,  0, 14)
	ROUND1(ebp, eax, ebx, ecx, edx, 0x7A6D76E9,  5,  6)
	ROUND1(edx, ebp, eax, ebx, ecx, 0x7A6D76E9, 12,  9)
	ROUND1(ecx, edx, ebp, eax, ebx, 0x7A6D76E9,  2, 12)
	ROUND1(ebx, ecx, edx, ebp, eax, 0x7A6D76E9, 13,  9)
	ROUND1(eax, ebx, ecx, edx, ebp, 0x7A6D76E9,  9, 12)
	ROUND1(ebp, eax, ebx, ecx, edx, 0x7A6D76E9,  7,  5)
	ROUND1(edx, ebp, eax, ebx, ecx, 0x7A6D76E9, 10, 15)
	ROUND1(ecx, edx, ebp, eax, ebx, 0x7A6D76E9, 14,  8)
	ROUND0(ebx, ecx, edx, ebp, eax, 0x00000000, 12,  8)
	ROUND0(eax, ebx, ecx, edx, ebp, 0x00000000, 15,  5)
	ROUND0(ebp, eax, ebx, ecx, edx, 0x00000000, 10, 12)
	ROUND0(edx, ebp, eax, ebx, ecx, 0x00000000,  4,  9)
	ROUND0(ecx, edx, ebp, eax, ebx, 0x00000000,  1, 12)
	ROUND0(ebx, ecx, edx, ebp, eax, 0x00000000,  5,  5)
	ROUND0(eax, ebx, ecx, edx, ebp, 0x00000000,  8, 14)
	ROUND0(ebp, eax, ebx, ecx, edx, 0x00000000,  7,  6)
	ROUND0(edx, ebp, eax, ebx, ecx, 0x00000000,  6,  8)
	ROUND0(ecx, edx, ebp, eax, ebx, 0x00000000,  2, 13)
	ROUND0(ebx, ecx, edx, ebp, eax, 0x00000000, 13,  6)
	ROUND0(eax, ebx, ecx, edx, ebp, 0x00000000, 14,  5)
	ROUND0(ebp, eax, ebx, ecx, edx, 0x00000000,  0, 15)
	ROUND0(edx, ebp, eax, ebx, ecx, 0x00000000,  3, 13)
	ROUND0(ecx, edx, ebp, eax, ebx, 0x00000000,  9, 11)
	ROUND0(ebx, ecx, edx, ebp, eax, 0x00000000, 11, 11)
	
	/* Update state and save */
	addl  32(%esp), %eax
	addl  16(%esp), %ebx
	addl  20(%esp), %ecx
	addl  24(%esp), %edx
	addl  28(%esp), %ebp
	movl  44(%esp), %esi
	addl  12(%esi), %eax
	addl  16(%esi), %ebx
	addl   0(%esi), %ecx
	addl   4(%esi), %edx
	addl   8(%esi), %ebp
	movl  %eax,  8(%esi)
	movl  %ebx, 12(%esi)
	movl  %ecx, 16(%esi)
	movl  %edx,  0(%esi)
	movl  %ebp,  4(%esi)
	
	/* Restore registers */
	movl   0(%esp), %ebx
	movl   4(%esp), %esi
	movl   8(%esp), %edi
	movl  12(%esp), %ebp
	addl  $36, %esp
	retl
