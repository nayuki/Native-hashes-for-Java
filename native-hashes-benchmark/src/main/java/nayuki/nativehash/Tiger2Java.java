/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nativehash;


final class Tiger2Java extends TigerJava {
	
	public Tiger2Java() {
		super();
		padding = (byte)0x80;
	}
	
}
