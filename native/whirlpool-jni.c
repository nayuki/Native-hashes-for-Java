/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

#include <stdint.h>
#include <jni.h>


#define STATE_LEN 64
extern void whirlpool_compress_block(const jbyte *block, uint8_t state[STATE_LEN]);


/* 
 * Class:     nayuki_nativehash_Whirlpool
 * Method:    compress
 * Signature: ([B[BII)Z
 */
JNIEXPORT jboolean JNICALL Java_nayuki_nativehash_Whirlpool_compress(JNIEnv *env, jclass thisClass, jbyteArray stateArray, jbyteArray msg, jint off, jint len) {
	if (len < 0 || (len & 63) != 0)  // Block size is 64 bytes
		return JNI_FALSE;
	JNIEnv theEnv = *env;
	(void)thisClass;
	
	// Get state array and convert to uint8_t
	jbyte *stateJava = theEnv->GetByteArrayElements(env, stateArray, NULL);
	if (stateJava == NULL)
		return JNI_FALSE;
	uint8_t state[STATE_LEN];
	for (int i = 0; i < STATE_LEN; i++)
		state[i] = (uint8_t)stateJava[i];
	
	// Iterate over each block in msg
	jbyte *block = theEnv->GetPrimitiveArrayCritical(env, msg, NULL);
	if (block == NULL)
		return JNI_FALSE;
	size_t newoff;
	size_t newlen = len;
	for (newoff = 0; newoff < newlen; newoff += 64)
		whirlpool_compress_block(block + off + newoff, state);
	theEnv->ReleasePrimitiveArrayCritical(env, msg, block, JNI_ABORT);
	
	// Convert state array to jbyte and clean up
	for (int i = 0; i < STATE_LEN; i++)
		stateJava[i] = (jbyte)state[i];
	theEnv->ReleaseByteArrayElements(env, stateArray, stateJava, 0);
	return JNI_TRUE;
}
