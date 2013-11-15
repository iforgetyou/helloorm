package com.zy17.auth;

import org.springframework.security.crypto.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-9 Time: 下午3:35 To
 * change this template use File | Settings | File Templates.
 */
public class Encrypt {

	public static String encrypt(String msg) {
		String result = "";
		try {
			// 128 bit
			byte[] ivBytes = new byte[16];
			byte[] keyBytes = "com.zy17".getBytes("UTF-8");
			System.arraycopy(keyBytes, 0, ivBytes, 0,
                    Math.min(keyBytes.length, 16));
			SecretKey secret = new SecretKeySpec(ivBytes, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			cipher.init(Cipher.ENCRYPT_MODE, secret);

			result = new String(Base64.encode(cipher.doFinal(msg.getBytes())));
		} catch (Exception e) {
			throw new RestAuthenticationException("", e.getCause());
		}

		return result;
	}

	public static String decrypt(String info) {

		String plaintext = "";

		try {
			byte[] ivBytes = new byte[16];
			byte[] keyBytes = "com.zy17".getBytes("UTF-8");
			System.arraycopy(keyBytes, 0, ivBytes, 0,
                    Math.min(keyBytes.length, 16));

			SecretKey secret = new SecretKeySpec(ivBytes, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secret);

			plaintext = new String(
					cipher.doFinal(Base64.decode(info.getBytes())), "UTF-8");
		} catch (Exception e) {
			throw new RestAuthenticationException("Authorization failed.",
					e.getCause());
		}
		return plaintext;
	}

	public static void main(String[] args) {
		System.out
				.println(encrypt("admin:8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918"));
		System.out
				.println(decrypt("ob4zSq9GG96ukfaqYn2GKjvOxVNnG5t/fBsetSzq7xM7qy+Vkd0iZJ94f85IkRPd"));
	}
}
