package nayuki.nativehash;


public final class Tiger2Test extends HashTest {
	
	public static void main(String[] args) {
		new Tiger2Test().run();
	}
	
	
	protected BlockHasher newHasher(boolean useNative) {
		return useNative ? new Tiger2() : new Tiger2Java();
	}
	
	
	protected String[][] getTestVectors() {
		return new String[][] {
			{"4441BE75F6018773C206C22745374B924AA8313FEF919F41", ""},
			{"67E6AE8E9E968999F70A23E72AEAA9251CBC7C78A7916636", "a"},
			{"F68D7BC5AF4B43A06E048D7829560D4A9415658BB0B1F3BF", "abc"},
			{"E29419A1B5FA259DE8005E7DE75078EA81A542EF2552462D", "message digest"},
			{"F5B6B6A78C405C8547E91CD8624CB8BE83FC804A474488FD", "abcdefghijklmnopqrstuvwxyz"},
			{"A6737F3997E8FBB63D20D2DF88F86376B5FE2D5CE36646A9", "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"},
			{"EA9AB6228CEE7B51B77544FCA6066C8CBB5BBAE6319505CD", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"},
			{"D85278115329EBAA0EEC85ECDC5396FDA8AA3A5820942FFF", "12345678901234567890123456789012345678901234567890123456789012345678901234567890"},
		};
	}
	
}
