package com.mntmdev.cryptoutil.core;

import com.mntmdev.cryptoutil.common.KeyUtil;

/**
 * �򻯰�ԳƼ�����(������Կ��ԭ�ģ�ֱ�Ӹ������)
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
	 * AES����
	 * 
	 * @param str ԭ��
	 * @param key ��Կ
	 * @return
	 */
	public String AesEncrypt(String str, String key) {
		key = keyUtil.getKeyHex(key, "AES");
		return symUtil.AesEncryptString(str, key);
	}

	/**
	 * AES����
	 * 
	 * @param str ����
	 * @param key ��Կ
	 * @return
	 */
	public String AesDecrypt(String str, String key) {
		key = keyUtil.getKeyHex(key, "AES");
		return symUtil.AesDecryptString(str, key);
	}

	/**
	 * DES����
	 * 
	 * @param str ԭ��
	 * @param key ��Կ
	 * @return
	 */
	public String DesEncrypt(String str, String key) {
		key = keyUtil.getKeyHex(key, "DES");
		return symUtil.DesEncryptString(str, key);
	}

	/**
	 * DES����
	 * 
	 * @param str ����
	 * @param key ��Կ
	 * @return
	 */
	public String DesDecrypt(String str, String key) {
		key = keyUtil.getKeyHex(key, "DES");
		return symUtil.DesDecryptString(str, key);
	}

	/**
	 * ����ں���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String key = "123456";
		String str = "123456";
		String aesEnc = "OQXvThGuGqlPHR9GA/i1tg==";
		String desEnc = "Y5ukuVYyc0s=";
		SymSimple symSimple = new SymSimple();
		System.out.println(symSimple.AesEncrypt(str, key));
		System.out.println(symSimple.AesDecrypt(aesEnc, key));
		System.out.println(symSimple.DesEncrypt(str, key));
		System.out.println(symSimple.DesDecrypt(desEnc, key));
	}

}
