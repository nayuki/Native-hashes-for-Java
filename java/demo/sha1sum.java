/* 
 * Native hash functions for Java
 * 
 * Copyright (c) Nayuki Minase
 * http://nayuki.eigenstate.org/page/native-hash-functions-for-java
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import nayuki.nativehash.Sha1;


public class sha1sum {
	
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("Usage: java sha1sum FILES...");
			System.exit(1);
		}
		
		int status = 0;
		for (String arg : args) {
			InputStream in = null;
			try {
				if (arg.equals("-"))
					in = System.in;
				else {
					File file = new File(arg);
					if (file.isDirectory()) {
						System.err.println("sha1sum: " + arg + ": Is a directory");
						status = 1;
						continue;
					} else if (!file.exists()) {
						System.err.println("sha1sum: " + arg + ": No such file or directory");
						status = 1;
						continue;
					} else {
						in = new FileInputStream(file);
					}
				}
				
				Sha1 hasher = new Sha1();
				byte[] buf = new byte[64 * 1024];
				while (true) {
					int n = in.read(buf);
					if (n == -1)
						break;
					hasher.update(buf, 0, n);
				}
				
				byte[] hash = hasher.getHash();
				StringBuilder sb = new StringBuilder();
				for (byte b : hash) {
					sb.append(Integer.toString((b >>> 4) & 0xF, 16))
					  .append(Integer.toString((b >>> 0) & 0xF, 16));
				}
				System.out.println(sb.toString() + "  " + arg);
				
			} finally {
				if (in != null && in != System.in)
					in.close();
			}
		}
		System.exit(status);
	}
	
}
