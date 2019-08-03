package com.mntmdev.cryptoutil.core;

import java.security.MessageDigest;

import com.mntmdev.cryptoutil.common.EncodingUtil;

/**
 * ��ϣ������(����������У��)
 * 
 * @author MNTMDEV
 *
 */
public class HashUtil {
	private EncodingUtil encodingUtil;

	public HashUtil() {
		super();
		encodingUtil = new EncodingUtil();
	}

	/**
	 * ������MD5����
	 * 
	 * @param byArr
	 * @return
	 */
	public byte[] EncryptMD5(byte[] byArr) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] ret = md5.digest(byArr);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ַ���MD5����
	 * 
	 * @param str
	 * @return
	 */
	public String EncryptMD5(String str) {
		// ���ܺ���ַ���
		try {
			byte[] ba = EncryptMD5(str.getBytes("utf-8"));
			String ret = encodingUtil.Byte2Hex(ba);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ������SHA-1����
	 * 
	 * @param byArr
	 * @return
	 */
	public byte[] EncryptSHA1(byte[] byArr) {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			byte[] ret = sha1.digest(byArr);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ַ���SHA-1����
	 * 
	 * @param str
	 * @return
	 */
	public String EncryptSHA1(String str) {
		try {
			byte[] ba = EncryptSHA1(str.getBytes("utf-8"));
			String ret = encodingUtil.Byte2Hex(ba);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ������SHA-256����
	 * 
	 * @param byArr
	 * @return
	 */
	public byte[] EncryptSHA256(byte[] byArr) {
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] ret = sha256.digest(byArr);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ַ���SHA-256����
	 * 
	 * @param str
	 * @return
	 */
	public String EncryptSHA256(String str) {
		try {
			byte[] ba = EncryptSHA256(str.getBytes("utf-8"));
			String ret = encodingUtil.Byte2Hex(ba);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����Ժ���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HashUtil util = new HashUtil();
		String str = "123456";
		System.out.println(util.EncryptMD5(str));
		System.out.println(util.EncryptSHA1(str));
		System.out.println(util.EncryptSHA256(str));
	}

}
