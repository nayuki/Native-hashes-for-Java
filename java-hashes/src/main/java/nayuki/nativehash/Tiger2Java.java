/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki. (MIT License)
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


final class Tiger2Java extends TigerJava {
	
	public Tiger2Java() {
		super();
		padding = (byte)0x80;
	}
	
}
