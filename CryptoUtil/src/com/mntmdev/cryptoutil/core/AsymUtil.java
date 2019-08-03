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
	 * ���ԳƼ���
	 * 
	 * @param flag  trueΪʹ�ù�Կ��falseΪʹ��˽Կ
	 * @param type  �������� RSA
	 * @param byArr ԭ�Ķ�����
	 * @param byKey ��Կ������
	 * @return ���Ķ�����
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
	 * ���ԳƼ��ܵ��ַ�����
	 * 
	 * @param flag trueΪʹ�ù�Կ��falseΪʹ��˽Կ
	 * @param type �������� RSA
	 * @param str  ԭ���ַ���
	 * @param key  ��Կbase64
	 * @return ����base64
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
	 * ���Գƽ���
	 * 
	 * @param flag  trueΪʹ�ù�Կ��falseΪʹ��˽Կ
	 * @param type  �������� RSA
	 * @param byArr ԭ�Ķ�����
	 * @param byKey ��Կ������
	 * @return ���Ķ�����
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
	 * ���Գƽ����ַ�����
	 * 
	 * @param flag trueΪʹ�ù�Կ��falseΪʹ��˽Կ
	 * @param type �������� RSA
	 * @param str  ����base64
	 * @param key  ��Կbase64
	 * @return �����ַ���
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
	 * ����ں���
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
		System.out.println("�����ǲ��Գ��㷨�Ĺ�Կ����˽Կ����");
		System.out.println(enc);
		System.out.println(util.DecryptString(false, "RSA", enc, pri));
		System.out.println("�����ǲ��Գ��㷨�Ĺ�Կ����˽Կ����");
		enc = util.EncryptString(false, "RSA", str, pri);
		System.out.println(enc);
		System.out.println(util.DecryptString(true, "RSA", enc, pub));
	}

}
