#include <stdint.h>
#include <jni.h>


extern void sha512_compress_block(const jbyte *block, uint64_t state[8]);


/*
 * Class:     nayuki_nativehash_Sha512
 * Method:    compress
 * Signature: ([J[BII)Z
 */
JNIEXPORT jboolean JNICALL Java_nayuki_nativehash_Sha512_compress(JNIEnv *env, jclass thisClass, jlongArray stateArray, jbyteArray msg, jint off, jint len) {
	JNIEnv theEnv = *env;
	
	// Get state array and convert to uint64_t
	jlong *stateJava = theEnv->GetLongArrayElements(env, stateArray, NULL);
	if (stateJava == NULL)
		return 0;
	unsigned int i;
	uint64_t state[8];
	for (i = 0; i < 8; i++)
		state[i] = (uint64_t)stateJava[i];
	
	// Iterate over each block in msg. Requires len to be a multiple of 128
	jbyte *block = theEnv->GetPrimitiveArrayCritical(env, msg, NULL);
	if (block == NULL)
		return 0;
	size_t newoff;
	size_t newlen = len;
	for (newoff = 0; newoff < newlen; newoff += 128)
		sha512_compress_block(block + off + newoff, state);
	theEnv->ReleasePrimitiveArrayCritical(env, msg, block, JNI_ABORT);
	
	// Convert state array to jlong and clean up
	for (i = 0; i < 8; i++)
		stateJava[i] = (jlong)state[i];
	theEnv->ReleaseLongArrayElements(env, stateArray, stateJava, 0);
	return 1;
}
