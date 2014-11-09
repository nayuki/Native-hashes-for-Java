/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Nayuki Minase
 * http://nayuki.eigenstate.org/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public final class Sha1Test extends HashTest {
	
	public static void main(String[] args) {
		new Sha1Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Sha1() : new Sha1Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"DA39A3EE5E6B4B0D3255BFEF95601890AFD80709", ""},
			{"86F7E437FAA5A7FCE15D1DDCB9EAEAEA377667B8", "a"},
			{"A9993E364706816ABA3E25717850C26C9CD0D89D", "abc"},
			{"C12252CEDA8BE8994D5FA0290A47231C1D16AAE3", "message digest"},
			{"32D10C7B8CF96570CA04CE37F2A19D84240D3A89", "abcdefghijklmnopqrstuvwxyz"},
			{"84983E441C3BD26EBAAE4AA1F95129E5E54670F1", "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"},
		};
	}
	
}
