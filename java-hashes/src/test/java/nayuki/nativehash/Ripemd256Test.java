/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public final class Ripemd256Test extends HashTest {
	
	public static void main(String[] args) {
		new Ripemd256Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Ripemd256() : new Ripemd256Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"02BA4C4E5F8ECD1877FC52D64D30E37A2D9774FB1E5D026380AE0168E3C5522D", ""},
			{"F9333E45D857F5D90A91BAB70A1EBA0CFB1BE4B0783C9ACFCD883A9134692925", "a"},
			{"AFBD6E228B9D8CBBCEF5CA2D03E6DBA10AC0BC7DCBE4680E1E42D2E975459B65", "abc"},
			{"87E971759A1CE47A514D5C914C392C9018C7C46BC14465554AFCDF54A5070C0E", "message digest"},
			{"649D3034751EA216776BF9A18ACC81BC7896118A5197968782DD1FD97D8D5133", "abcdefghijklmnopqrstuvwxyz"},
			{"3843045583AAC6C8C8D9128573E7A9809AFB2A0F34CCC36EA9E72F16F6368E3F", "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"},
			{"5740A408AC16B720B84424AE931CBB1FE363D1D0BF4017F1A89F7EA6DE77A0B8", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"},
			{"06FDCC7A409548AAF91368C06A6275B553E3F099BF0EA4EDFD6778DF89A890DD", "12345678901234567890123456789012345678901234567890123456789012345678901234567890"},
		};
	}
	
}
