/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * http://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import java.util.Arrays;


final class Sha384Java extends Sha512Java {
	
	public Sha384Java() {
		super();
		state = new long[] {
			0xCBBB9D5DC1059ED8L, 0x629A292A367CD507L, 0x9159015A3070DD17L, 0x152FECD8F70E5939L,
			0x67332667FFC00B31L, 0x8EB44A8768581511L, 0xDB0C2E0D64F98FA7L, 0x47B5481DBEFA4FA4L,
		};
	}
	
	
	
	protected byte[] getHashDestructively() {
		return Arrays.copyOf(super.getHashDestructively(), 48);
	}
	
}
