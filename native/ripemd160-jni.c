/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki. (MIT License)
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

#include <stdint.h>
#include <jni.h>


#define BLOCK_LEN 64
#define STATE_LEN 5
extern void ripemd160_compress_block(const jbyte block[static BLOCK_LEN], uint32_t state[static STATE_LEN]);


/* 
 * Class:     nayuki_nativehash_Ripemd160
 * Method:    compress
 * Signature: ([I[BII)Z
 */
JNIEXPORT jboolean JNICALL Java_nayuki_nativehash_Ripemd160_compress(JNIEnv *env, jclass thisClass, jintArray stateArray, jbyteArray msg, jint off, jint len) {
	jboolean status = JNI_FALSE;
	if (len < 0 || (len & 63) != 0)  // Block size is 64 bytes
		goto cleanup0;
	JNIEnv theEnv = *env;
	(void)thisClass;
	
	// Get state array and convert to uint32_t
	jint *stateJava = theEnv->GetIntArrayElements(env, stateArray, NULL);
	if (stateJava == NULL)
		goto cleanup0;
	uint32_t state[STATE_LEN];
	for (int i = 0; i < STATE_LEN; i++)
		state[i] = (uint32_t)stateJava[i];
	
	// Iterate over each block in msg
	jbyte *block = theEnv->GetPrimitiveArrayCritical(env, msg, NULL);
	if (block == NULL)
		goto cleanup1;
	for (jint end = off + len; off < end; off += BLOCK_LEN)
		ripemd160_compress_block(&block[off], state);
	theEnv->ReleasePrimitiveArrayCritical(env, msg, block, JNI_ABORT);
	
	// Convert state array to jint and clean up
	for (int i = 0; i < STATE_LEN; i++)
		stateJava[i] = (jint)state[i];
	status = JNI_TRUE;
cleanup1:
	theEnv->ReleaseIntArrayElements(env, stateArray, stateJava, 0);
cleanup0:
	return status;
}
