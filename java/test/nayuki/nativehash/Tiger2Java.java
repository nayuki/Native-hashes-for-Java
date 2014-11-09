/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Nayuki Minase
 * http://nayuki.eigenstate.org/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


final class Tiger2Java extends TigerJava {
	
	public Tiger2Java() {
		super();
		padding = (byte)0x80;
	}
	
}
