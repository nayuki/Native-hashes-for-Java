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
	 *       4  eax       RIPEMD-160 state variable AL
	 *       4  ebx       RIPEMD-160 state variable BL
	 *       4  ecx       RIPEMD-160 state variable CL
	 *       4  edx       RIPEMD-160 state variable DL
	 *       4  ebp       RIPEMD-160 state variable EL
	 *       4  r10d      RIPEMD-160 state variable AR
	 *       4  r11d      RIPEMD-160 state variable BR
	 *       4  r12d      RIPEMD-160 state variable CR
	 *       4  r13d      RIPEMD-160 state variable DR
	 *       4  r14d      RIPEMD-160 state variable ER
	 *       4  r8d       Temporary for calculation per round
	 *       8  rdi       Base address of block array argument (read-only)
	 *       8  rsi       Base address of state array argument (read-only)
	 *      16  xmm0      Caller's value of rbx (only lower 64 bits are used)
	 *      16  xmm1      Caller's value of rbp (only lower 64 bits are used)
	 *      16  xmm2      Caller's value of r10 (only lower 64 bits are used)
	 *      16  xmm3      Caller's value of r11 (only lower 64 bits are used)
	 *      16  xmm4      Caller's value of r12 (only lower 64 bits are used)
	 *      16  xmm5      Caller's value of r13 (only lower 64 bits are used)
	 *      16  xmm6      Caller's value of r14 (only lower 64 bits are used)
	 */
	
	#define ROUND0(a, b, c, d, e, k, r, s)  \
		movl  %b, %r8d;  \
		xorl  %c, %r8d;  \
		xorl  %d, %r8d;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUND1(a, b, c, d, e, k, r, s)  \
		movl  %c, %r8d;  \
		xorl  %d, %r8d;  \
		andl  %b, %r8d;  \
		xorl  %d, %r8d;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUND2(a, b, c, d, e, k, r, s)  \
		movl  %c, %r8d;  \
		notl  %r8d;      \
		orl   %b, %r8d;  \
		xorl  %d, %r8d;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUND3(a, b, c, d, e, k, r, s)  \
		movl  %b, %r8d;  \
		xorl  %c, %r8d;  \
		andl  %d, %r8d;  \
		xorl  %c, %r8d;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUND4(a, b, c, d, e, k, r, s)  \
		movl  %d, %r8d;  \
		notl  %r8d;      \
		orl   %c, %r8d;  \
		xorl  %b, %r8d;  \
		ROUNDTAIL(a, c, e, k, r, s)
	
	#define ROUNDTAIL(a, c, e, k, r, s)  \
		leal  k(%a, %r8d), %a;  \
		addl  (r*4)(%rdi), %a;  \
		roll  $s, %a;           \
		addl  %e, %a;           \
		roll  $10, %c;
	
	/* Save registers */
	movq  %rbx, %xmm0
	movq  %rbp, %xmm1
	movq  %r10, %xmm2
	movq  %r11, %xmm3
	movq  %r12, %xmm4
	movq  %r13, %xmm5
	movq  %r14, %xmm6
	
	/* 80 rounds of left-side hashing */
	movl   0(%rsi), %eax  /* a */
	movl   4(%rsi), %ebx  /* b */
	movl   8(%rsi), %ecx  /* c */
	movl  12(%rsi), %edx  /* d */
	movl  16(%rsi), %ebp  /* e */
	ROUND0(eax, ebx, ecx, edx, ebp,  0x00000000,  0, 11)
	ROUND0(ebp, eax, ebx, ecx, edx,  0x00000000,  1, 14)
	ROUND0(edx, ebp, eax, ebx, ecx,  0x00000000,  2, 15)
	ROUND0(ecx, edx, ebp, eax, ebx,  0x00000000,  3, 12)
	ROUND0(ebx, ecx, edx, ebp, eax,  0x00000000,  4,  5)
	ROUND0(eax, ebx, ecx, edx, ebp,  0x00000000,  5,  8)
	ROUND0(ebp, eax, ebx, ecx, edx,  0x00000000,  6,  7)
	ROUND0(edx, ebp, eax, ebx, ecx,  0x00000000,  7,  9)
	ROUND0(ecx, edx, ebp, eax, ebx,  0x00000000,  8, 11)
	ROUND0(ebx, ecx, edx, ebp, eax,  0x00000000,  9, 13)
	ROUND0(eax, ebx, ecx, edx, ebp,  0x00000000, 10, 14)
	ROUND0(ebp, eax, ebx, ecx, edx,  0x00000000, 11, 15)
	ROUND0(edx, ebp, eax, ebx, ecx,  0x00000000, 12,  6)
	ROUND0(ecx, edx, ebp, eax, ebx,  0x00000000, 13,  7)
	ROUND0(ebx, ecx, edx, ebp, eax,  0x00000000, 14,  9)
	ROUND0(eax, ebx, ecx, edx, ebp,  0x00000000, 15,  8)
	ROUND1(ebp, eax, ebx, ecx, edx,  0x5A827999,  7,  7)
	ROUND1(edx, ebp, eax, ebx, ecx,  0x5A827999,  4,  6)
	ROUND1(ecx, edx, ebp, eax, ebx,  0x5A827999, 13,  8)
	ROUND1(ebx, ecx, edx, ebp, eax,  0x5A827999,  1, 13)
	ROUND1(eax, ebx, ecx, edx, ebp,  0x5A827999, 10, 11)
	ROUND1(ebp, eax, ebx, ecx, edx,  0x5A827999,  6,  9)
	ROUND1(edx, ebp, eax, ebx, ecx,  0x5A827999, 15,  7)
	ROUND1(ecx, edx, ebp, eax, ebx,  0x5A827999,  3, 15)
	ROUND1(ebx, ecx, edx, ebp, eax,  0x5A827999, 12,  7)
	ROUND1(eax, ebx, ecx, edx, ebp,  0x5A827999,  0, 12)
	ROUND1(ebp, eax, ebx, ecx, edx,  0x5A827999,  9, 15)
	ROUND1(edx, ebp, eax, ebx, ecx,  0x5A827999,  5,  9)
	ROUND1(ecx, edx, ebp, eax, ebx,  0x5A827999,  2, 11)
	ROUND1(ebx, ecx, edx, ebp, eax,  0x5A827999, 14,  7)
	ROUND1(eax, ebx, ecx, edx, ebp,  0x5A827999, 11, 13)
	ROUND1(ebp, eax, ebx, ecx, edx,  0x5A827999,  8, 12)
	ROUND2(edx, ebp, eax, ebx, ecx,  0x6ED9EBA1,  3, 11)
	ROUND2(ecx, edx, ebp, eax, ebx,  0x6ED9EBA1, 10, 13)
	ROUND2(ebx, ecx, edx, ebp, eax,  0x6ED9EBA1, 14,  6)
	ROUND2(eax, ebx, ecx, edx, ebp,  0x6ED9EBA1,  4,  7)
	ROUND2(ebp, eax, ebx, ecx, edx,  0x6ED9EBA1,  9, 14)
	ROUND2(edx, ebp, eax, ebx, ecx,  0x6ED9EBA1, 15,  9)
	ROUND2(ecx, edx, ebp, eax, ebx,  0x6ED9EBA1,  8, 13)
	ROUND2(ebx, ecx, edx, ebp, eax,  0x6ED9EBA1,  1, 15)
	ROUND2(eax, ebx, ecx, edx, ebp,  0x6ED9EBA1,  2, 14)
	ROUND2(ebp, eax, ebx, ecx, edx,  0x6ED9EBA1,  7,  8)
	ROUND2(edx, ebp, eax, ebx, ecx,  0x6ED9EBA1,  0, 13)
	ROUND2(ecx, edx, ebp, eax, ebx,  0x6ED9EBA1,  6,  6)
	ROUND2(ebx, ecx, edx, ebp, eax,  0x6ED9EBA1, 13,  5)
	ROUND2(eax, ebx, ecx, edx, ebp,  0x6ED9EBA1, 11, 12)
	ROUND2(ebp, eax, ebx, ecx, edx,  0x6ED9EBA1,  5,  7)
	ROUND2(edx, ebp, eax, ebx, ecx,  0x6ED9EBA1, 12,  5)
	ROUND3(ecx, edx, ebp, eax, ebx, -0x70E44324,  1, 11)
	ROUND3(ebx, ecx, edx, ebp, eax, -0x70E44324,  9, 12)
	ROUND3(eax, ebx, ecx, edx, ebp, -0x70E44324, 11, 14)
	ROUND3(ebp, eax, ebx, ecx, edx, -0x70E44324, 10, 15)
	ROUND3(edx, ebp, eax, ebx, ecx, -0x70E44324,  0, 14)
	ROUND3(ecx, edx, ebp, eax, ebx, -0x70E44324,  8, 15)
	ROUND3(ebx, ecx, edx, ebp, eax, -0x70E44324, 12,  9)
	ROUND3(eax, ebx, ecx, edx, ebp, -0x70E44324,  4,  8)
	ROUND3(ebp, eax, ebx, ecx, edx, -0x70E44324, 13,  9)
	ROUND3(edx, ebp, eax, ebx, ecx, -0x70E44324,  3, 14)
	ROUND3(ecx, edx, ebp, eax, ebx, -0x70E44324,  7,  5)
	ROUND3(ebx, ecx, edx, ebp, eax, -0x70E44324, 15,  6)
	ROUND3(eax, ebx, ecx, edx, ebp, -0x70E44324, 14,  8)
	ROUND3(ebp, eax, ebx, ecx, edx, -0x70E44324,  5,  6)
	ROUND3(edx, ebp, eax, ebx, ecx, -0x70E44324,  6,  5)
	ROUND3(ecx, edx, ebp, eax, ebx, -0x70E44324,  2, 12)
	ROUND4(ebx, ecx, edx, ebp, eax, -0x56AC02B2,  4,  9)
	ROUND4(eax, ebx, ecx, edx, ebp, -0x56AC02B2,  0, 15)
	ROUND4(ebp, eax, ebx, ecx, edx, -0x56AC02B2,  5,  5)
	ROUND4(edx, ebp, eax, ebx, ecx, -0x56AC02B2,  9, 11)
	ROUND4(ecx, edx, ebp, eax, ebx, -0x56AC02B2,  7,  6)
	ROUND4(ebx, ecx, edx, ebp, eax, -0x56AC02B2, 12,  8)
	ROUND4(eax, ebx, ecx, edx, ebp, -0x56AC02B2,  2, 13)
	ROUND4(ebp, eax, ebx, ecx, edx, -0x56AC02B2, 10, 12)
	ROUND4(edx, ebp, eax, ebx, ecx, -0x56AC02B2, 14,  5)
	ROUND4(ecx, edx, ebp, eax, ebx, -0x56AC02B2,  1, 12)
	ROUND4(ebx, ecx, edx, ebp, eax, -0x56AC02B2,  3, 13)
	ROUND4(eax, ebx, ecx, edx, ebp, -0x56AC02B2,  8, 14)
	ROUND4(ebp, eax, ebx, ecx, edx, -0x56AC02B2, 11, 11)
	ROUND4(edx, ebp, eax, ebx, ecx, -0x56AC02B2,  6,  8)
	ROUND4(ecx, edx, ebp, eax, ebx, -0x56AC02B2, 15,  5)
	ROUND4(ebx, ecx, edx, ebp, eax, -0x56AC02B2, 13,  6)
	
	/* 80 rounds of right-side hashing */
	movl   0(%rsi), %r10d  /* a */
	movl   4(%rsi), %r11d  /* b */
	movl   8(%rsi), %r12d  /* c */
	movl  12(%rsi), %r13d  /* d */
	movl  16(%rsi), %r14d  /* e */
	ROUND4(r10d, r11d, r12d, r13d, r14d, 0x50A28BE6,  5,  8)
	ROUND4(r14d, r10d, r11d, r12d, r13d, 0x50A28BE6, 14,  9)
	ROUND4(r13d, r14d, r10d, r11d, r12d, 0x50A28BE6,  7,  9)
	ROUND4(r12d, r13d, r14d, r10d, r11d, 0x50A28BE6,  0, 11)
	ROUND4(r11d, r12d, r13d, r14d, r10d, 0x50A28BE6,  9, 13)
	ROUND4(r10d, r11d, r12d, r13d, r14d, 0x50A28BE6,  2, 15)
	ROUND4(r14d, r10d, r11d, r12d, r13d, 0x50A28BE6, 11, 15)
	ROUND4(r13d, r14d, r10d, r11d, r12d, 0x50A28BE6,  4,  5)
	ROUND4(r12d, r13d, r14d, r10d, r11d, 0x50A28BE6, 13,  7)
	ROUND4(r11d, r12d, r13d, r14d, r10d, 0x50A28BE6,  6,  7)
	ROUND4(r10d, r11d, r12d, r13d, r14d, 0x50A28BE6, 15,  8)
	ROUND4(r14d, r10d, r11d, r12d, r13d, 0x50A28BE6,  8, 11)
	ROUND4(r13d, r14d, r10d, r11d, r12d, 0x50A28BE6,  1, 14)
	ROUND4(r12d, r13d, r14d, r10d, r11d, 0x50A28BE6, 10, 14)
	ROUND4(r11d, r12d, r13d, r14d, r10d, 0x50A28BE6,  3, 12)
	ROUND4(r10d, r11d, r12d, r13d, r14d, 0x50A28BE6, 12,  6)
	ROUND3(r14d, r10d, r11d, r12d, r13d, 0x5C4DD124,  6,  9)
	ROUND3(r13d, r14d, r10d, r11d, r12d, 0x5C4DD124, 11, 13)
	ROUND3(r12d, r13d, r14d, r10d, r11d, 0x5C4DD124,  3, 15)
	ROUND3(r11d, r12d, r13d, r14d, r10d, 0x5C4DD124,  7,  7)
	ROUND3(r10d, r11d, r12d, r13d, r14d, 0x5C4DD124,  0, 12)
	ROUND3(r14d, r10d, r11d, r12d, r13d, 0x5C4DD124, 13,  8)
	ROUND3(r13d, r14d, r10d, r11d, r12d, 0x5C4DD124,  5,  9)
	ROUND3(r12d, r13d, r14d, r10d, r11d, 0x5C4DD124, 10, 11)
	ROUND3(r11d, r12d, r13d, r14d, r10d, 0x5C4DD124, 14,  7)
	ROUND3(r10d, r11d, r12d, r13d, r14d, 0x5C4DD124, 15,  7)
	ROUND3(r14d, r10d, r11d, r12d, r13d, 0x5C4DD124,  8, 12)
	ROUND3(r13d, r14d, r10d, r11d, r12d, 0x5C4DD124, 12,  7)
	ROUND3(r12d, r13d, r14d, r10d, r11d, 0x5C4DD124,  4,  6)
	ROUND3(r11d, r12d, r13d, r14d, r10d, 0x5C4DD124,  9, 15)
	ROUND3(r10d, r11d, r12d, r13d, r14d, 0x5C4DD124,  1, 13)
	ROUND3(r14d, r10d, r11d, r12d, r13d, 0x5C4DD124,  2, 11)
	ROUND2(r13d, r14d, r10d, r11d, r12d, 0x6D703EF3, 15,  9)
	ROUND2(r12d, r13d, r14d, r10d, r11d, 0x6D703EF3,  5,  7)
	ROUND2(r11d, r12d, r13d, r14d, r10d, 0x6D703EF3,  1, 15)
	ROUND2(r10d, r11d, r12d, r13d, r14d, 0x6D703EF3,  3, 11)
	ROUND2(r14d, r10d, r11d, r12d, r13d, 0x6D703EF3,  7,  8)
	ROUND2(r13d, r14d, r10d, r11d, r12d, 0x6D703EF3, 14,  6)
	ROUND2(r12d, r13d, r14d, r10d, r11d, 0x6D703EF3,  6,  6)
	ROUND2(r11d, r12d, r13d, r14d, r10d, 0x6D703EF3,  9, 14)
	ROUND2(r10d, r11d, r12d, r13d, r14d, 0x6D703EF3, 11, 12)
	ROUND2(r14d, r10d, r11d, r12d, r13d, 0x6D703EF3,  8, 13)
	ROUND2(r13d, r14d, r10d, r11d, r12d, 0x6D703EF3, 12,  5)
	ROUND2(r12d, r13d, r14d, r10d, r11d, 0x6D703EF3,  2, 14)
	ROUND2(r11d, r12d, r13d, r14d, r10d, 0x6D703EF3, 10, 13)
	ROUND2(r10d, r11d, r12d, r13d, r14d, 0x6D703EF3,  0, 13)
	ROUND2(r14d, r10d, r11d, r12d, r13d, 0x6D703EF3,  4,  7)
	ROUND2(r13d, r14d, r10d, r11d, r12d, 0x6D703EF3, 13,  5)
	ROUND1(r12d, r13d, r14d, r10d, r11d, 0x7A6D76E9,  8, 15)
	ROUND1(r11d, r12d, r13d, r14d, r10d, 0x7A6D76E9,  6,  5)
	ROUND1(r10d, r11d, r12d, r13d, r14d, 0x7A6D76E9,  4,  8)
	ROUND1(r14d, r10d, r11d, r12d, r13d, 0x7A6D76E9,  1, 11)
	ROUND1(r13d, r14d, r10d, r11d, r12d, 0x7A6D76E9,  3, 14)
	ROUND1(r12d, r13d, r14d, r10d, r11d, 0x7A6D76E9, 11, 14)
	ROUND1(r11d, r12d, r13d, r14d, r10d, 0x7A6D76E9, 15,  6)
	ROUND1(r10d, r11d, r12d, r13d, r14d, 0x7A6D76E9,  0, 14)
	ROUND1(r14d, r10d, r11d, r12d, r13d, 0x7A6D76E9,  5,  6)
	ROUND1(r13d, r14d, r10d, r11d, r12d, 0x7A6D76E9, 12,  9)
	ROUND1(r12d, r13d, r14d, r10d, r11d, 0x7A6D76E9,  2, 12)
	ROUND1(r11d, r12d, r13d, r14d, r10d, 0x7A6D76E9, 13,  9)
	ROUND1(r10d, r11d, r12d, r13d, r14d, 0x7A6D76E9,  9, 12)
	ROUND1(r14d, r10d, r11d, r12d, r13d, 0x7A6D76E9,  7,  5)
	ROUND1(r13d, r14d, r10d, r11d, r12d, 0x7A6D76E9, 10, 15)
	ROUND1(r12d, r13d, r14d, r10d, r11d, 0x7A6D76E9, 14,  8)
	ROUND0(r11d, r12d, r13d, r14d, r10d, 0x00000000, 12,  8)
	ROUND0(r10d, r11d, r12d, r13d, r14d, 0x00000000, 15,  5)
	ROUND0(r14d, r10d, r11d, r12d, r13d, 0x00000000, 10, 12)
	ROUND0(r13d, r14d, r10d, r11d, r12d, 0x00000000,  4,  9)
	ROUND0(r12d, r13d, r14d, r10d, r11d, 0x00000000,  1, 12)
	ROUND0(r11d, r12d, r13d, r14d, r10d, 0x00000000,  5,  5)
	ROUND0(r10d, r11d, r12d, r13d, r14d, 0x00000000,  8, 14)
	ROUND0(r14d, r10d, r11d, r12d, r13d, 0x00000000,  7,  6)
	ROUND0(r13d, r14d, r10d, r11d, r12d, 0x00000000,  6,  8)
	ROUND0(r12d, r13d, r14d, r10d, r11d, 0x00000000,  2, 13)
	ROUND0(r11d, r12d, r13d, r14d, r10d, 0x00000000, 13,  6)
	ROUND0(r10d, r11d, r12d, r13d, r14d, 0x00000000, 14,  5)
	ROUND0(r14d, r10d, r11d, r12d, r13d, 0x00000000,  0, 15)
	ROUND0(r13d, r14d, r10d, r11d, r12d, 0x00000000,  3, 13)
	ROUND0(r12d, r13d, r14d, r10d, r11d, 0x00000000,  9, 11)
	ROUND0(r11d, r12d, r13d, r14d, r10d, 0x00000000, 11, 11)
	
	/* Update state and save */
	addl  %r11d, %eax
	addl  %r12d, %ebx
	addl  %r13d, %ecx
	addl  %r14d, %edx
	addl  %r10d, %ebp
	addl  16(%rsi), %eax
	addl   0(%rsi), %ebx
	addl   4(%rsi), %ecx
	addl   8(%rsi), %edx
	addl  12(%rsi), %ebp
	movl  %eax, 12(%rsi)
	movl  %ebx, 16(%rsi)
	movl  %ecx,  0(%rsi)
	movl  %edx,  4(%rsi)
	movl  %ebp,  8(%rsi)
	
	/* Restore registers */
	movq  %xmm0, %rbx
	movq  %xmm1, %rbp
	movq  %xmm2, %r10
	movq  %xmm3, %r11
	movq  %xmm4, %r12
	movq  %xmm5, %r13
	movq  %xmm6, %r14
	retq
