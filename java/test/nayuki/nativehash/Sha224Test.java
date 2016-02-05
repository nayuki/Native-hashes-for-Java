/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public final class Sha224Test extends HashTest {
	
	public static void main(String[] args) {
		new Sha224Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Sha224() : new Sha224Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"D14A028C2A3A2BC9476102BB288234C415A2B01F828EA62AC5B3E42F", ""},
			{"ABD37534C7D9A2EFB9465DE931CD7055FFDB8879563AE98078D6D6D5", "a"},
			{"23097D223405D8228642A477BDA255B32AADBCE4BDA0B3F7E36C9DA7", "abc"},
			{"2CB21C83AE2F004DE7E81C3C7019CBCB65B71AB656B22D6D0C39B8EB", "message digest"},
			{"45A5F72C39C5CFF2522EB3429799E49E5F44B356EF926BCF390DCCC2", "abcdefghijklmnopqrstuvwxyz"},
			{"75388B16512776CC5DBA5DA1FD890150B0C6455CB4F58B1952522525", "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"},
		};
	}
	
}
