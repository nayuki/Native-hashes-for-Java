/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki. (MIT License)
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public final class Md5Test extends HashTest {
	
	public static void main(String[] args) {
		new Md5Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Md5() : new Md5Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"D41D8CD98F00B204E9800998ECF8427E", ""},
			{"0CC175B9C0F1B6A831C399E269772661", "a"},
			{"900150983CD24FB0D6963F7D28E17F72", "abc"},
			{"F96B697D7CB7938D525A2F31AAF161D0", "message digest"},
			{"C3FCD3D76192E4007DFB496CCA67E13B", "abcdefghijklmnopqrstuvwxyz"},
			{"D174AB98D277D9F5A5611C2C9F419D9F", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"},
			{"57EDF4A22BE3C955AC49DA2E2107B67A", "12345678901234567890123456789012345678901234567890123456789012345678901234567890"},
		};
	}
	
}
