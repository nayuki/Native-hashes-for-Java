/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nativehash;


public final class TigerTest extends HashTest {
	
	public static void main(String[] args) {
		new TigerTest().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Tiger() : new TigerJava();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"3293AC630C13F0245F92BBB1766E16167A4E58492DDE73F3", ""},
			{"77BEFBEF2E7EF8AB2EC8F93BF587A7FC613E247F5F247809", "a"},
			{"2AAB1484E8C158F2BFB8C5FF41B57A525129131C957B5F93", "abc"},
			{"D981F8CB78201A950DCF3048751E441C517FCA1AA55A29F6", "message digest"},
			{"1714A472EEE57D30040412BFCC55032A0B11602FF37BEEE9", "abcdefghijklmnopqrstuvwxyz"},
			{"0F7BF9A19B9C58F2B7610DF7E84F0AC3A71C631E7B53F78E", "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"},
			{"8DCEA680A17583EE502BA38A3C368651890FFBCCDC49A8CC", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"},
			{"1C14795529FD9F207A958F84C52F11E887FA0CABDFD91BFD", "12345678901234567890123456789012345678901234567890123456789012345678901234567890"},
		};
	}
	
}
