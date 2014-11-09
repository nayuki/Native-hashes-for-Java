/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Nayuki Minase
 * http://nayuki.eigenstate.org/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public final class Ripemd160Test extends HashTest {
	
	public static void main(String[] args) {
		new Ripemd160Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Ripemd160() : new Ripemd160Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"9C1185A5C5E9FC54612808977EE8F548B2258D31", ""},
			{"0BDC9D2D256B3EE9DAAE347BE6F4DC835A467FFE", "a"},
			{"8EB208F7E05D987A9B044A8E98C6B087F15A0BFC", "abc"},
			{"5D0689EF49D2FAE572B881B123A85FFA21595F36", "message digest"},
			{"F71C27109C692C1B56BBDCEB5B9D2865B3708DBC", "abcdefghijklmnopqrstuvwxyz"},
			{"12A053384A9C0C88E405A06C27DCF49ADA62EB2B", "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"},
			{"B0E20B6E3116640286ED3A87A5713079B21F5189", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"},
			{"9B752E45573D4B39F4DBD3323CAB82BF63326BFB", "12345678901234567890123456789012345678901234567890123456789012345678901234567890"},
		};
	}
	
}
