/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki. (MIT License)
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

#include <stdint.h>
#include <jni.h>


#define BLOCK_LEN 64
#define STATE_LEN 64
extern void whirlpool_compress_block(const jbyte block[BLOCK_LEN], uint8_t state[STATE_LEN]);


/* 
 * Class:     nayuki_nativehash_Whirlpool
 * Method:    compress
 * Signature: ([B[BII)Z
 */
JNIEXPORT jboolean JNICALL Java_nayuki_nativehash_Whirlpool_compress(JNIEnv *env, jclass thisClass, jbyteArray stateArray, jbyteArray msg, jint off, jint len) {
	jboolean status = JNI_FALSE;
	if (len < 0 || (len & 63) != 0)  // Block size is 64 bytes
		goto cleanup0;
	JNIEnv theEnv = *env;
	(void)thisClass;
	
	// Get state array and convert to uint8_t
	jbyte *stateJava = theEnv->GetByteArrayElements(env, stateArray, NULL);
	if (stateJava == NULL)
		goto cleanup0;
	uint8_t state[STATE_LEN];
	for (int i = 0; i < STATE_LEN; i++)
		state[i] = (uint8_t)stateJava[i];
	
	// Iterate over each block in msg
	jbyte *block = theEnv->GetPrimitiveArrayCritical(env, msg, NULL);
	if (block == NULL)
		goto cleanup1;
	for (jint end = off + len; off < end; off += BLOCK_LEN)
		whirlpool_compress_block(&block[off], state);
	theEnv->ReleasePrimitiveArrayCritical(env, msg, block, JNI_ABORT);
	
	// Convert state array to jbyte and clean up
	for (int i = 0; i < STATE_LEN; i++)
		stateJava[i] = (jbyte)state[i];
	status = JNI_TRUE;
cleanup1:
	theEnv->ReleaseByteArrayElements(env, stateArray, stateJava, 0);
cleanup0:
	return status;
}
