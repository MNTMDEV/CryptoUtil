package com.mntmdev.cryptoutil.core;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.mntmdev.cryptoutil.common.AsymKeyPair;
import com.mntmdev.cryptoutil.common.EncodingUtil;
import com.mntmdev.cryptoutil.common.KeyUtil;

public class AsymUtil {
	private EncodingUtil encodingUtil;

	public AsymUtil() {
		super();
		encodingUtil = new EncodingUtil();
	}

	/**
	 * 不对称加密
	 * 
	 * @param flag  true为使用公钥，false为使用私钥
	 * @param type  加密类型 RSA
	 * @param byArr 原文二进制
	 * @param byKey 密钥二进制
	 * @return 密文二进制
	 */
	public byte[] EncryptByte(boolean flag, String type, byte[] byArr, byte[] byKey) {
		try {
			Cipher cipher = Cipher.getInstance(type);
			KeySpec keySpec = null;
			if (flag) {
				keySpec = new X509EncodedKeySpec(byKey);
			} else {
				keySpec = new PKCS8EncodedKeySpec(byKey);
			}
			KeyFactory keyFactory = KeyFactory.getInstance(type);
			Key key = null;
			if (flag) {
				key = keyFactory.generatePublic(keySpec);
			} else {
				key = keyFactory.generatePrivate(keySpec);
			}
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byEnc = cipher.doFinal(byArr);
			return byEnc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 不对称加密的字符串版
	 * 
	 * @param flag true为使用公钥，false为使用私钥
	 * @param type 加密类型 RSA
	 * @param str  原文字符串
	 * @param key  密钥base64
	 * @return 密文base64
	 */
	public String EncryptString(boolean flag, String type, String str, String key) {
		try {
			byte[] byArr = str.getBytes("utf-8");
			byte[] byKey = encodingUtil.Base64DecodeByte(key);
			byte[] byEnc = EncryptByte(flag, type, byArr, byKey);
			return encodingUtil.Base64Encode(byEnc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 不对称解密
	 * 
	 * @param flag  true为使用公钥，false为使用私钥
	 * @param type  加密类型 RSA
	 * @param byArr 原文二进制
	 * @param byKey 密钥二进制
	 * @return 密文二进制
	 */
	public byte[] DecryptByte(boolean flag, String type, byte[] byArr, byte[] byKey) {
		try {
			Cipher cipher = Cipher.getInstance(type);
			KeySpec keySpec = null;
			if (flag) {
				keySpec = new X509EncodedKeySpec(byKey);
			} else {
				keySpec = new PKCS8EncodedKeySpec(byKey);
			}
			KeyFactory keyFactory = KeyFactory.getInstance(type);
			Key key = null;
			if (flag) {
				key = keyFactory.generatePublic(keySpec);
			} else {
				key = keyFactory.generatePrivate(keySpec);
			}
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] byEnc = cipher.doFinal(byArr);
			return byEnc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 不对称解密字符串版
	 * 
	 * @param flag true为使用公钥，false为使用私钥
	 * @param type 加密类型 RSA
	 * @param str  密文base64
	 * @param key  密钥base64
	 * @return 明文字符串
	 */
	public String DecryptString(boolean flag, String type, String str, String key) {
		try {
			byte[] byArr = encodingUtil.Base64DecodeByte(str);
			byte[] byKey = encodingUtil.Base64DecodeByte(key);
			byte[] byDec = DecryptByte(flag, type, byArr, byKey);
			return new String(byDec, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 类入口函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "123456";
		AsymUtil util = new AsymUtil();
		KeyUtil keyUtil = new KeyUtil();
		AsymKeyPair keyPair = keyUtil.getKeyPair("RSA", 1024);
		String pub = keyPair.getPubString();
		String pri = keyPair.getPriString();
		String enc = util.EncryptString(true, "RSA", str, pub);
		System.out.println("以下是不对称算法的公钥加密私钥解密");
		System.out.println(enc);
		System.out.println(util.DecryptString(false, "RSA", enc, pri));
		System.out.println("以下是不对称算法的公钥解密私钥加密");
		enc = util.EncryptString(false, "RSA", str, pri);
		System.out.println(enc);
		System.out.println(util.DecryptString(true, "RSA", enc, pub));
	}

}
