/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Nayuki Minase
 * http://nayuki.eigenstate.org/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import static java.lang.Integer.rotateLeft;


final class Ripemd128Java extends Ripemd128 {
	
	protected void compress(byte[] msg, int off, int len) {
		for (int i = off, end = off + len; i < end; i += 64) {
			int sch00 = (msg[i +  0] & 0xFF) | (msg[i +  1] & 0xFF) << 8 | (msg[i +  2] & 0xFF) << 16 | msg[i +  3] << 24;
			int sch01 = (msg[i +  4] & 0xFF) | (msg[i +  5] & 0xFF) << 8 | (msg[i +  6] & 0xFF) << 16 | msg[i +  7] << 24;
			int sch02 = (msg[i +  8] & 0xFF) | (msg[i +  9] & 0xFF) << 8 | (msg[i + 10] & 0xFF) << 16 | msg[i + 11] << 24;
			int sch03 = (msg[i + 12] & 0xFF) | (msg[i + 13] & 0xFF) << 8 | (msg[i + 14] & 0xFF) << 16 | msg[i + 15] << 24;
			int sch04 = (msg[i + 16] & 0xFF) | (msg[i + 17] & 0xFF) << 8 | (msg[i + 18] & 0xFF) << 16 | msg[i + 19] << 24;
			int sch05 = (msg[i + 20] & 0xFF) | (msg[i + 21] & 0xFF) << 8 | (msg[i + 22] & 0xFF) << 16 | msg[i + 23] << 24;
			int sch06 = (msg[i + 24] & 0xFF) | (msg[i + 25] & 0xFF) << 8 | (msg[i + 26] & 0xFF) << 16 | msg[i + 27] << 24;
			int sch07 = (msg[i + 28] & 0xFF) | (msg[i + 29] & 0xFF) << 8 | (msg[i + 30] & 0xFF) << 16 | msg[i + 31] << 24;
			int sch08 = (msg[i + 32] & 0xFF) | (msg[i + 33] & 0xFF) << 8 | (msg[i + 34] & 0xFF) << 16 | msg[i + 35] << 24;
			int sch09 = (msg[i + 36] & 0xFF) | (msg[i + 37] & 0xFF) << 8 | (msg[i + 38] & 0xFF) << 16 | msg[i + 39] << 24;
			int sch10 = (msg[i + 40] & 0xFF) | (msg[i + 41] & 0xFF) << 8 | (msg[i + 42] & 0xFF) << 16 | msg[i + 43] << 24;
			int sch11 = (msg[i + 44] & 0xFF) | (msg[i + 45] & 0xFF) << 8 | (msg[i + 46] & 0xFF) << 16 | msg[i + 47] << 24;
			int sch12 = (msg[i + 48] & 0xFF) | (msg[i + 49] & 0xFF) << 8 | (msg[i + 50] & 0xFF) << 16 | msg[i + 51] << 24;
			int sch13 = (msg[i + 52] & 0xFF) | (msg[i + 53] & 0xFF) << 8 | (msg[i + 54] & 0xFF) << 16 | msg[i + 55] << 24;
			int sch14 = (msg[i + 56] & 0xFF) | (msg[i + 57] & 0xFF) << 8 | (msg[i + 58] & 0xFF) << 16 | msg[i + 59] << 24;
			int sch15 = (msg[i + 60] & 0xFF) | (msg[i + 61] & 0xFF) << 8 | (msg[i + 62] & 0xFF) << 16 | msg[i + 63] << 24;
			
			int al = state[0], ar = state[0];
			int bl = state[1], br = state[1];
			int cl = state[2], cr = state[2];
			int dl = state[3], dr = state[3];
			
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch00, 11);
			dl = rotateLeft(dl + (al ^ bl ^ cl) + sch01, 14);
			cl = rotateLeft(cl + (dl ^ al ^ bl) + sch02, 15);
			bl = rotateLeft(bl + (cl ^ dl ^ al) + sch03, 12);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch04,  5);
			dl = rotateLeft(dl + (al ^ bl ^ cl) + sch05,  8);
			cl = rotateLeft(cl + (dl ^ al ^ bl) + sch06,  7);
			bl = rotateLeft(bl + (cl ^ dl ^ al) + sch07,  9);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch08, 11);
			dl = rotateLeft(dl + (al ^ bl ^ cl) + sch09, 13);
			cl = rotateLeft(cl + (dl ^ al ^ bl) + sch10, 14);
			bl = rotateLeft(bl + (cl ^ dl ^ al) + sch11, 15);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch12,  6);
			dl = rotateLeft(dl + (al ^ bl ^ cl) + sch13,  7);
			cl = rotateLeft(cl + (dl ^ al ^ bl) + sch14,  9);
			bl = rotateLeft(bl + (cl ^ dl ^ al) + sch15,  8);
			al = rotateLeft(al + ((bl & cl) | (~bl & dl)) + sch07 + 0x5A827999,  7);
			dl = rotateLeft(dl + ((al & bl) | (~al & cl)) + sch04 + 0x5A827999,  6);
			cl = rotateLeft(cl + ((dl & al) | (~dl & bl)) + sch13 + 0x5A827999,  8);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & al)) + sch01 + 0x5A827999, 13);
			al = rotateLeft(al + ((bl & cl) | (~bl & dl)) + sch10 + 0x5A827999, 11);
			dl = rotateLeft(dl + ((al & bl) | (~al & cl)) + sch06 + 0x5A827999,  9);
			cl = rotateLeft(cl + ((dl & al) | (~dl & bl)) + sch15 + 0x5A827999,  7);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & al)) + sch03 + 0x5A827999, 15);
			al = rotateLeft(al + ((bl & cl) | (~bl & dl)) + sch12 + 0x5A827999,  7);
			dl = rotateLeft(dl + ((al & bl) | (~al & cl)) + sch00 + 0x5A827999, 12);
			cl = rotateLeft(cl + ((dl & al) | (~dl & bl)) + sch09 + 0x5A827999, 15);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & al)) + sch05 + 0x5A827999,  9);
			al = rotateLeft(al + ((bl & cl) | (~bl & dl)) + sch02 + 0x5A827999, 11);
			dl = rotateLeft(dl + ((al & bl) | (~al & cl)) + sch14 + 0x5A827999,  7);
			cl = rotateLeft(cl + ((dl & al) | (~dl & bl)) + sch11 + 0x5A827999, 13);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & al)) + sch08 + 0x5A827999, 12);
			al = rotateLeft(al + ((bl | ~cl) ^ dl) + sch03 + 0x6ED9EBA1, 11);
			dl = rotateLeft(dl + ((al | ~bl) ^ cl) + sch10 + 0x6ED9EBA1, 13);
			cl = rotateLeft(cl + ((dl | ~al) ^ bl) + sch14 + 0x6ED9EBA1,  6);
			bl = rotateLeft(bl + ((cl | ~dl) ^ al) + sch04 + 0x6ED9EBA1,  7);
			al = rotateLeft(al + ((bl | ~cl) ^ dl) + sch09 + 0x6ED9EBA1, 14);
			dl = rotateLeft(dl + ((al | ~bl) ^ cl) + sch15 + 0x6ED9EBA1,  9);
			cl = rotateLeft(cl + ((dl | ~al) ^ bl) + sch08 + 0x6ED9EBA1, 13);
			bl = rotateLeft(bl + ((cl | ~dl) ^ al) + sch01 + 0x6ED9EBA1, 15);
			al = rotateLeft(al + ((bl | ~cl) ^ dl) + sch02 + 0x6ED9EBA1, 14);
			dl = rotateLeft(dl + ((al | ~bl) ^ cl) + sch07 + 0x6ED9EBA1,  8);
			cl = rotateLeft(cl + ((dl | ~al) ^ bl) + sch00 + 0x6ED9EBA1, 13);
			bl = rotateLeft(bl + ((cl | ~dl) ^ al) + sch06 + 0x6ED9EBA1,  6);
			al = rotateLeft(al + ((bl | ~cl) ^ dl) + sch13 + 0x6ED9EBA1,  5);
			dl = rotateLeft(dl + ((al | ~bl) ^ cl) + sch11 + 0x6ED9EBA1, 12);
			cl = rotateLeft(cl + ((dl | ~al) ^ bl) + sch05 + 0x6ED9EBA1,  7);
			bl = rotateLeft(bl + ((cl | ~dl) ^ al) + sch12 + 0x6ED9EBA1,  5);
			al = rotateLeft(al + ((bl & dl) | (cl & ~dl)) + sch01 + 0x8F1BBCDC, 11);
			dl = rotateLeft(dl + ((al & cl) | (bl & ~cl)) + sch09 + 0x8F1BBCDC, 12);
			cl = rotateLeft(cl + ((dl & bl) | (al & ~bl)) + sch11 + 0x8F1BBCDC, 14);
			bl = rotateLeft(bl + ((cl & al) | (dl & ~al)) + sch10 + 0x8F1BBCDC, 15);
			al = rotateLeft(al + ((bl & dl) | (cl & ~dl)) + sch00 + 0x8F1BBCDC, 14);
			dl = rotateLeft(dl + ((al & cl) | (bl & ~cl)) + sch08 + 0x8F1BBCDC, 15);
			cl = rotateLeft(cl + ((dl & bl) | (al & ~bl)) + sch12 + 0x8F1BBCDC,  9);
			bl = rotateLeft(bl + ((cl & al) | (dl & ~al)) + sch04 + 0x8F1BBCDC,  8);
			al = rotateLeft(al + ((bl & dl) | (cl & ~dl)) + sch13 + 0x8F1BBCDC,  9);
			dl = rotateLeft(dl + ((al & cl) | (bl & ~cl)) + sch03 + 0x8F1BBCDC, 14);
			cl = rotateLeft(cl + ((dl & bl) | (al & ~bl)) + sch07 + 0x8F1BBCDC,  5);
			bl = rotateLeft(bl + ((cl & al) | (dl & ~al)) + sch15 + 0x8F1BBCDC,  6);
			al = rotateLeft(al + ((bl & dl) | (cl & ~dl)) + sch14 + 0x8F1BBCDC,  8);
			dl = rotateLeft(dl + ((al & cl) | (bl & ~cl)) + sch05 + 0x8F1BBCDC,  6);
			cl = rotateLeft(cl + ((dl & bl) | (al & ~bl)) + sch06 + 0x8F1BBCDC,  5);
			bl = rotateLeft(bl + ((cl & al) | (dl & ~al)) + sch02 + 0x8F1BBCDC, 12);
			
			ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch05 + 0x50A28BE6,  8);
			dr = rotateLeft(dr + ((ar & cr) | (br & ~cr)) + sch14 + 0x50A28BE6,  9);
			cr = rotateLeft(cr + ((dr & br) | (ar & ~br)) + sch07 + 0x50A28BE6,  9);
			br = rotateLeft(br + ((cr & ar) | (dr & ~ar)) + sch00 + 0x50A28BE6, 11);
			ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch09 + 0x50A28BE6, 13);
			dr = rotateLeft(dr + ((ar & cr) | (br & ~cr)) + sch02 + 0x50A28BE6, 15);
			cr = rotateLeft(cr + ((dr & br) | (ar & ~br)) + sch11 + 0x50A28BE6, 15);
			br = rotateLeft(br + ((cr & ar) | (dr & ~ar)) + sch04 + 0x50A28BE6,  5);
			ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch13 + 0x50A28BE6,  7);
			dr = rotateLeft(dr + ((ar & cr) | (br & ~cr)) + sch06 + 0x50A28BE6,  7);
			cr = rotateLeft(cr + ((dr & br) | (ar & ~br)) + sch15 + 0x50A28BE6,  8);
			br = rotateLeft(br + ((cr & ar) | (dr & ~ar)) + sch08 + 0x50A28BE6, 11);
			ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch01 + 0x50A28BE6, 14);
			dr = rotateLeft(dr + ((ar & cr) | (br & ~cr)) + sch10 + 0x50A28BE6, 14);
			cr = rotateLeft(cr + ((dr & br) | (ar & ~br)) + sch03 + 0x50A28BE6, 12);
			br = rotateLeft(br + ((cr & ar) | (dr & ~ar)) + sch12 + 0x50A28BE6,  6);
			ar = rotateLeft(ar + ((br | ~cr) ^ dr) + sch06 + 0x5C4DD124,  9);
			dr = rotateLeft(dr + ((ar | ~br) ^ cr) + sch11 + 0x5C4DD124, 13);
			cr = rotateLeft(cr + ((dr | ~ar) ^ br) + sch03 + 0x5C4DD124, 15);
			br = rotateLeft(br + ((cr | ~dr) ^ ar) + sch07 + 0x5C4DD124,  7);
			ar = rotateLeft(ar + ((br | ~cr) ^ dr) + sch00 + 0x5C4DD124, 12);
			dr = rotateLeft(dr + ((ar | ~br) ^ cr) + sch13 + 0x5C4DD124,  8);
			cr = rotateLeft(cr + ((dr | ~ar) ^ br) + sch05 + 0x5C4DD124,  9);
			br = rotateLeft(br + ((cr | ~dr) ^ ar) + sch10 + 0x5C4DD124, 11);
			ar = rotateLeft(ar + ((br | ~cr) ^ dr) + sch14 + 0x5C4DD124,  7);
			dr = rotateLeft(dr + ((ar | ~br) ^ cr) + sch15 + 0x5C4DD124,  7);
			cr = rotateLeft(cr + ((dr | ~ar) ^ br) + sch08 + 0x5C4DD124, 12);
			br = rotateLeft(br + ((cr | ~dr) ^ ar) + sch12 + 0x5C4DD124,  7);
			ar = rotateLeft(ar + ((br | ~cr) ^ dr) + sch04 + 0x5C4DD124,  6);
			dr = rotateLeft(dr + ((ar | ~br) ^ cr) + sch09 + 0x5C4DD124, 15);
			cr = rotateLeft(cr + ((dr | ~ar) ^ br) + sch01 + 0x5C4DD124, 13);
			br = rotateLeft(br + ((cr | ~dr) ^ ar) + sch02 + 0x5C4DD124, 11);
			ar = rotateLeft(ar + ((br & cr) | (~br & dr)) + sch15 + 0x6D703EF3,  9);
			dr = rotateLeft(dr + ((ar & br) | (~ar & cr)) + sch05 + 0x6D703EF3,  7);
			cr = rotateLeft(cr + ((dr & ar) | (~dr & br)) + sch01 + 0x6D703EF3, 15);
			br = rotateLeft(br + ((cr & dr) | (~cr & ar)) + sch03 + 0x6D703EF3, 11);
			ar = rotateLeft(ar + ((br & cr) | (~br & dr)) + sch07 + 0x6D703EF3,  8);
			dr = rotateLeft(dr + ((ar & br) | (~ar & cr)) + sch14 + 0x6D703EF3,  6);
			cr = rotateLeft(cr + ((dr & ar) | (~dr & br)) + sch06 + 0x6D703EF3,  6);
			br = rotateLeft(br + ((cr & dr) | (~cr & ar)) + sch09 + 0x6D703EF3, 14);
			ar = rotateLeft(ar + ((br & cr) | (~br & dr)) + sch11 + 0x6D703EF3, 12);
			dr = rotateLeft(dr + ((ar & br) | (~ar & cr)) + sch08 + 0x6D703EF3, 13);
			cr = rotateLeft(cr + ((dr & ar) | (~dr & br)) + sch12 + 0x6D703EF3,  5);
			br = rotateLeft(br + ((cr & dr) | (~cr & ar)) + sch02 + 0x6D703EF3, 14);
			ar = rotateLeft(ar + ((br & cr) | (~br & dr)) + sch10 + 0x6D703EF3, 13);
			dr = rotateLeft(dr + ((ar & br) | (~ar & cr)) + sch00 + 0x6D703EF3, 13);
			cr = rotateLeft(cr + ((dr & ar) | (~dr & br)) + sch04 + 0x6D703EF3,  7);
			br = rotateLeft(br + ((cr & dr) | (~cr & ar)) + sch13 + 0x6D703EF3,  5);
			ar = rotateLeft(ar + (br ^ cr ^ dr) + sch08, 15);
			dr = rotateLeft(dr + (ar ^ br ^ cr) + sch06,  5);
			cr = rotateLeft(cr + (dr ^ ar ^ br) + sch04,  8);
			br = rotateLeft(br + (cr ^ dr ^ ar) + sch01, 11);
			ar = rotateLeft(ar + (br ^ cr ^ dr) + sch03, 14);
			dr = rotateLeft(dr + (ar ^ br ^ cr) + sch11, 14);
			cr = rotateLeft(cr + (dr ^ ar ^ br) + sch15,  6);
			br = rotateLeft(br + (cr ^ dr ^ ar) + sch00, 14);
			ar = rotateLeft(ar + (br ^ cr ^ dr) + sch05,  6);
			dr = rotateLeft(dr + (ar ^ br ^ cr) + sch12,  9);
			cr = rotateLeft(cr + (dr ^ ar ^ br) + sch02, 12);
			br = rotateLeft(br + (cr ^ dr ^ ar) + sch13,  9);
			ar = rotateLeft(ar + (br ^ cr ^ dr) + sch09, 12);
			dr = rotateLeft(dr + (ar ^ br ^ cr) + sch07,  5);
			cr = rotateLeft(cr + (dr ^ ar ^ br) + sch10, 15);
			br = rotateLeft(br + (cr ^ dr ^ ar) + sch14,  8);
			
			int temp = state[1] + cl + dr;
			state[1] = state[2] + dl + ar;
			state[2] = state[3] + al + br;
			state[3] = state[0] + bl + cr;
			state[0] = temp;
		}
	}
	
}
