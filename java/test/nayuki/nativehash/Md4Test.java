/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * http://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public final class Md4Test extends HashTest {
	
	public static void main(String[] args) {
		new Md4Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Md4() : new Md4Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"31D6CFE0D16AE931B73C59D7E0C089C0", ""},
			{"BDE52CB31DE33E46245E05FBDBD6FB24", "a"},
			{"A448017AAF21D8525FC10AE87AA6729D", "abc"},
			{"D9130A8164549FE818874806E1C7014B", "message digest"},
			{"D79E1C308AA5BBCDEEA8ED63DF412DA9", "abcdefghijklmnopqrstuvwxyz"},
			{"043F8582F241DB351CE627E153E7F0E4", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"},
			{"E33B4DDC9C38F2199C3E7B164FCC0536", "12345678901234567890123456789012345678901234567890123456789012345678901234567890"},
		};
	}
	
}
