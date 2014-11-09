/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Nayuki Minase
 * http://nayuki.eigenstate.org/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import static java.lang.Integer.rotateLeft;


final class Ripemd160Java extends Ripemd160 {
	
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
			int el = state[4], er = state[4];
			
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch00, 11) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + (al ^ bl ^ cl) + sch01, 14) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + (el ^ al ^ bl) + sch02, 15) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + (dl ^ el ^ al) + sch03, 12) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + (cl ^ dl ^ el) + sch04,  5) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch05,  8) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + (al ^ bl ^ cl) + sch06,  7) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + (el ^ al ^ bl) + sch07,  9) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + (dl ^ el ^ al) + sch08, 11) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + (cl ^ dl ^ el) + sch09, 13) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch10, 14) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + (al ^ bl ^ cl) + sch11, 15) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + (el ^ al ^ bl) + sch12,  6) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + (dl ^ el ^ al) + sch13,  7) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + (cl ^ dl ^ el) + sch14,  9) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch15,  8) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al & bl) | (~al & cl)) + sch07 + 0x5A827999,  7) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el & al) | (~el & bl)) + sch04 + 0x5A827999,  6) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & el) | (~dl & al)) + sch13 + 0x5A827999,  8) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & el)) + sch01 + 0x5A827999, 13) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + ((bl & cl) | (~bl & dl)) + sch10 + 0x5A827999, 11) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al & bl) | (~al & cl)) + sch06 + 0x5A827999,  9) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el & al) | (~el & bl)) + sch15 + 0x5A827999,  7) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & el) | (~dl & al)) + sch03 + 0x5A827999, 15) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & el)) + sch12 + 0x5A827999,  7) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + ((bl & cl) | (~bl & dl)) + sch00 + 0x5A827999, 12) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al & bl) | (~al & cl)) + sch09 + 0x5A827999, 15) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el & al) | (~el & bl)) + sch05 + 0x5A827999,  9) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & el) | (~dl & al)) + sch02 + 0x5A827999, 11) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & el)) + sch14 + 0x5A827999,  7) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + ((bl & cl) | (~bl & dl)) + sch11 + 0x5A827999, 13) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al & bl) | (~al & cl)) + sch08 + 0x5A827999, 12) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el | ~al) ^ bl) + sch03 + 0x6ED9EBA1, 11) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl | ~el) ^ al) + sch10 + 0x6ED9EBA1, 13) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + ((cl | ~dl) ^ el) + sch14 + 0x6ED9EBA1,  6) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + ((bl | ~cl) ^ dl) + sch04 + 0x6ED9EBA1,  7) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al | ~bl) ^ cl) + sch09 + 0x6ED9EBA1, 14) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el | ~al) ^ bl) + sch15 + 0x6ED9EBA1,  9) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl | ~el) ^ al) + sch08 + 0x6ED9EBA1, 13) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + ((cl | ~dl) ^ el) + sch01 + 0x6ED9EBA1, 15) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + ((bl | ~cl) ^ dl) + sch02 + 0x6ED9EBA1, 14) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al | ~bl) ^ cl) + sch07 + 0x6ED9EBA1,  8) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el | ~al) ^ bl) + sch00 + 0x6ED9EBA1, 13) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl | ~el) ^ al) + sch06 + 0x6ED9EBA1,  6) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + ((cl | ~dl) ^ el) + sch13 + 0x6ED9EBA1,  5) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + ((bl | ~cl) ^ dl) + sch11 + 0x6ED9EBA1, 12) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al | ~bl) ^ cl) + sch05 + 0x6ED9EBA1,  7) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el | ~al) ^ bl) + sch12 + 0x6ED9EBA1,  5) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & al) | (el & ~al)) + sch01 + 0x8F1BBCDC, 11) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + ((cl & el) | (dl & ~el)) + sch09 + 0x8F1BBCDC, 12) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + ((bl & dl) | (cl & ~dl)) + sch11 + 0x8F1BBCDC, 14) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al & cl) | (bl & ~cl)) + sch10 + 0x8F1BBCDC, 15) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el & bl) | (al & ~bl)) + sch00 + 0x8F1BBCDC, 14) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & al) | (el & ~al)) + sch08 + 0x8F1BBCDC, 15) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + ((cl & el) | (dl & ~el)) + sch12 + 0x8F1BBCDC,  9) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + ((bl & dl) | (cl & ~dl)) + sch04 + 0x8F1BBCDC,  8) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al & cl) | (bl & ~cl)) + sch13 + 0x8F1BBCDC,  9) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el & bl) | (al & ~bl)) + sch03 + 0x8F1BBCDC, 14) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & al) | (el & ~al)) + sch07 + 0x8F1BBCDC,  5) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + ((cl & el) | (dl & ~el)) + sch15 + 0x8F1BBCDC,  6) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + ((bl & dl) | (cl & ~dl)) + sch14 + 0x8F1BBCDC,  8) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((al & cl) | (bl & ~cl)) + sch05 + 0x8F1BBCDC,  6) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el & bl) | (al & ~bl)) + sch06 + 0x8F1BBCDC,  5) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & al) | (el & ~al)) + sch02 + 0x8F1BBCDC, 12) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + (cl ^ (dl | ~el)) + sch04 + 0xA953FD4E,  9) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + (bl ^ (cl | ~dl)) + sch00 + 0xA953FD4E, 15) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + (al ^ (bl | ~cl)) + sch05 + 0xA953FD4E,  5) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + (el ^ (al | ~bl)) + sch09 + 0xA953FD4E, 11) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + (dl ^ (el | ~al)) + sch07 + 0xA953FD4E,  6) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + (cl ^ (dl | ~el)) + sch12 + 0xA953FD4E,  8) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + (bl ^ (cl | ~dl)) + sch02 + 0xA953FD4E, 13) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + (al ^ (bl | ~cl)) + sch10 + 0xA953FD4E, 12) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + (el ^ (al | ~bl)) + sch14 + 0xA953FD4E,  5) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + (dl ^ (el | ~al)) + sch01 + 0xA953FD4E, 12) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + (cl ^ (dl | ~el)) + sch03 + 0xA953FD4E, 13) + al; dl = rotateLeft(dl, 10);
			al = rotateLeft(al + (bl ^ (cl | ~dl)) + sch08 + 0xA953FD4E, 14) + el; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + (al ^ (bl | ~cl)) + sch11 + 0xA953FD4E, 11) + dl; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + (el ^ (al | ~bl)) + sch06 + 0xA953FD4E,  8) + cl; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + (dl ^ (el | ~al)) + sch15 + 0xA953FD4E,  5) + bl; el = rotateLeft(el, 10);
			bl = rotateLeft(bl + (cl ^ (dl | ~el)) + sch13 + 0xA953FD4E,  6) + al; dl = rotateLeft(dl, 10);
			
			ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch05 + 0x50A28BE6,  8) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + (ar ^ (br | ~cr)) + sch14 + 0x50A28BE6,  9) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + (er ^ (ar | ~br)) + sch07 + 0x50A28BE6,  9) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + (dr ^ (er | ~ar)) + sch00 + 0x50A28BE6, 11) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ (dr | ~er)) + sch09 + 0x50A28BE6, 13) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch02 + 0x50A28BE6, 15) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + (ar ^ (br | ~cr)) + sch11 + 0x50A28BE6, 15) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + (er ^ (ar | ~br)) + sch04 + 0x50A28BE6,  5) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + (dr ^ (er | ~ar)) + sch13 + 0x50A28BE6,  7) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ (dr | ~er)) + sch06 + 0x50A28BE6,  7) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch15 + 0x50A28BE6,  8) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + (ar ^ (br | ~cr)) + sch08 + 0x50A28BE6, 11) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + (er ^ (ar | ~br)) + sch01 + 0x50A28BE6, 14) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + (dr ^ (er | ~ar)) + sch10 + 0x50A28BE6, 14) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ (dr | ~er)) + sch03 + 0x50A28BE6, 12) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch12 + 0x50A28BE6,  6) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar & cr) | (br & ~cr)) + sch06 + 0x5C4DD124,  9) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er & br) | (ar & ~br)) + sch11 + 0x5C4DD124, 13) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr & ar) | (er & ~ar)) + sch03 + 0x5C4DD124, 15) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr & er) | (dr & ~er)) + sch07 + 0x5C4DD124,  7) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch00 + 0x5C4DD124, 12) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar & cr) | (br & ~cr)) + sch13 + 0x5C4DD124,  8) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er & br) | (ar & ~br)) + sch05 + 0x5C4DD124,  9) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr & ar) | (er & ~ar)) + sch10 + 0x5C4DD124, 11) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr & er) | (dr & ~er)) + sch14 + 0x5C4DD124,  7) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch15 + 0x5C4DD124,  7) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar & cr) | (br & ~cr)) + sch08 + 0x5C4DD124, 12) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er & br) | (ar & ~br)) + sch12 + 0x5C4DD124,  7) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr & ar) | (er & ~ar)) + sch04 + 0x5C4DD124,  6) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr & er) | (dr & ~er)) + sch09 + 0x5C4DD124, 15) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br & dr) | (cr & ~dr)) + sch01 + 0x5C4DD124, 13) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar & cr) | (br & ~cr)) + sch02 + 0x5C4DD124, 11) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er | ~ar) ^ br) + sch15 + 0x6D703EF3,  9) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr | ~er) ^ ar) + sch05 + 0x6D703EF3,  7) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr | ~dr) ^ er) + sch01 + 0x6D703EF3, 15) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br | ~cr) ^ dr) + sch03 + 0x6D703EF3, 11) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar | ~br) ^ cr) + sch07 + 0x6D703EF3,  8) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er | ~ar) ^ br) + sch14 + 0x6D703EF3,  6) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr | ~er) ^ ar) + sch06 + 0x6D703EF3,  6) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr | ~dr) ^ er) + sch09 + 0x6D703EF3, 14) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br | ~cr) ^ dr) + sch11 + 0x6D703EF3, 12) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar | ~br) ^ cr) + sch08 + 0x6D703EF3, 13) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er | ~ar) ^ br) + sch12 + 0x6D703EF3,  5) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr | ~er) ^ ar) + sch02 + 0x6D703EF3, 14) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr | ~dr) ^ er) + sch10 + 0x6D703EF3, 13) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br | ~cr) ^ dr) + sch00 + 0x6D703EF3, 13) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar | ~br) ^ cr) + sch04 + 0x6D703EF3,  7) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er | ~ar) ^ br) + sch13 + 0x6D703EF3,  5) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr & er) | (~dr & ar)) + sch08 + 0x7A6D76E9, 15) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr & dr) | (~cr & er)) + sch06 + 0x7A6D76E9,  5) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br & cr) | (~br & dr)) + sch04 + 0x7A6D76E9,  8) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar & br) | (~ar & cr)) + sch01 + 0x7A6D76E9, 11) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er & ar) | (~er & br)) + sch03 + 0x7A6D76E9, 14) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr & er) | (~dr & ar)) + sch11 + 0x7A6D76E9, 14) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr & dr) | (~cr & er)) + sch15 + 0x7A6D76E9,  6) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br & cr) | (~br & dr)) + sch00 + 0x7A6D76E9, 14) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar & br) | (~ar & cr)) + sch05 + 0x7A6D76E9,  6) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er & ar) | (~er & br)) + sch12 + 0x7A6D76E9,  9) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr & er) | (~dr & ar)) + sch02 + 0x7A6D76E9, 12) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr & dr) | (~cr & er)) + sch13 + 0x7A6D76E9,  9) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br & cr) | (~br & dr)) + sch09 + 0x7A6D76E9, 12) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + ((ar & br) | (~ar & cr)) + sch07 + 0x7A6D76E9,  5) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + ((er & ar) | (~er & br)) + sch10 + 0x7A6D76E9, 15) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + ((dr & er) | (~dr & ar)) + sch14 + 0x7A6D76E9,  8) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ dr ^ er) + sch12,  8) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + (br ^ cr ^ dr) + sch15,  5) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + (ar ^ br ^ cr) + sch10, 12) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + (er ^ ar ^ br) + sch04,  9) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + (dr ^ er ^ ar) + sch01, 12) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ dr ^ er) + sch05,  5) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + (br ^ cr ^ dr) + sch08, 14) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + (ar ^ br ^ cr) + sch07,  6) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + (er ^ ar ^ br) + sch06,  8) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + (dr ^ er ^ ar) + sch02, 13) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ dr ^ er) + sch13,  6) + ar; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + (br ^ cr ^ dr) + sch14,  5) + er; cr = rotateLeft(cr, 10);
			er = rotateLeft(er + (ar ^ br ^ cr) + sch00, 15) + dr; br = rotateLeft(br, 10);
			dr = rotateLeft(dr + (er ^ ar ^ br) + sch03, 13) + cr; ar = rotateLeft(ar, 10);
			cr = rotateLeft(cr + (dr ^ er ^ ar) + sch09, 11) + br; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ dr ^ er) + sch11, 11) + ar; dr = rotateLeft(dr, 10);
			
			int temp = state[1] + cl + dr;
			state[1] = state[2] + dl + er;
			state[2] = state[3] + el + ar;
			state[3] = state[4] + al + br;
			state[4] = state[0] + bl + cr;
			state[0] = temp;
		}
	}
	
}
