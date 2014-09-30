#include <stdint.h>
#include <jni.h>


#define STATE_LEN 4
extern void md5_compress_block(const jbyte *block, uint32_t state[STATE_LEN]);


/*
 * Class:     nayuki_nativehash_Md5
 * Method:    compress
 * Signature: ([I[BII)Z
 */
JNIEXPORT jboolean JNICALL Java_nayuki_nativehash_Md5_compress(JNIEnv *env, jclass thisClass, jintArray stateArray, jbyteArray msg, jint off, jint len) {
	JNIEnv theEnv = *env;
	
	// Get state array and convert to uint32_t
	jint *stateJava = theEnv->GetIntArrayElements(env, stateArray, NULL);
	if (stateJava == NULL)
		return 0;
	unsigned int i;
	uint32_t state[STATE_LEN];
	for (i = 0; i < STATE_LEN; i++)
		state[i] = (uint32_t)stateJava[i];
	
	// Iterate over each block in msg. Requires len to be a multiple of 64
	jbyte *block = theEnv->GetPrimitiveArrayCritical(env, msg, NULL);
	if (block == NULL)
		return 0;
	size_t newoff;
	size_t newlen = len;
	for (newoff = 0; newoff < newlen; newoff += 64)
		md5_compress_block(block + off + newoff, state);
	theEnv->ReleasePrimitiveArrayCritical(env, msg, block, JNI_ABORT);
	
	// Convert state array to jint and clean up
	for (i = 0; i < STATE_LEN; i++)
		stateJava[i] = (jint)state[i];
	theEnv->ReleaseIntArrayElements(env, stateArray, stateJava, 0);
	return 1;
}
