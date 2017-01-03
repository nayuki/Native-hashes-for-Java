/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nativehash;

import static java.lang.Integer.rotateLeft;


final class Ripemd256Java extends Ripemd256 {
	
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
			
			int al = state[0], ar = state[4];
			int bl = state[1], br = state[5];
			int cl = state[2], cr = state[6];
			int dl = state[3], dr = state[7];
			
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch00, 11);                         ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch05 + 0x50A28BE6,  8);
			dl = rotateLeft(dl + (al ^ bl ^ cl) + sch01, 14);                         dr = rotateLeft(dr + ((ar & cr) | (br & ~cr)) + sch14 + 0x50A28BE6,  9);
			cl = rotateLeft(cl + (dl ^ al ^ bl) + sch02, 15);                         cr = rotateLeft(cr + ((dr & br) | (ar & ~br)) + sch07 + 0x50A28BE6,  9);
			bl = rotateLeft(bl + (cl ^ dl ^ al) + sch03, 12);                         br = rotateLeft(br + ((cr & ar) | (dr & ~ar)) + sch00 + 0x50A28BE6, 11);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch04,  5);                         ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch09 + 0x50A28BE6, 13);
			dl = rotateLeft(dl + (al ^ bl ^ cl) + sch05,  8);                         dr = rotateLeft(dr + ((ar & cr) | (br & ~cr)) + sch02 + 0x50A28BE6, 15);
			cl = rotateLeft(cl + (dl ^ al ^ bl) + sch06,  7);                         cr = rotateLeft(cr + ((dr & br) | (ar & ~br)) + sch11 + 0x50A28BE6, 15);
			bl = rotateLeft(bl + (cl ^ dl ^ al) + sch07,  9);                         br = rotateLeft(br + ((cr & ar) | (dr & ~ar)) + sch04 + 0x50A28BE6,  5);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch08, 11);                         ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch13 + 0x50A28BE6,  7);
			dl = rotateLeft(dl + (al ^ bl ^ cl) + sch09, 13);                         dr = rotateLeft(dr + ((ar & cr) | (br & ~cr)) + sch06 + 0x50A28BE6,  7);
			cl = rotateLeft(cl + (dl ^ al ^ bl) + sch10, 14);                         cr = rotateLeft(cr + ((dr & br) | (ar & ~br)) + sch15 + 0x50A28BE6,  8);
			bl = rotateLeft(bl + (cl ^ dl ^ al) + sch11, 15);                         br = rotateLeft(br + ((cr & ar) | (dr & ~ar)) + sch08 + 0x50A28BE6, 11);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch12,  6);                         ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch01 + 0x50A28BE6, 14);
			dl = rotateLeft(dl + (al ^ bl ^ cl) + sch13,  7);                         dr = rotateLeft(dr + ((ar & cr) | (br & ~cr)) + sch10 + 0x50A28BE6, 14);
			cl = rotateLeft(cl + (dl ^ al ^ bl) + sch14,  9);                         cr = rotateLeft(cr + ((dr & br) | (ar & ~br)) + sch03 + 0x50A28BE6, 12);
			bl = rotateLeft(bl + (cl ^ dl ^ al) + sch15,  8);                         br = rotateLeft(br + ((cr & ar) | (dr & ~ar)) + sch12 + 0x50A28BE6,  6);
			ar = rotateLeft(ar + ((bl & cl) | (~bl & dl)) + sch07 + 0x5A827999,  7);  al = rotateLeft(al + ((br | ~cr) ^ dr) + sch06 + 0x5C4DD124,  9);
			dl = rotateLeft(dl + ((ar & bl) | (~ar & cl)) + sch04 + 0x5A827999,  6);  dr = rotateLeft(dr + ((al | ~br) ^ cr) + sch11 + 0x5C4DD124, 13);
			cl = rotateLeft(cl + ((dl & ar) | (~dl & bl)) + sch13 + 0x5A827999,  8);  cr = rotateLeft(cr + ((dr | ~al) ^ br) + sch03 + 0x5C4DD124, 15);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & ar)) + sch01 + 0x5A827999, 13);  br = rotateLeft(br + ((cr | ~dr) ^ al) + sch07 + 0x5C4DD124,  7);
			ar = rotateLeft(ar + ((bl & cl) | (~bl & dl)) + sch10 + 0x5A827999, 11);  al = rotateLeft(al + ((br | ~cr) ^ dr) + sch00 + 0x5C4DD124, 12);
			dl = rotateLeft(dl + ((ar & bl) | (~ar & cl)) + sch06 + 0x5A827999,  9);  dr = rotateLeft(dr + ((al | ~br) ^ cr) + sch13 + 0x5C4DD124,  8);
			cl = rotateLeft(cl + ((dl & ar) | (~dl & bl)) + sch15 + 0x5A827999,  7);  cr = rotateLeft(cr + ((dr | ~al) ^ br) + sch05 + 0x5C4DD124,  9);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & ar)) + sch03 + 0x5A827999, 15);  br = rotateLeft(br + ((cr | ~dr) ^ al) + sch10 + 0x5C4DD124, 11);
			ar = rotateLeft(ar + ((bl & cl) | (~bl & dl)) + sch12 + 0x5A827999,  7);  al = rotateLeft(al + ((br | ~cr) ^ dr) + sch14 + 0x5C4DD124,  7);
			dl = rotateLeft(dl + ((ar & bl) | (~ar & cl)) + sch00 + 0x5A827999, 12);  dr = rotateLeft(dr + ((al | ~br) ^ cr) + sch15 + 0x5C4DD124,  7);
			cl = rotateLeft(cl + ((dl & ar) | (~dl & bl)) + sch09 + 0x5A827999, 15);  cr = rotateLeft(cr + ((dr | ~al) ^ br) + sch08 + 0x5C4DD124, 12);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & ar)) + sch05 + 0x5A827999,  9);  br = rotateLeft(br + ((cr | ~dr) ^ al) + sch12 + 0x5C4DD124,  7);
			ar = rotateLeft(ar + ((bl & cl) | (~bl & dl)) + sch02 + 0x5A827999, 11);  al = rotateLeft(al + ((br | ~cr) ^ dr) + sch04 + 0x5C4DD124,  6);
			dl = rotateLeft(dl + ((ar & bl) | (~ar & cl)) + sch14 + 0x5A827999,  7);  dr = rotateLeft(dr + ((al | ~br) ^ cr) + sch09 + 0x5C4DD124, 15);
			cl = rotateLeft(cl + ((dl & ar) | (~dl & bl)) + sch11 + 0x5A827999, 13);  cr = rotateLeft(cr + ((dr | ~al) ^ br) + sch01 + 0x5C4DD124, 13);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & ar)) + sch08 + 0x5A827999, 12);  br = rotateLeft(br + ((cr | ~dr) ^ al) + sch02 + 0x5C4DD124, 11);
			ar = rotateLeft(ar + ((br | ~cl) ^ dl) + sch03 + 0x6ED9EBA1, 11);         al = rotateLeft(al + ((bl & cr) | (~bl & dr)) + sch15 + 0x6D703EF3,  9);
			dl = rotateLeft(dl + ((ar | ~br) ^ cl) + sch10 + 0x6ED9EBA1, 13);         dr = rotateLeft(dr + ((al & bl) | (~al & cr)) + sch05 + 0x6D703EF3,  7);
			cl = rotateLeft(cl + ((dl | ~ar) ^ br) + sch14 + 0x6ED9EBA1,  6);         cr = rotateLeft(cr + ((dr & al) | (~dr & bl)) + sch01 + 0x6D703EF3, 15);
			br = rotateLeft(br + ((cl | ~dl) ^ ar) + sch04 + 0x6ED9EBA1,  7);         bl = rotateLeft(bl + ((cr & dr) | (~cr & al)) + sch03 + 0x6D703EF3, 11);
			ar = rotateLeft(ar + ((br | ~cl) ^ dl) + sch09 + 0x6ED9EBA1, 14);         al = rotateLeft(al + ((bl & cr) | (~bl & dr)) + sch07 + 0x6D703EF3,  8);
			dl = rotateLeft(dl + ((ar | ~br) ^ cl) + sch15 + 0x6ED9EBA1,  9);         dr = rotateLeft(dr + ((al & bl) | (~al & cr)) + sch14 + 0x6D703EF3,  6);
			cl = rotateLeft(cl + ((dl | ~ar) ^ br) + sch08 + 0x6ED9EBA1, 13);         cr = rotateLeft(cr + ((dr & al) | (~dr & bl)) + sch06 + 0x6D703EF3,  6);
			br = rotateLeft(br + ((cl | ~dl) ^ ar) + sch01 + 0x6ED9EBA1, 15);         bl = rotateLeft(bl + ((cr & dr) | (~cr & al)) + sch09 + 0x6D703EF3, 14);
			ar = rotateLeft(ar + ((br | ~cl) ^ dl) + sch02 + 0x6ED9EBA1, 14);         al = rotateLeft(al + ((bl & cr) | (~bl & dr)) + sch11 + 0x6D703EF3, 12);
			dl = rotateLeft(dl + ((ar | ~br) ^ cl) + sch07 + 0x6ED9EBA1,  8);         dr = rotateLeft(dr + ((al & bl) | (~al & cr)) + sch08 + 0x6D703EF3, 13);
			cl = rotateLeft(cl + ((dl | ~ar) ^ br) + sch00 + 0x6ED9EBA1, 13);         cr = rotateLeft(cr + ((dr & al) | (~dr & bl)) + sch12 + 0x6D703EF3,  5);
			br = rotateLeft(br + ((cl | ~dl) ^ ar) + sch06 + 0x6ED9EBA1,  6);         bl = rotateLeft(bl + ((cr & dr) | (~cr & al)) + sch02 + 0x6D703EF3, 14);
			ar = rotateLeft(ar + ((br | ~cl) ^ dl) + sch13 + 0x6ED9EBA1,  5);         al = rotateLeft(al + ((bl & cr) | (~bl & dr)) + sch10 + 0x6D703EF3, 13);
			dl = rotateLeft(dl + ((ar | ~br) ^ cl) + sch11 + 0x6ED9EBA1, 12);         dr = rotateLeft(dr + ((al & bl) | (~al & cr)) + sch00 + 0x6D703EF3, 13);
			cl = rotateLeft(cl + ((dl | ~ar) ^ br) + sch05 + 0x6ED9EBA1,  7);         cr = rotateLeft(cr + ((dr & al) | (~dr & bl)) + sch04 + 0x6D703EF3,  7);
			br = rotateLeft(br + ((cl | ~dl) ^ ar) + sch12 + 0x6ED9EBA1,  5);         bl = rotateLeft(bl + ((cr & dr) | (~cr & al)) + sch13 + 0x6D703EF3,  5);
			ar = rotateLeft(ar + ((br & dl) | (cr & ~dl)) + sch01 + 0x8F1BBCDC, 11);  al = rotateLeft(al + (bl ^ cl ^ dr) + sch08, 15);
			dl = rotateLeft(dl + ((ar & cr) | (br & ~cr)) + sch09 + 0x8F1BBCDC, 12);  dr = rotateLeft(dr + (al ^ bl ^ cl) + sch06,  5);
			cr = rotateLeft(cr + ((dl & br) | (ar & ~br)) + sch11 + 0x8F1BBCDC, 14);  cl = rotateLeft(cl + (dr ^ al ^ bl) + sch04,  8);
			br = rotateLeft(br + ((cr & ar) | (dl & ~ar)) + sch10 + 0x8F1BBCDC, 15);  bl = rotateLeft(bl + (cl ^ dr ^ al) + sch01, 11);
			ar = rotateLeft(ar + ((br & dl) | (cr & ~dl)) + sch00 + 0x8F1BBCDC, 14);  al = rotateLeft(al + (bl ^ cl ^ dr) + sch03, 14);
			dl = rotateLeft(dl + ((ar & cr) | (br & ~cr)) + sch08 + 0x8F1BBCDC, 15);  dr = rotateLeft(dr + (al ^ bl ^ cl) + sch11, 14);
			cr = rotateLeft(cr + ((dl & br) | (ar & ~br)) + sch12 + 0x8F1BBCDC,  9);  cl = rotateLeft(cl + (dr ^ al ^ bl) + sch15,  6);
			br = rotateLeft(br + ((cr & ar) | (dl & ~ar)) + sch04 + 0x8F1BBCDC,  8);  bl = rotateLeft(bl + (cl ^ dr ^ al) + sch00, 14);
			ar = rotateLeft(ar + ((br & dl) | (cr & ~dl)) + sch13 + 0x8F1BBCDC,  9);  al = rotateLeft(al + (bl ^ cl ^ dr) + sch05,  6);
			dl = rotateLeft(dl + ((ar & cr) | (br & ~cr)) + sch03 + 0x8F1BBCDC, 14);  dr = rotateLeft(dr + (al ^ bl ^ cl) + sch12,  9);
			cr = rotateLeft(cr + ((dl & br) | (ar & ~br)) + sch07 + 0x8F1BBCDC,  5);  cl = rotateLeft(cl + (dr ^ al ^ bl) + sch02, 12);
			br = rotateLeft(br + ((cr & ar) | (dl & ~ar)) + sch15 + 0x8F1BBCDC,  6);  bl = rotateLeft(bl + (cl ^ dr ^ al) + sch13,  9);
			ar = rotateLeft(ar + ((br & dl) | (cr & ~dl)) + sch14 + 0x8F1BBCDC,  8);  al = rotateLeft(al + (bl ^ cl ^ dr) + sch09, 12);
			dl = rotateLeft(dl + ((ar & cr) | (br & ~cr)) + sch05 + 0x8F1BBCDC,  6);  dr = rotateLeft(dr + (al ^ bl ^ cl) + sch07,  5);
			cr = rotateLeft(cr + ((dl & br) | (ar & ~br)) + sch06 + 0x8F1BBCDC,  5);  cl = rotateLeft(cl + (dr ^ al ^ bl) + sch10, 15);
			br = rotateLeft(br + ((cr & ar) | (dl & ~ar)) + sch02 + 0x8F1BBCDC, 12);  bl = rotateLeft(bl + (cl ^ dr ^ al) + sch14,  8);
			
			state[0] += ar;
			state[1] += br;
			state[2] += cr;
			state[3] += dr;
			state[4] += al;
			state[5] += bl;
			state[6] += cl;
			state[7] += dl;
		}
	}
	
}
