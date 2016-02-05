/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/native-hash-functions-for-java
 */

package nayuki.nativehash;

import static java.lang.Integer.rotateLeft;


final class Ripemd320Java extends Ripemd320 {
	
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
			
			int al = state[0], ar = state[5];
			int bl = state[1], br = state[6];
			int cl = state[2], cr = state[7];
			int dl = state[3], dr = state[8];
			int el = state[4], er = state[9];
			
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch00, 11) + el; cl = rotateLeft(cl, 10);                          ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch05 + 0x50A28BE6,  8) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + (al ^ bl ^ cl) + sch01, 14) + dl; bl = rotateLeft(bl, 10);                          er = rotateLeft(er + (ar ^ (br | ~cr)) + sch14 + 0x50A28BE6,  9) + dr; br = rotateLeft(br, 10);
			dl = rotateLeft(dl + (el ^ al ^ bl) + sch02, 15) + cl; al = rotateLeft(al, 10);                          dr = rotateLeft(dr + (er ^ (ar | ~br)) + sch07 + 0x50A28BE6,  9) + cr; ar = rotateLeft(ar, 10);
			cl = rotateLeft(cl + (dl ^ el ^ al) + sch03, 12) + bl; el = rotateLeft(el, 10);                          cr = rotateLeft(cr + (dr ^ (er | ~ar)) + sch00 + 0x50A28BE6, 11) + br; er = rotateLeft(er, 10);
			bl = rotateLeft(bl + (cl ^ dl ^ el) + sch04,  5) + al; dl = rotateLeft(dl, 10);                          br = rotateLeft(br + (cr ^ (dr | ~er)) + sch09 + 0x50A28BE6, 13) + ar; dr = rotateLeft(dr, 10);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch05,  8) + el; cl = rotateLeft(cl, 10);                          ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch02 + 0x50A28BE6, 15) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + (al ^ bl ^ cl) + sch06,  7) + dl; bl = rotateLeft(bl, 10);                          er = rotateLeft(er + (ar ^ (br | ~cr)) + sch11 + 0x50A28BE6, 15) + dr; br = rotateLeft(br, 10);
			dl = rotateLeft(dl + (el ^ al ^ bl) + sch07,  9) + cl; al = rotateLeft(al, 10);                          dr = rotateLeft(dr + (er ^ (ar | ~br)) + sch04 + 0x50A28BE6,  5) + cr; ar = rotateLeft(ar, 10);
			cl = rotateLeft(cl + (dl ^ el ^ al) + sch08, 11) + bl; el = rotateLeft(el, 10);                          cr = rotateLeft(cr + (dr ^ (er | ~ar)) + sch13 + 0x50A28BE6,  7) + br; er = rotateLeft(er, 10);
			bl = rotateLeft(bl + (cl ^ dl ^ el) + sch09, 13) + al; dl = rotateLeft(dl, 10);                          br = rotateLeft(br + (cr ^ (dr | ~er)) + sch06 + 0x50A28BE6,  7) + ar; dr = rotateLeft(dr, 10);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch10, 14) + el; cl = rotateLeft(cl, 10);                          ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch15 + 0x50A28BE6,  8) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + (al ^ bl ^ cl) + sch11, 15) + dl; bl = rotateLeft(bl, 10);                          er = rotateLeft(er + (ar ^ (br | ~cr)) + sch08 + 0x50A28BE6, 11) + dr; br = rotateLeft(br, 10);
			dl = rotateLeft(dl + (el ^ al ^ bl) + sch12,  6) + cl; al = rotateLeft(al, 10);                          dr = rotateLeft(dr + (er ^ (ar | ~br)) + sch01 + 0x50A28BE6, 14) + cr; ar = rotateLeft(ar, 10);
			cl = rotateLeft(cl + (dl ^ el ^ al) + sch13,  7) + bl; el = rotateLeft(el, 10);                          cr = rotateLeft(cr + (dr ^ (er | ~ar)) + sch10 + 0x50A28BE6, 14) + br; er = rotateLeft(er, 10);
			bl = rotateLeft(bl + (cl ^ dl ^ el) + sch14,  9) + al; dl = rotateLeft(dl, 10);                          br = rotateLeft(br + (cr ^ (dr | ~er)) + sch03 + 0x50A28BE6, 12) + ar; dr = rotateLeft(dr, 10);
			al = rotateLeft(al + (bl ^ cl ^ dl) + sch15,  8) + el; cl = rotateLeft(cl, 10);                          ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch12 + 0x50A28BE6,  6) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + ((ar & bl) | (~ar & cl)) + sch07 + 0x5A827999,  7) + dl;  bl = rotateLeft(bl, 10);  er = rotateLeft(er + ((al & cr) | (br & ~cr)) + sch06 + 0x5C4DD124,  9) + dr; br = rotateLeft(br, 10);
			dl = rotateLeft(dl + ((el & ar) | (~el & bl)) + sch04 + 0x5A827999,  6) + cl;  ar = rotateLeft(ar, 10);  dr = rotateLeft(dr + ((er & br) | (al & ~br)) + sch11 + 0x5C4DD124, 13) + cr; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & el) | (~dl & ar)) + sch13 + 0x5A827999,  8) + bl;  el = rotateLeft(el, 10);  cr = rotateLeft(cr + ((dr & al) | (er & ~al)) + sch03 + 0x5C4DD124, 15) + br; er = rotateLeft(er, 10);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & el)) + sch01 + 0x5A827999, 13) + ar;  dl = rotateLeft(dl, 10);  br = rotateLeft(br + ((cr & er) | (dr & ~er)) + sch07 + 0x5C4DD124,  7) + al; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((bl & cl) | (~bl & dl)) + sch10 + 0x5A827999, 11) + el;  cl = rotateLeft(cl, 10);  al = rotateLeft(al + ((br & dr) | (cr & ~dr)) + sch00 + 0x5C4DD124, 12) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + ((ar & bl) | (~ar & cl)) + sch06 + 0x5A827999,  9) + dl;  bl = rotateLeft(bl, 10);  er = rotateLeft(er + ((al & cr) | (br & ~cr)) + sch13 + 0x5C4DD124,  8) + dr; br = rotateLeft(br, 10);
			dl = rotateLeft(dl + ((el & ar) | (~el & bl)) + sch15 + 0x5A827999,  7) + cl;  ar = rotateLeft(ar, 10);  dr = rotateLeft(dr + ((er & br) | (al & ~br)) + sch05 + 0x5C4DD124,  9) + cr; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & el) | (~dl & ar)) + sch03 + 0x5A827999, 15) + bl;  el = rotateLeft(el, 10);  cr = rotateLeft(cr + ((dr & al) | (er & ~al)) + sch10 + 0x5C4DD124, 11) + br; er = rotateLeft(er, 10);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & el)) + sch12 + 0x5A827999,  7) + ar;  dl = rotateLeft(dl, 10);  br = rotateLeft(br + ((cr & er) | (dr & ~er)) + sch14 + 0x5C4DD124,  7) + al; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((bl & cl) | (~bl & dl)) + sch00 + 0x5A827999, 12) + el;  cl = rotateLeft(cl, 10);  al = rotateLeft(al + ((br & dr) | (cr & ~dr)) + sch15 + 0x5C4DD124,  7) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + ((ar & bl) | (~ar & cl)) + sch09 + 0x5A827999, 15) + dl;  bl = rotateLeft(bl, 10);  er = rotateLeft(er + ((al & cr) | (br & ~cr)) + sch08 + 0x5C4DD124, 12) + dr; br = rotateLeft(br, 10);
			dl = rotateLeft(dl + ((el & ar) | (~el & bl)) + sch05 + 0x5A827999,  9) + cl;  ar = rotateLeft(ar, 10);  dr = rotateLeft(dr + ((er & br) | (al & ~br)) + sch12 + 0x5C4DD124,  7) + cr; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl & el) | (~dl & ar)) + sch02 + 0x5A827999, 11) + bl;  el = rotateLeft(el, 10);  cr = rotateLeft(cr + ((dr & al) | (er & ~al)) + sch04 + 0x5C4DD124,  6) + br; er = rotateLeft(er, 10);
			bl = rotateLeft(bl + ((cl & dl) | (~cl & el)) + sch14 + 0x5A827999,  7) + ar;  dl = rotateLeft(dl, 10);  br = rotateLeft(br + ((cr & er) | (dr & ~er)) + sch09 + 0x5C4DD124, 15) + al; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((bl & cl) | (~bl & dl)) + sch11 + 0x5A827999, 13) + el;  cl = rotateLeft(cl, 10);  al = rotateLeft(al + ((br & dr) | (cr & ~dr)) + sch01 + 0x5C4DD124, 13) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + ((ar & bl) | (~ar & cl)) + sch08 + 0x5A827999, 12) + dl;  bl = rotateLeft(bl, 10);  er = rotateLeft(er + ((al & cr) | (br & ~cr)) + sch02 + 0x5C4DD124, 11) + dr; br = rotateLeft(br, 10);
			dl = rotateLeft(dl + ((el | ~ar) ^ br) + sch03 + 0x6ED9EBA1, 11) + cl; ar = rotateLeft(ar, 10);          dr = rotateLeft(dr + ((er | ~al) ^ bl) + sch15 + 0x6D703EF3,  9) + cr; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl | ~el) ^ ar) + sch10 + 0x6ED9EBA1, 13) + br; el = rotateLeft(el, 10);          cr = rotateLeft(cr + ((dr | ~er) ^ al) + sch05 + 0x6D703EF3,  7) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cl | ~dl) ^ el) + sch14 + 0x6ED9EBA1,  6) + ar; dl = rotateLeft(dl, 10);          bl = rotateLeft(bl + ((cr | ~dr) ^ er) + sch01 + 0x6D703EF3, 15) + al; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br | ~cl) ^ dl) + sch04 + 0x6ED9EBA1,  7) + el; cl = rotateLeft(cl, 10);          al = rotateLeft(al + ((bl | ~cr) ^ dr) + sch03 + 0x6D703EF3, 11) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + ((ar | ~br) ^ cl) + sch09 + 0x6ED9EBA1, 14) + dl; br = rotateLeft(br, 10);          er = rotateLeft(er + ((al | ~bl) ^ cr) + sch07 + 0x6D703EF3,  8) + dr; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el | ~ar) ^ br) + sch15 + 0x6ED9EBA1,  9) + cl; ar = rotateLeft(ar, 10);          dr = rotateLeft(dr + ((er | ~al) ^ bl) + sch14 + 0x6D703EF3,  6) + cr; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl | ~el) ^ ar) + sch08 + 0x6ED9EBA1, 13) + br; el = rotateLeft(el, 10);          cr = rotateLeft(cr + ((dr | ~er) ^ al) + sch06 + 0x6D703EF3,  6) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cl | ~dl) ^ el) + sch01 + 0x6ED9EBA1, 15) + ar; dl = rotateLeft(dl, 10);          bl = rotateLeft(bl + ((cr | ~dr) ^ er) + sch09 + 0x6D703EF3, 14) + al; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br | ~cl) ^ dl) + sch02 + 0x6ED9EBA1, 14) + el; cl = rotateLeft(cl, 10);          al = rotateLeft(al + ((bl | ~cr) ^ dr) + sch11 + 0x6D703EF3, 12) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + ((ar | ~br) ^ cl) + sch07 + 0x6ED9EBA1,  8) + dl; br = rotateLeft(br, 10);          er = rotateLeft(er + ((al | ~bl) ^ cr) + sch08 + 0x6D703EF3, 13) + dr; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el | ~ar) ^ br) + sch00 + 0x6ED9EBA1, 13) + cl; ar = rotateLeft(ar, 10);          dr = rotateLeft(dr + ((er | ~al) ^ bl) + sch12 + 0x6D703EF3,  5) + cr; al = rotateLeft(al, 10);
			cl = rotateLeft(cl + ((dl | ~el) ^ ar) + sch06 + 0x6ED9EBA1,  6) + br; el = rotateLeft(el, 10);          cr = rotateLeft(cr + ((dr | ~er) ^ al) + sch02 + 0x6D703EF3, 14) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cl | ~dl) ^ el) + sch13 + 0x6ED9EBA1,  5) + ar; dl = rotateLeft(dl, 10);          bl = rotateLeft(bl + ((cr | ~dr) ^ er) + sch10 + 0x6D703EF3, 13) + al; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br | ~cl) ^ dl) + sch11 + 0x6ED9EBA1, 12) + el; cl = rotateLeft(cl, 10);          al = rotateLeft(al + ((bl | ~cr) ^ dr) + sch00 + 0x6D703EF3, 13) + er; cr = rotateLeft(cr, 10);
			el = rotateLeft(el + ((ar | ~br) ^ cl) + sch05 + 0x6ED9EBA1,  7) + dl; br = rotateLeft(br, 10);          er = rotateLeft(er + ((al | ~bl) ^ cr) + sch04 + 0x6D703EF3,  7) + dr; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el | ~ar) ^ br) + sch12 + 0x6ED9EBA1,  5) + cl; ar = rotateLeft(ar, 10);          dr = rotateLeft(dr + ((er | ~al) ^ bl) + sch13 + 0x6D703EF3,  5) + cr; al = rotateLeft(al, 10);
			cr = rotateLeft(cr + ((dl & ar) | (el & ~ar)) + sch01 + 0x8F1BBCDC, 11) + br; el = rotateLeft(el, 10);   cl = rotateLeft(cl + ((dr & er) | (~dr & al)) + sch08 + 0x7A6D76E9, 15) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr & el) | (dl & ~el)) + sch09 + 0x8F1BBCDC, 12) + ar; dl = rotateLeft(dl, 10);   bl = rotateLeft(bl + ((cl & dr) | (~cl & er)) + sch06 + 0x7A6D76E9,  5) + al; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br & dl) | (cr & ~dl)) + sch11 + 0x8F1BBCDC, 14) + el; cr = rotateLeft(cr, 10);   al = rotateLeft(al + ((bl & cl) | (~bl & dr)) + sch04 + 0x7A6D76E9,  8) + er; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((ar & cr) | (br & ~cr)) + sch10 + 0x8F1BBCDC, 15) + dl; br = rotateLeft(br, 10);   er = rotateLeft(er + ((al & bl) | (~al & cl)) + sch01 + 0x7A6D76E9, 11) + dr; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el & br) | (ar & ~br)) + sch00 + 0x8F1BBCDC, 14) + cr; ar = rotateLeft(ar, 10);   dr = rotateLeft(dr + ((er & al) | (~er & bl)) + sch03 + 0x7A6D76E9, 14) + cl; al = rotateLeft(al, 10);
			cr = rotateLeft(cr + ((dl & ar) | (el & ~ar)) + sch08 + 0x8F1BBCDC, 15) + br; el = rotateLeft(el, 10);   cl = rotateLeft(cl + ((dr & er) | (~dr & al)) + sch11 + 0x7A6D76E9, 14) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr & el) | (dl & ~el)) + sch12 + 0x8F1BBCDC,  9) + ar; dl = rotateLeft(dl, 10);   bl = rotateLeft(bl + ((cl & dr) | (~cl & er)) + sch15 + 0x7A6D76E9,  6) + al; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br & dl) | (cr & ~dl)) + sch04 + 0x8F1BBCDC,  8) + el; cr = rotateLeft(cr, 10);   al = rotateLeft(al + ((bl & cl) | (~bl & dr)) + sch00 + 0x7A6D76E9, 14) + er; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((ar & cr) | (br & ~cr)) + sch13 + 0x8F1BBCDC,  9) + dl; br = rotateLeft(br, 10);   er = rotateLeft(er + ((al & bl) | (~al & cl)) + sch05 + 0x7A6D76E9,  6) + dr; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el & br) | (ar & ~br)) + sch03 + 0x8F1BBCDC, 14) + cr; ar = rotateLeft(ar, 10);   dr = rotateLeft(dr + ((er & al) | (~er & bl)) + sch12 + 0x7A6D76E9,  9) + cl; al = rotateLeft(al, 10);
			cr = rotateLeft(cr + ((dl & ar) | (el & ~ar)) + sch07 + 0x8F1BBCDC,  5) + br; el = rotateLeft(el, 10);   cl = rotateLeft(cl + ((dr & er) | (~dr & al)) + sch02 + 0x7A6D76E9, 12) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + ((cr & el) | (dl & ~el)) + sch15 + 0x8F1BBCDC,  6) + ar; dl = rotateLeft(dl, 10);   bl = rotateLeft(bl + ((cl & dr) | (~cl & er)) + sch13 + 0x7A6D76E9,  9) + al; dr = rotateLeft(dr, 10);
			ar = rotateLeft(ar + ((br & dl) | (cr & ~dl)) + sch14 + 0x8F1BBCDC,  8) + el; cr = rotateLeft(cr, 10);   al = rotateLeft(al + ((bl & cl) | (~bl & dr)) + sch09 + 0x7A6D76E9, 12) + er; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + ((ar & cr) | (br & ~cr)) + sch05 + 0x8F1BBCDC,  6) + dl; br = rotateLeft(br, 10);   er = rotateLeft(er + ((al & bl) | (~al & cl)) + sch07 + 0x7A6D76E9,  5) + dr; bl = rotateLeft(bl, 10);
			dl = rotateLeft(dl + ((el & br) | (ar & ~br)) + sch06 + 0x8F1BBCDC,  5) + cr; ar = rotateLeft(ar, 10);   dr = rotateLeft(dr + ((er & al) | (~er & bl)) + sch10 + 0x7A6D76E9, 15) + cl; al = rotateLeft(al, 10);
			cr = rotateLeft(cr + ((dl & ar) | (el & ~ar)) + sch02 + 0x8F1BBCDC, 12) + br; el = rotateLeft(el, 10);   cl = rotateLeft(cl + ((dr & er) | (~dr & al)) + sch14 + 0x7A6D76E9,  8) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ (dr | ~el)) + sch04 + 0xA953FD4E,  9) + ar; dr = rotateLeft(dr, 10);          bl = rotateLeft(bl + (cl ^ dl ^ er) + sch12,  8) + al; dl = rotateLeft(dl, 10);
			ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch00 + 0xA953FD4E, 15) + el; cr = rotateLeft(cr, 10);          al = rotateLeft(al + (bl ^ cl ^ dl) + sch15,  5) + er; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + (ar ^ (br | ~cr)) + sch05 + 0xA953FD4E,  5) + dr; br = rotateLeft(br, 10);          er = rotateLeft(er + (al ^ bl ^ cl) + sch10, 12) + dl; bl = rotateLeft(bl, 10);
			dr = rotateLeft(dr + (el ^ (ar | ~br)) + sch09 + 0xA953FD4E, 11) + cr; ar = rotateLeft(ar, 10);          dl = rotateLeft(dl + (er ^ al ^ bl) + sch04,  9) + cl; al = rotateLeft(al, 10);
			cr = rotateLeft(cr + (dr ^ (el | ~ar)) + sch07 + 0xA953FD4E,  6) + br; el = rotateLeft(el, 10);          cl = rotateLeft(cl + (dl ^ er ^ al) + sch01, 12) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ (dr | ~el)) + sch12 + 0xA953FD4E,  8) + ar; dr = rotateLeft(dr, 10);          bl = rotateLeft(bl + (cl ^ dl ^ er) + sch05,  5) + al; dl = rotateLeft(dl, 10);
			ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch02 + 0xA953FD4E, 13) + el; cr = rotateLeft(cr, 10);          al = rotateLeft(al + (bl ^ cl ^ dl) + sch08, 14) + er; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + (ar ^ (br | ~cr)) + sch10 + 0xA953FD4E, 12) + dr; br = rotateLeft(br, 10);          er = rotateLeft(er + (al ^ bl ^ cl) + sch07,  6) + dl; bl = rotateLeft(bl, 10);
			dr = rotateLeft(dr + (el ^ (ar | ~br)) + sch14 + 0xA953FD4E,  5) + cr; ar = rotateLeft(ar, 10);          dl = rotateLeft(dl + (er ^ al ^ bl) + sch06,  8) + cl; al = rotateLeft(al, 10);
			cr = rotateLeft(cr + (dr ^ (el | ~ar)) + sch01 + 0xA953FD4E, 12) + br; el = rotateLeft(el, 10);          cl = rotateLeft(cl + (dl ^ er ^ al) + sch02, 13) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ (dr | ~el)) + sch03 + 0xA953FD4E, 13) + ar; dr = rotateLeft(dr, 10);          bl = rotateLeft(bl + (cl ^ dl ^ er) + sch13,  6) + al; dl = rotateLeft(dl, 10);
			ar = rotateLeft(ar + (br ^ (cr | ~dr)) + sch08 + 0xA953FD4E, 14) + el; cr = rotateLeft(cr, 10);          al = rotateLeft(al + (bl ^ cl ^ dl) + sch14,  5) + er; cl = rotateLeft(cl, 10);
			el = rotateLeft(el + (ar ^ (br | ~cr)) + sch11 + 0xA953FD4E, 11) + dr; br = rotateLeft(br, 10);          er = rotateLeft(er + (al ^ bl ^ cl) + sch00, 15) + dl; bl = rotateLeft(bl, 10);
			dr = rotateLeft(dr + (el ^ (ar | ~br)) + sch06 + 0xA953FD4E,  8) + cr; ar = rotateLeft(ar, 10);          dl = rotateLeft(dl + (er ^ al ^ bl) + sch03, 13) + cl; al = rotateLeft(al, 10);
			cr = rotateLeft(cr + (dr ^ (el | ~ar)) + sch15 + 0xA953FD4E,  5) + br; el = rotateLeft(el, 10);          cl = rotateLeft(cl + (dl ^ er ^ al) + sch09, 11) + bl; er = rotateLeft(er, 10);
			br = rotateLeft(br + (cr ^ (dr | ~el)) + sch13 + 0xA953FD4E,  6) + ar; dr = rotateLeft(dr, 10);          bl = rotateLeft(bl + (cl ^ dl ^ er) + sch11, 11) + al; dl = rotateLeft(dl, 10);
			
			state[0] += ar;
			state[1] += br;
			state[2] += cr;
			state[3] += dr;
			state[4] += er;
			state[5] += al;
			state[6] += bl;
			state[7] += cl;
			state[8] += dl;
			state[9] += el;
		}
	}
	
}
