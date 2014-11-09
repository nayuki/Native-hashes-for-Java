/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Nayuki Minase
 * http://nayuki.eigenstate.org/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public final class Sha256Test extends HashTest {
	
	public static void main(String[] args) {
		new Sha256Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Sha256() : new Sha256Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"E3B0C44298FC1C149AFBF4C8996FB92427AE41E4649B934CA495991B7852B855", ""},
			{"CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB", "a"},
			{"BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD", "abc"},
			{"F7846F55CF23E14EEBEAB5B4E1550CAD5B509E3348FBC4EFA3A1413D393CB650", "message digest"},
			{"71C480DF93D6AE2F1EFAD1447C66C9525E316218CF51FC8D9ED832F2DAF18B73", "abcdefghijklmnopqrstuvwxyz"},
			{"248D6A61D20638B8E5C026930C3E6039A33CE45964FF2167F6ECEDD419DB06C1", "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"},
		};
	}
	
}
