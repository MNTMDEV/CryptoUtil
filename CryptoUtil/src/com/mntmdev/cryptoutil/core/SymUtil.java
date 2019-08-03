package com.mntmdev.cryptoutil.core;

import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.mntmdev.cryptoutil.common.EncodingUtil;

/**
 * 对称加密辅助类(这个类对于对称加密的处理方式更加通用)
 * 
 * @author MNTMDEV
 *
 */
public class SymUtil {
	private EncodingUtil encodingUtil;

	public SymUtil() {
		super();
		encodingUtil = new EncodingUtil();
	}

	/**
	 * 对二进制内容使用二进制密钥进行对称加密
	 * 
	 * @param type  加密类型
	 * @param byArr 原文
	 * @param byKey 密钥
	 * @return 加密结果
	 */
	public byte[] EncryptByte(String type, byte[] byArr, byte[] byKey) {
		try {
			SecretKey key = new SecretKeySpec(byKey, type);
			Cipher cipher = Cipher.getInstance(type);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] ret = cipher.doFinal(byArr);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对二进制内容使用二进制密钥进行对称解密
	 * 
	 * @param type  加密类型
	 * @param byArr 密文
	 * @param byKey 密钥
	 * @return 解密结果
	 */
	public byte[] DecryptByte(String type, byte[] byArr, byte[] byKey) {
		try {
			SecretKey key = new SecretKeySpec(byKey, type);
			Cipher cipher = Cipher.getInstance(type);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] ret = cipher.doFinal(byArr);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对二进制内容使用二进制密钥进行AES加密
	 * 
	 * @param byArr 明文
	 * @param byKey 密钥
	 * @return
	 */
	public byte[] AesEncryptByte(byte[] byArr, byte[] byKey) {
		byte[] byEnc = EncryptByte("AES", byArr, byKey);
		return byEnc;
	}

	/**
	 * 对字符串使用16进制AES密钥进行加密
	 * 
	 * @param str 原文
	 * @param key 密钥(16进制字符串)
	 * @return 密文的base64
	 */
	public String AesEncryptString(String str, String key) {
		try {
			byte[] byArr = str.getBytes("utf-8");
			byte[] byKey = encodingUtil.Hex2Byte(key);
			byte[] byEnc = AesEncryptByte(byArr, byKey);
			String strEnc = encodingUtil.Base64Encode(byEnc);
			return strEnc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对二进制密文使用二进制密钥进行AES解密
	 * 
	 * @param byArr 密文
	 * @param byKey 密钥
	 * @return
	 */
	public byte[] AesDecryptByte(byte[] byArr, byte[] byKey) {
		byte[] byDec = DecryptByte("AES", byArr, byKey);
		return byDec;
	}

	/**
	 * 对密文base64字符串使用16进制密钥进行AES解密
	 * 
	 * @param str 密文的base64
	 * @param key 密钥的16进制
	 * @return 原文字符串
	 */
	public String AesDecryptString(String str, String key) {
		byte[] byArr = encodingUtil.Base64DecodeByte(str);
		byte[] byKey = encodingUtil.Hex2Byte(key);
		byte[] byDec = AesDecryptByte(byArr, byKey);
		String ret = null;
		try {
			ret = new String(byDec, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 对二进制内容使用二进制密钥进行DES加密
	 * 
	 * @param byArr 明文
	 * @param byKey 密钥
	 * @return
	 */
	public byte[] DesEncryptByte(byte[] byArr, byte[] byKey) {
		byte[] byEnc = EncryptByte("DES", byArr, byKey);
		return byEnc;
	}

	/**
	 * 对字符串使用16进制AES密钥进行加密
	 * 
	 * @param str 原文
	 * @param key 密钥(16进制字符串)
	 * @return 密文的base64
	 */
	public String DesEncryptString(String str, String key) {
		try {
			byte[] byArr = str.getBytes("utf-8");
			byte[] byKey = encodingUtil.Hex2Byte(key);
			byte[] byEnc = DesEncryptByte(byArr, byKey);
			String strEnc = encodingUtil.Base64Encode(byEnc);
			return strEnc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对二进制密文使用二进制密钥进行DES解密
	 * 
	 * @param byArr 密文
	 * @param byKey 密钥
	 * @return
	 */
	public byte[] DesDecryptByte(byte[] byArr, byte[] byKey) {
		byte[] byDec = DecryptByte("DES", byArr, byKey);
		return byDec;
	}

	/**
	 * 对密文base64字符串使用16进制密钥进行DES解密
	 * 
	 * @param str 密文的base64
	 * @param key 密钥的16进制
	 * @return 原文字符串
	 */
	public String DesDecryptString(String str, String key) {
		byte[] byArr = encodingUtil.Base64DecodeByte(str);
		byte[] byKey = encodingUtil.Hex2Byte(key);
		byte[] byDec = DesDecryptByte(byArr, byKey);
		String ret = null;
		try {
			ret = new String(byDec, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) {
		String aesKey = "0123456789ABCDEFFEDCBA9876543210";
		String desKey = "0123456789ABCDEF";
		String str = "123456";
		String aesEnc = "nLPeCKJpD1GPyu0Oe2EtRg==";
		String desEnc = "YvyLlffrXN8=";
		SymUtil util = new SymUtil();
		System.out.println("AES加密和解密");
		System.out.println(util.AesEncryptString(str, aesKey));
		System.out.println(util.AesDecryptString(aesEnc, aesKey));
		System.out.println("DES加密和解密");
		System.out.println(util.DesEncryptString(str, desKey));
		System.out.println(util.DesDecryptString(desEnc, desKey));
	}

}
