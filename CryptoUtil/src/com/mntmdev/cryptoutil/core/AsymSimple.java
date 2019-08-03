package com.mntmdev.cryptoutil.core;

import com.mntmdev.cryptoutil.common.AsymKeyPair;
import com.mntmdev.cryptoutil.common.KeyUtil;

/**
 * ���ԳƼ��ܵļ򻯰�
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
	 * RSA����
	 * 
	 * @param flag true��ʾʹ�ù�Կ false��ʾʹ��˽Կ
	 * @param str  �������ַ���
	 * @param key  ��Կ
	 * @return ����
	 */
	public String RsaEncrypt(boolean flag, String str, String key) {
		return asymUtil.EncryptString(flag, "RSA", str, key);
	}

	/**
	 * RSA����
	 * 
	 * @param flag true��ʾʹ�ù�Կ false��ʾʹ��˽Կ
	 * @param str  �������ַ���
	 * @param key  ��Կ
	 * @return ����
	 */
	public String RsaDecrypt(boolean flag, String str, String key) {
		return asymUtil.DecryptString(flag, "RSA", str, key);
	}

	/**
	 * RSA����(ֻ�ܹ�Կ����)
	 * 
	 * @param str
	 * @param key ��Կ
	 * @return ����
	 */
	public String RsaEncrypt(String str, String key) {
		return RsaEncrypt(true, str, key);
	}

	/**
	 * RSA����(ֻ��˽Կ����)
	 * 
	 * @param str �������ַ���
	 * @param key ��Կ
	 * @return ����
	 */
	public String RsaDecrypt(String str, String key) {
		return RsaDecrypt(false, str, key);
	}

	/**
	 * ����ں���
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
		System.out.println("RSA����");
		System.out.println(enc);
		System.out.println("RSA����");
		System.out.println(asymSimple.RsaDecrypt(enc, pri));
	}

}
