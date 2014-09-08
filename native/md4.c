#include <stdint.h>
#include <jni.h>


static void md4_compress_block(const jbyte *block, uint32_t state[4]);


/*
 * Class:     nayuki_nativehash_Md4
 * Method:    compress
 * Signature: ([I[BII)Z
 */
JNIEXPORT jboolean JNICALL Java_nayuki_nativehash_Md4_compress(JNIEnv *env, jclass thisClass, jintArray stateArray, jbyteArray msg, jint off, jint len) {
	JNIEnv theEnv = *env;
	
	// Get state array and convert to uint32_t
	jint *stateJava = theEnv->GetIntArrayElements(env, stateArray, NULL);
	if (stateJava == NULL)
		return 0;
	unsigned int i;
	uint32_t state[4];
	for (i = 0; i < 4; i++)
		state[i] = (uint32_t)stateJava[i];
	
	// Iterate over each block in msg. Requires len to be a multiple of 64
	jbyte *block = theEnv->GetPrimitiveArrayCritical(env, msg, NULL);
	if (block == NULL)
		return 0;
	size_t newoff;
	size_t newlen = len;
	for (newoff = 0; newoff < newlen; newoff += 64)
		md4_compress_block(block + off + newoff, state);
	theEnv->ReleasePrimitiveArrayCritical(env, msg, block, JNI_ABORT);
	
	// Convert state array to jint and clean up
	for (i = 0; i < 4; i++)
		stateJava[i] = (jint)state[i];
	theEnv->ReleaseIntArrayElements(env, stateArray, stateJava, 0);
	return 1;
}


static void md4_compress_block(const jbyte *block, uint32_t state[4]) {
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
	
	#define ROUND0(a, b, c, d, k, s)  a += (d ^ (b & (c ^ d)))       + schedule[k]             ;  a = (a << s | a >> (32 - s));
	#define ROUND1(a, b, c, d, k, s)  a += ((b & c) | (d & (b | c))) + schedule[k] + 0x5A827999;  a = (a << s | a >> (32 - s));
	#define ROUND2(a, b, c, d, k, s)  a += (b ^ c ^ d)               + schedule[k] + 0x6ED9EBA1;  a = (a << s | a >> (32 - s));
	
	uint32_t a = state[0];
	uint32_t b = state[1];
	uint32_t c = state[2];
	uint32_t d = state[3];
	
	ROUND0(a, b, c, d,  0,  3)
	ROUND0(d, a, b, c,  1,  7)
	ROUND0(c, d, a, b,  2, 11)
	ROUND0(b, c, d, a,  3, 19)
	ROUND0(a, b, c, d,  4,  3)
	ROUND0(d, a, b, c,  5,  7)
	ROUND0(c, d, a, b,  6, 11)
	ROUND0(b, c, d, a,  7, 19)
	ROUND0(a, b, c, d,  8,  3)
	ROUND0(d, a, b, c,  9,  7)
	ROUND0(c, d, a, b, 10, 11)
	ROUND0(b, c, d, a, 11, 19)
	ROUND0(a, b, c, d, 12,  3)
	ROUND0(d, a, b, c, 13,  7)
	ROUND0(c, d, a, b, 14, 11)
	ROUND0(b, c, d, a, 15, 19)
	ROUND1(a, b, c, d,  0,  3)
	ROUND1(d, a, b, c,  4,  5)
	ROUND1(c, d, a, b,  8,  9)
	ROUND1(b, c, d, a, 12, 13)
	ROUND1(a, b, c, d,  1,  3)
	ROUND1(d, a, b, c,  5,  5)
	ROUND1(c, d, a, b,  9,  9)
	ROUND1(b, c, d, a, 13, 13)
	ROUND1(a, b, c, d,  2,  3)
	ROUND1(d, a, b, c,  6,  5)
	ROUND1(c, d, a, b, 10,  9)
	ROUND1(b, c, d, a, 14, 13)
	ROUND1(a, b, c, d,  3,  3)
	ROUND1(d, a, b, c,  7,  5)
	ROUND1(c, d, a, b, 11,  9)
	ROUND1(b, c, d, a, 15, 13)
	ROUND2(a, b, c, d,  0,  3)
	ROUND2(d, a, b, c,  8,  9)
	ROUND2(c, d, a, b,  4, 11)
	ROUND2(b, c, d, a, 12, 15)
	ROUND2(a, b, c, d,  2,  3)
	ROUND2(d, a, b, c, 10,  9)
	ROUND2(c, d, a, b,  6, 11)
	ROUND2(b, c, d, a, 14, 15)
	ROUND2(a, b, c, d,  1,  3)
	ROUND2(d, a, b, c,  9,  9)
	ROUND2(c, d, a, b,  5, 11)
	ROUND2(b, c, d, a, 13, 15)
	ROUND2(a, b, c, d,  3,  3)
	ROUND2(d, a, b, c, 11,  9)
	ROUND2(c, d, a, b,  7, 11)
	ROUND2(b, c, d, a, 15, 15)
	
	state[0] += a;
	state[1] += b;
	state[2] += c;
	state[3] += d;
}
