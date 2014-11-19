/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * http://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public class Tiger2 extends Tiger {
	
	public Tiger2() {
		super();
		padding = (byte)0x80;
	}
	
}
