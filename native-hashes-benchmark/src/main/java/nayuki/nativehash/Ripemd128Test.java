/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nativehash;


public final class Ripemd128Test extends HashTest {
	
	public static void main(String[] args) {
		new Ripemd128Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Ripemd128() : new Ripemd128Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"CDF26213A150DC3ECB610F18F6B38B46", ""},
			{"86BE7AFA339D0FC7CFC785E72F578D33", "a"},
			{"C14A12199C66E4BA84636B0F69144C77", "abc"},
			{"9E327B3D6E523062AFC1132D7DF9D1B8", "message digest"},
			{"FD2AA607F71DC8F510714922B371834E", "abcdefghijklmnopqrstuvwxyz"},
			{"A1AA0689D0FAFA2DDC22E88B49133A06", "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"},
			{"D1E959EB179C911FAEA4624C60C5C702", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"},
			{"3F45EF194732C2DBB2C4A2C769795FA3", "12345678901234567890123456789012345678901234567890123456789012345678901234567890"},
		};
	}
	
}
