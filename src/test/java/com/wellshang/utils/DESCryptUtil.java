package com.wellshang.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESCryptUtil {
	private static final String strKey = "abcdefghijk1234";

	public static void main(String[] args) throws Exception {
		String orgContent = "ABCDEFGxyz1234567890~<>?&";
		String enContent = DESCryptUtil.enCrypt(orgContent);
		String deContent = DESCryptUtil.deCrypt(enContent);

		System.out.println("Original text: " + orgContent);
		System.out.println("Encrypt text: " + enContent);
		System.out.println("Decrypt text: " + deContent);
	}

	private static SecretKey getKey() throws NoSuchAlgorithmException,
			InvalidKeyException, InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		KeySpec keySpec = new DESKeySpec(strKey.getBytes());
		return keyFactory.generateSecret(keySpec);
	}

	private static byte[] doCrypt(byte[] data, int mode)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException {
		Cipher c = Cipher.getInstance("DES");
		SecretKey key = DESCryptUtil.getKey();
		c.init(mode, key);
		return c.doFinal(data);
	}
	
	public static String enCrypt(String content) {
		String result = null;
		try {
			byte[] deContent = DESCryptUtil.doCrypt(content.getBytes(),
					Cipher.ENCRYPT_MODE);
			Base64 encoder = new Base64();
			result = encoder.encodeToString(deContent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String deCrypt(String content) {
		String result = null;
		try {
			Base64 decoder = new Base64();
			byte[] orgContent = DESCryptUtil.doCrypt(decoder.decode(content),
					Cipher.DECRYPT_MODE);
			result = new String(orgContent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}