/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public final class Md2Test extends HashTest {
	
	public static void main(String[] args) {
		new Md2Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Md2() : new Md2Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"8350E5A3E24C153DF2275C9F80692773", ""},
			{"32EC01EC4A6DAC72C0AB96FB34C0B5D1", "a"},
			{"DA853B0D3F88D99B30283A69E6DED6BB", "abc"},
			{"AB4F496BFB2A530B219FF33031FE06B0", "message digest"},
			{"4E8DDFF3650292AB5A4108C3AA47940B", "abcdefghijklmnopqrstuvwxyz"},
			{"DA33DEF2A42DF13975352846C30338CD", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"},
			{"D5976F79D83D3A0DC9806C3C66F3EFD8", "12345678901234567890123456789012345678901234567890123456789012345678901234567890"},
		};
	}
	
}
