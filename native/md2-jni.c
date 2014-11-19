/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * http://www.nayuki.io/page/native-hash-functions-for-java
 */

#include <stdint.h>
#include <jni.h>


#define STATE_LEN 48
#define CHECKSUM_LEN 16
extern void md2_compress_block(const jbyte *block, uint8_t state[STATE_LEN], uint8_t checksum[CHECKSUM_LEN]);


/*
 * Class:     nayuki_nativehash_Md2
 * Method:    compress
 * Signature: ([B[B[BII)Z
 */
JNIEXPORT jboolean JNICALL Java_nayuki_nativehash_Md2_compress(JNIEnv *env, jclass thisClass, jbyteArray stateArray, jbyteArray checksumArray, jbyteArray msg, jint off, jint len) {
	if (len < 0 || (len & 15) != 0)  // Block size is 16 bytes
		return 0;
	JNIEnv theEnv = *env;
	
	// Get state and checksum arrays and convert to uint8_t
	jbyte *stateJava = theEnv->GetByteArrayElements(env, stateArray, NULL);
	if (stateJava == NULL)
		return 0;
	jbyte *checksumJava = theEnv->GetByteArrayElements(env, checksumArray, NULL);
	if (checksumJava == NULL) {
		theEnv->ReleaseByteArrayElements(env, stateArray, stateJava, 0);
		return 0;
	}
	unsigned int i;
	uint8_t state[STATE_LEN];
	uint8_t checksum[CHECKSUM_LEN];
	for (i = 0; i < STATE_LEN; i++)
		state[i] = (uint8_t)stateJava[i];
	for (i = 0; i < CHECKSUM_LEN; i++)
		checksum[i] = (uint8_t)checksumJava[i];
	
	// Iterate over each block in msg
	jbyte *block = theEnv->GetPrimitiveArrayCritical(env, msg, NULL);
	if (block == NULL)
		return 0;
	size_t newoff;
	size_t newlen = len;
	for (newoff = 0; newoff < newlen; newoff += 16)
		md2_compress_block(block + off + newoff, state, checksum);
	theEnv->ReleasePrimitiveArrayCritical(env, msg, block, JNI_ABORT);
	
	// Convert state and checksum arrays to jbyte and clean up
	for (i = 0; i < STATE_LEN; i++)
		stateJava[i] = (jbyte)state[i];
	for (i = 0; i < CHECKSUM_LEN; i++)
		checksumJava[i] = (jbyte)checksum[i];
	theEnv->ReleaseByteArrayElements(env, stateArray, stateJava, 0);
	theEnv->ReleaseByteArrayElements(env, checksumArray, checksumJava, 0);
	return 1;
}
