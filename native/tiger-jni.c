/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki. (MIT License)
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

#include <stdint.h>
#include <jni.h>


#define STATE_LEN 3
extern void tiger_compress_block(const jbyte *block, uint64_t state[STATE_LEN]);


/* 
 * Class:     nayuki_nativehash_Tiger
 * Method:    compress
 * Signature: ([J[BII)Z
 */
JNIEXPORT jboolean JNICALL Java_nayuki_nativehash_Tiger_compress(JNIEnv *env, jclass thisClass, jlongArray stateArray, jbyteArray msg, jint off, jint len) {
	jboolean status = JNI_FALSE;
	if (len < 0 || (len & 63) != 0)  // Block size is 64 bytes
		goto cleanup0;
	JNIEnv theEnv = *env;
	(void)thisClass;
	
	// Get state array and convert to uint64_t
	jlong *stateJava = theEnv->GetLongArrayElements(env, stateArray, NULL);
	if (stateJava == NULL)
		goto cleanup0;
	uint64_t state[STATE_LEN];
	for (int i = 0; i < STATE_LEN; i++)
		state[i] = (uint64_t)stateJava[i];
	
	// Iterate over each block in msg
	jbyte *block = theEnv->GetPrimitiveArrayCritical(env, msg, NULL);
	if (block == NULL)
		goto cleanup1;
	for (jint end = off + len; off < end; off += 64)
		tiger_compress_block(&block[off], state);
	theEnv->ReleasePrimitiveArrayCritical(env, msg, block, JNI_ABORT);
	
	// Convert state array to jlong and clean up
	for (int i = 0; i < STATE_LEN; i++)
		stateJava[i] = (jlong)state[i];
	status = JNI_TRUE;
cleanup1:
	theEnv->ReleaseLongArrayElements(env, stateArray, stateJava, 0);
cleanup0:
	return status;
}
