/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * http://www.nayuki.io/page/native-hash-functions-for-java
 */


/* void md4_compress_block(const uint8_t block[64], uint32_t state[4]) */
.globl md4_compress_block
md4_compress_block:
	/* 
	 * Storage usage:
	 *   Bytes  Location  Description
	 *       4  eax       MD4 state variable A
	 *       4  ebx       MD4 state variable B
	 *       4  ecx       MD4 state variable C
	 *       4  edx       MD4 state variable D
	 *       4  esi       Temporary for calculation per round
	 *       4  edi       Temporary for calculation per round
	 *       4  ebp       Base address of block array argument (read-only)
	 *       4  esp       x86 stack pointer
	 *       4  [esp+ 0]  Caller's value of ebx
	 *       4  [esp+ 4]  Caller's value of esi
	 *       4  [esp+ 8]  Caller's value of edi
	 *       4  [esp+12]  Caller's value of ebp
	 */
	
	#define ROUND0(a, b, c, d, k, s)  \
		movl  %c, %esi;         \
		addl  (k*4)(%ebp), %a;  \
		xorl  %d, %esi;         \
		andl  %b, %esi;         \
		xorl  %d, %esi;         \
		addl  %esi, %a;         \
		roll  $s, %a;
	
	#define ROUND1(a, b, c, d, k, s)  \
		movl  %b, %esi;                 \
		movl  %b, %edi;                 \
		orl   %c, %esi;                 \
		andl  %c, %edi;                 \
		addl  (k*4)(%ebp), %a;          \
		andl  %d, %esi;                 \
		orl   %edi, %esi;               \
		leal  0x5A827999(%esi,%a), %a;  \
		roll  $s, %a;
	
	#define ROUND2(a, b, c, d, k, s)  \
		movl  %c, %esi;                 \
		addl  (k*4)(%ebp), %a;          \
		xorl  %d, %esi;                 \
		xorl  %b, %esi;                 \
		leal  0x6ED9EBA1(%esi,%a), %a;  \
		roll  $s, %a;
	
	/* Save registers */
	subl  $16, %esp
	movl  %ebx,  0(%esp)
	movl  %esi,  4(%esp)
	movl  %edi,  8(%esp)
	movl  %ebp, 12(%esp)
	
	/* Load arguments */
	movl  24(%esp), %esi  /* state */
	movl  20(%esp), %ebp  /* block */
	movl   0(%esi), %eax  /* a */
	movl   4(%esi), %ebx  /* b */
	movl   8(%esi), %ecx  /* c */
	movl  12(%esi), %edx  /* d */
	
	/* 64 rounds of hashing */
	ROUND0(eax, ebx, ecx, edx,  0,  3)
	ROUND0(edx, eax, ebx, ecx,  1,  7)
	ROUND0(ecx, edx, eax, ebx,  2, 11)
	ROUND0(ebx, ecx, edx, eax,  3, 19)
	ROUND0(eax, ebx, ecx, edx,  4,  3)
	ROUND0(edx, eax, ebx, ecx,  5,  7)
	ROUND0(ecx, edx, eax, ebx,  6, 11)
	ROUND0(ebx, ecx, edx, eax,  7, 19)
	ROUND0(eax, ebx, ecx, edx,  8,  3)
	ROUND0(edx, eax, ebx, ecx,  9,  7)
	ROUND0(ecx, edx, eax, ebx, 10, 11)
	ROUND0(ebx, ecx, edx, eax, 11, 19)
	ROUND0(eax, ebx, ecx, edx, 12,  3)
	ROUND0(edx, eax, ebx, ecx, 13,  7)
	ROUND0(ecx, edx, eax, ebx, 14, 11)
	ROUND0(ebx, ecx, edx, eax, 15, 19)
	ROUND1(eax, ebx, ecx, edx,  0,  3)
	ROUND1(edx, eax, ebx, ecx,  4,  5)
	ROUND1(ecx, edx, eax, ebx,  8,  9)
	ROUND1(ebx, ecx, edx, eax, 12, 13)
	ROUND1(eax, ebx, ecx, edx,  1,  3)
	ROUND1(edx, eax, ebx, ecx,  5,  5)
	ROUND1(ecx, edx, eax, ebx,  9,  9)
	ROUND1(ebx, ecx, edx, eax, 13, 13)
	ROUND1(eax, ebx, ecx, edx,  2,  3)
	ROUND1(edx, eax, ebx, ecx,  6,  5)
	ROUND1(ecx, edx, eax, ebx, 10,  9)
	ROUND1(ebx, ecx, edx, eax, 14, 13)
	ROUND1(eax, ebx, ecx, edx,  3,  3)
	ROUND1(edx, eax, ebx, ecx,  7,  5)
	ROUND1(ecx, edx, eax, ebx, 11,  9)
	ROUND1(ebx, ecx, edx, eax, 15, 13)
	ROUND2(eax, ebx, ecx, edx,  0,  3)
	ROUND2(edx, eax, ebx, ecx,  8,  9)
	ROUND2(ecx, edx, eax, ebx,  4, 11)
	ROUND2(ebx, ecx, edx, eax, 12, 15)
	ROUND2(eax, ebx, ecx, edx,  2,  3)
	ROUND2(edx, eax, ebx, ecx, 10,  9)
	ROUND2(ecx, edx, eax, ebx,  6, 11)
	ROUND2(ebx, ecx, edx, eax, 14, 15)
	ROUND2(eax, ebx, ecx, edx,  1,  3)
	ROUND2(edx, eax, ebx, ecx,  9,  9)
	ROUND2(ecx, edx, eax, ebx,  5, 11)
	ROUND2(ebx, ecx, edx, eax, 13, 15)
	ROUND2(eax, ebx, ecx, edx,  3,  3)
	ROUND2(edx, eax, ebx, ecx, 11,  9)
	ROUND2(ecx, edx, eax, ebx,  7, 11)
	ROUND2(ebx, ecx, edx, eax, 15, 15)
	
	/* Save updated state */
	movl  24(%esp), %esi
	addl  %eax,  0(%esi)
	addl  %ebx,  4(%esi)
	addl  %ecx,  8(%esi)
	addl  %edx, 12(%esi)
	
	/* Restore registers */
	movl   0(%esp), %ebx
	movl   4(%esp), %esi
	movl   8(%esp), %edi
	movl  12(%esp), %ebp
	addl  $16, %esp
	retl
