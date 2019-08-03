package com.mntmdev.cryptoutil.core;

import com.mntmdev.cryptoutil.common.KeyUtil;

/**
 * 简化版对称加密类(给定密钥和原文，直接给出结果)
 * 
 * @author MNTMDEV
 *
 */
public class SymSimple {
	private SymUtil symUtil;
	private KeyUtil keyUtil;

	public SymSimple() {
		super();
		symUtil = new SymUtil();
		keyUtil = new KeyUtil();
	}

	/**
	 * AES加密
	 * 
	 * @param str 原文
	 * @param key 密钥
	 * @return
	 */
	public String AesEncrypt(String str, String key) {
		key = keyUtil.getKeyHex(key, "AES");
		return symUtil.AesEncryptString(str, key);
	}

	/**
	 * AES解密
	 * 
	 * @param str 密文
	 * @param key 密钥
	 * @return
	 */
	public String AesDecrypt(String str, String key) {
		key = keyUtil.getKeyHex(key, "AES");
		return symUtil.AesDecryptString(str, key);
	}

	/**
	 * DES加密
	 * 
	 * @param str 原文
	 * @param key 密钥
	 * @return
	 */
	public String DesEncrypt(String str, String key) {
		key = keyUtil.getKeyHex(key, "DES");
		return symUtil.DesEncryptString(str, key);
	}

	/**
	 * DES解密
	 * 
	 * @param str 密文
	 * @param key 密钥
	 * @return
	 */
	public String DesDecrypt(String str, String key) {
		key = keyUtil.getKeyHex(key, "DES");
		return symUtil.DesDecryptString(str, key);
	}

	/**
	 * 类入口函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String key = "123456";
		String str = "123456";
		String aesEnc = "h3Jzi0Yp/WEnnnptGPSk9A==";
		String desEnc = "v2idKpeQ4lc=";
		SymSimple symSimple = new SymSimple();
		System.out.println(symSimple.AesEncrypt(str, key));
		System.out.println(symSimple.AesDecrypt(aesEnc, key));
		System.out.println(symSimple.DesEncrypt(str, key));
		System.out.println(symSimple.DesDecrypt(desEnc, key));
	}

}
