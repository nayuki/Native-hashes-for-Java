/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import java.util.Arrays;


final class Sha224Java extends Sha256Java {
	
	public Sha224Java() {
		super();
		state = new int[]{0xC1059ED8, 0x367CD507, 0x3070DD17, 0xF70E5939, 0xFFC00B31, 0x68581511, 0x64F98FA7, 0xBEFA4FA4};
	}
	
	
	
	protected byte[] getHashDestructively() {
		return Arrays.copyOf(super.getHashDestructively(), 28);
	}
	
}
