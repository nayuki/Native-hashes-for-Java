/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;


public final class WhirlpoolTest extends HashTest {
	
	public static void main(String[] args) {
		new WhirlpoolTest().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Whirlpool() : new WhirlpoolJava();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"19FA61D75522A4669B44E39C1D2E1726C530232130D407F89AFEE0964997F7A73E83BE698B288FEBCF88E3E03C4F0757EA8964E59B63D93708B138CC42A66EB3", ""},
			{"B97DE512E91E3828B40D2B0FDCE9CEB3C4A71F9BEA8D88E75C4FA854DF36725FD2B52EB6544EDCACD6F8BEDDFEA403CB55AE31F03AD62A5EF54E42EE82C3FB35", "The quick brown fox jumps over the lazy dog"},
			{"C27BA124205F72E6847F3E19834F925CC666D0974167AF915BB462420ED40CC50900D85A1F923219D832357750492D5C143011A76988344C2635E69D06F2D38C", "The quick brown fox jumps over the lazy eog"},
		};
	}
	
}
