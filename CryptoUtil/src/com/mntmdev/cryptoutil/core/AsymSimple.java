package com.mntmdev.cryptoutil.core;

import com.mntmdev.cryptoutil.common.AsymKeyPair;
import com.mntmdev.cryptoutil.common.KeyUtil;

/**
 * 不对称加密的简化版
 * 
 * @author MNTMDEV
 *
 */
public class AsymSimple {
	private AsymUtil asymUtil;

	public AsymSimple() {
		super();
		asymUtil = new AsymUtil();
	}

	/**
	 * RSA加密
	 * 
	 * @param flag true表示使用公钥 false表示使用私钥
	 * @param str  待加密字符串
	 * @param key  密钥
	 * @return 密文
	 */
	public String RsaEncrypt(boolean flag, String str, String key) {
		return asymUtil.EncryptString(flag, "RSA", str, key);
	}

	/**
	 * RSA解密
	 * 
	 * @param flag true表示使用公钥 false表示使用私钥
	 * @param str  待解密字符串
	 * @param key  密钥
	 * @return 明文
	 */
	public String RsaDecrypt(boolean flag, String str, String key) {
		return asymUtil.DecryptString(flag, "RSA", str, key);
	}

	/**
	 * RSA加密(只能公钥加密)
	 * 
	 * @param str
	 * @param key 密钥
	 * @return 密文
	 */
	public String RsaEncrypt(String str, String key) {
		return RsaEncrypt(true, str, key);
	}

	/**
	 * RSA解密(只能私钥解密)
	 * 
	 * @param str 待解密字符串
	 * @param key 密钥
	 * @return 明文
	 */
	public String RsaDecrypt(String str, String key) {
		return RsaDecrypt(false, str, key);
	}

	/**
	 * 类入口函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AsymSimple asymSimple = new AsymSimple();
		String str = "123456";
		KeyUtil keyUtil = new KeyUtil();
		AsymKeyPair keyPair = keyUtil.getKeyPair("RSA", 1024);
		String pub = keyPair.getPubString();
		String pri = keyPair.getPriString();
		String enc = asymSimple.RsaEncrypt(str, pub);
		System.out.println("RSA加密");
		System.out.println(enc);
		System.out.println("RSA解密");
		System.out.println(asymSimple.RsaDecrypt(enc, pri));
	}

}
