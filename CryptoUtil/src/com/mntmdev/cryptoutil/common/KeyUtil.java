package com.mntmdev.cryptoutil.common;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * 密钥辅助类
 * 
 * @author MNTMDEV
 *
 */
public class KeyUtil {
	private EncodingUtil encodingUtil;

	public KeyUtil() {
		super();
		encodingUtil = new EncodingUtil();
	}

	/**
	 * 随机生成对称密钥(二进制格式)
	 * 
	 * @param type 可以是AES DES
	 * @return 二进制格式的密钥
	 */
	public byte[] generateKey(String type, int length) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(type);
			keyGenerator.init(length);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byArr = secretKey.getEncoded();
			return byArr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用二进制格式的种子生成密钥
	 * 
	 * @param bySeed 种子
	 * @param type   可以是AES DES
	 * @return 二进制格式的密钥
	 */
	public byte[] getKey(byte[] bySeed, String type) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(type);
			keyGenerator.init(new SecureRandom(bySeed));
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byArr = secretKey.getEncoded();
			return byArr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 随机生成对称密钥(16进制字符串表示)
	 * 
	 * @param type   可以是AES DES
	 * @param length 密钥长度
	 * @return 16进制格式的密钥
	 */
	public String generateKeyHex(String type, int length) {
		byte[] byArr = generateKey(type, length);
		if (byArr == null)
			return null;
		String ret = encodingUtil.Byte2Hex(byArr);
		return ret;
	}

	/**
	 * 使用字符串种子生成16进制格式的密钥
	 * 
	 * @param seed 种子
	 * @param type 可以是AES DES
	 * @return 16进制格式的密钥
	 */
	public String getKeyHex(String seed, String type) {
		try {
			byte[] bySeed = seed.getBytes("utf-8");
			byte[] byArr = getKey(bySeed, type);
			String ret = encodingUtil.Byte2Hex(byArr);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 随机生成对称密钥(base64字符串表示)
	 * 
	 * @param type   可以是AES DES
	 * @param length 密钥长度
	 * @return base64格式的密钥
	 */
	public String generateKeyString(String type, int length) {
		byte[] byArr = generateKey(type, length);
		if (byArr == null)
			return null;
		String ret = encodingUtil.Base64Encode(byArr);
		return ret;
	}

	/**
	 * 使用字符串种子生成base64格式的密钥
	 * 
	 * @param seed 种子
	 * @param type 可以是AES DES
	 * @return base64格式的密钥
	 */
	public String getKeyString(String seed, String type) {
		try {
			byte[] bySeed = seed.getBytes("utf-8");
			byte[] byArr = getKey(bySeed, type);
			String ret = encodingUtil.Base64Encode(byArr);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成密钥对
	 * 
	 * @param type   加密类型 RSA DSA
	 * @param length 密钥长度
	 * @return 密钥对
	 */
	public AsymKeyPair getKeyPair(String type, int length) {
		try {
			AsymKeyPair ret = new AsymKeyPair();
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(type);
			keyPairGenerator.initialize(length);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
			byte[] byPub = publicKey.getEncoded();
			byte[] byPri = privateKey.getEncoded();
			ret.setByPub(byPub);
			ret.setByPri(byPri);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用种子生成密钥对
	 * 
	 * @param bySeed 二进制格式的种子
	 * @param type   加密类型 RSA DSA
	 * @param length 密钥长度
	 * @return 密钥对
	 */
	public AsymKeyPair getKeyPair(byte[] bySeed, String type, int length) {
		try {
			AsymKeyPair ret = new AsymKeyPair();
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(type);
			keyPairGenerator.initialize(length, new SecureRandom(bySeed));
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
			byte[] byPub = publicKey.getEncoded();
			byte[] byPri = privateKey.getEncoded();
			ret.setByPub(byPub);
			ret.setByPri(byPri);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用种子生成密钥对
	 * 
	 * @param seed   字符串格式的种子
	 * @param type   加密类型 RSA DSA
	 * @param length 密钥长度
	 * @return 密钥对
	 */
	public AsymKeyPair getKeyPair(String seed, String type, int length) {
		try {
			byte[] bySeed = seed.getBytes("utf-8");
			return getKeyPair(bySeed, type, length);
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
		String seed = "123456";
		KeyUtil util = new KeyUtil();
		System.out.println("以下是随机生成的结果");
		System.out.println(util.generateKeyHex("AES", 128));
		System.out.println(util.generateKeyHex("DES", 56));
		System.out.println(util.generateKeyString("AES", 128));
		System.out.println(util.generateKeyString("DES", 56));
		System.out.println("以下生成的是固定的密钥");
		System.out.println(util.getKeyHex(seed, "AES"));
		System.out.println(util.getKeyHex(seed, "DES"));
		System.out.println(util.getKeyString(seed, "AES"));
		System.out.println(util.getKeyString(seed, "DES"));
		System.out.println("以下是不对称加密密钥对的随机生成");
		AsymKeyPair asymKeyPair = util.getKeyPair("RSA", 1024);
		System.out.println(asymKeyPair.getPubString());
		System.out.println(asymKeyPair.getPriString());
		asymKeyPair = util.getKeyPair("DSA", 1024);
		System.out.println(asymKeyPair.getPubString());
		System.out.println(asymKeyPair.getPriString());
		System.out.println("以下是不对称加密密钥对的固定生成");
		asymKeyPair = util.getKeyPair("123456", "RSA", 1024);
		System.out.println(asymKeyPair.getPubString());
		System.out.println(asymKeyPair.getPriString());
	}

}
