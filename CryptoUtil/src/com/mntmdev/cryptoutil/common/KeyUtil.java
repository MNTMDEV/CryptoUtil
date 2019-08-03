package com.mntmdev.cryptoutil.common;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * ��Կ������
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
	 * ������ɶԳ���Կ(�����Ƹ�ʽ)
	 * 
	 * @param type ������AES DES
	 * @return �����Ƹ�ʽ����Կ
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
	 * ʹ�ö����Ƹ�ʽ������������Կ
	 * 
	 * @param bySeed ����
	 * @param type   ������AES DES
	 * @return �����Ƹ�ʽ����Կ
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
	 * ������ɶԳ���Կ(16�����ַ�����ʾ)
	 * 
	 * @param type   ������AES DES
	 * @param length ��Կ����
	 * @return 16���Ƹ�ʽ����Կ
	 */
	public String generateKeyHex(String type, int length) {
		byte[] byArr = generateKey(type, length);
		if (byArr == null)
			return null;
		String ret = encodingUtil.Byte2Hex(byArr);
		return ret;
	}

	/**
	 * ʹ���ַ�����������16���Ƹ�ʽ����Կ
	 * 
	 * @param seed ����
	 * @param type ������AES DES
	 * @return 16���Ƹ�ʽ����Կ
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
	 * ������ɶԳ���Կ(base64�ַ�����ʾ)
	 * 
	 * @deprecated �Գ���Կһ�㲻��base64
	 * @param type   ������AES DES
	 * @param length ��Կ����
	 * @return base64��ʽ����Կ
	 */
	public String generateKeyString(String type, int length) {
		byte[] byArr = generateKey(type, length);
		if (byArr == null)
			return null;
		String ret = encodingUtil.Base64Encode(byArr);
		return ret;
	}

	/**
	 * ʹ���ַ�����������base64��ʽ����Կ
	 * 
	 * @deprecated �Գ���Կһ�㲻��base64
	 * @param seed ����
	 * @param type ������AES DES
	 * @return base64��ʽ����Կ
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
	 * ���������Կ��
	 * 
	 * @param type   �������� RSA DSA
	 * @param length ��Կ����
	 * @return ��Կ��
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
	 * ʹ������������Կ��
	 * 
	 * @param bySeed �����Ƹ�ʽ������
	 * @param type   �������� RSA DSA
	 * @param length ��Կ����
	 * @return ��Կ��
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
	 * ʹ������������Կ��
	 * 
	 * @param seed   �ַ�����ʽ������
	 * @param type   �������� RSA DSA
	 * @param length ��Կ����
	 * @return ��Կ��
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
	 * ����ں���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String seed = "123456";
		KeyUtil util = new KeyUtil();
		System.out.println("������������ɵĽ��");
		System.out.println(util.generateKeyHex("AES", 128));
		System.out.println(util.generateKeyHex("DES", 56));
		System.out.println(util.generateKeyString("AES", 128));
		System.out.println(util.generateKeyString("DES", 56));
		System.out.println("�������ɵ��ǹ̶�����Կ");
		System.out.println(util.getKeyHex(seed, "AES"));
		System.out.println(util.getKeyHex(seed, "DES"));
//		System.out.println(util.getKeyString(seed, "AES"));
//		System.out.println(util.getKeyString(seed, "DES"));
		System.out.println("�����ǲ��ԳƼ�����Կ�Ե��������");
		AsymKeyPair asymKeyPair = util.getKeyPair("RSA", 1024);
		System.out.println(asymKeyPair.getPubString());
		System.out.println(asymKeyPair.getPriString());
		asymKeyPair = util.getKeyPair("DSA", 1024);
		System.out.println(asymKeyPair.getPubString());
		System.out.println(asymKeyPair.getPriString());
		System.out.println("�����ǲ��ԳƼ�����Կ�ԵĹ̶�����");
		asymKeyPair = util.getKeyPair("123456", "RSA", 1024);
		System.out.println(asymKeyPair.getPubString());
		System.out.println(asymKeyPair.getPriString());
	}

}
