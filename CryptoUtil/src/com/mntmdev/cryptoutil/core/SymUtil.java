package com.mntmdev.cryptoutil.core;

import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.mntmdev.cryptoutil.common.EncodingUtil;

/**
 * �ԳƼ��ܸ�����(�������ڶԳƼ��ܵĴ���ʽ����ͨ��)
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
	 * �Զ���������ʹ�ö�������Կ���жԳƼ���
	 * 
	 * @param type  ��������
	 * @param byArr ԭ��
	 * @param byKey ��Կ
	 * @return ���ܽ��
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
	 * �Զ���������ʹ�ö�������Կ���жԳƽ���
	 * 
	 * @param type  ��������
	 * @param byArr ����
	 * @param byKey ��Կ
	 * @return ���ܽ��
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
	 * �Զ���������ʹ�ö�������Կ����AES����
	 * 
	 * @param byArr ����
	 * @param byKey ��Կ
	 * @return
	 */
	public byte[] AesEncryptByte(byte[] byArr, byte[] byKey) {
		byte[] byEnc = EncryptByte("AES", byArr, byKey);
		return byEnc;
	}

	/**
	 * ���ַ���ʹ��16����AES��Կ���м���
	 * 
	 * @param str ԭ��
	 * @param key ��Կ(16�����ַ���)
	 * @return ���ĵ�base64
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
	 * �Զ���������ʹ�ö�������Կ����AES����
	 * 
	 * @param byArr ����
	 * @param byKey ��Կ
	 * @return
	 */
	public byte[] AesDecryptByte(byte[] byArr, byte[] byKey) {
		byte[] byDec = DecryptByte("AES", byArr, byKey);
		return byDec;
	}

	/**
	 * ������base64�ַ���ʹ��16������Կ����AES����
	 * 
	 * @param str ���ĵ�base64
	 * @param key ��Կ��16����
	 * @return ԭ���ַ���
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
	 * �Զ���������ʹ�ö�������Կ����DES����
	 * 
	 * @param byArr ����
	 * @param byKey ��Կ
	 * @return
	 */
	public byte[] DesEncryptByte(byte[] byArr, byte[] byKey) {
		byte[] byEnc = EncryptByte("DES", byArr, byKey);
		return byEnc;
	}

	/**
	 * ���ַ���ʹ��16����AES��Կ���м���
	 * 
	 * @param str ԭ��
	 * @param key ��Կ(16�����ַ���)
	 * @return ���ĵ�base64
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
	 * �Զ���������ʹ�ö�������Կ����DES����
	 * 
	 * @param byArr ����
	 * @param byKey ��Կ
	 * @return
	 */
	public byte[] DesDecryptByte(byte[] byArr, byte[] byKey) {
		byte[] byDec = DecryptByte("DES", byArr, byKey);
		return byDec;
	}

	/**
	 * ������base64�ַ���ʹ��16������Կ����DES����
	 * 
	 * @param str ���ĵ�base64
	 * @param key ��Կ��16����
	 * @return ԭ���ַ���
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
		String aesEnc = "iqPUIBXFMI4Vsw7w//ka9A==";
		String desEnc = "otEvy/NDSnY=";
		SymUtil util = new SymUtil();
		System.out.println("AES���ܺͽ���");
		System.out.println(util.AesEncryptString(str, aesKey));
		System.out.println(util.AesDecryptString(aesEnc, aesKey));
		System.out.println("DES���ܺͽ���");
		System.out.println(util.DesEncryptString(str, desKey));
		System.out.println(util.DesDecryptString(desEnc, desKey));
	}

}
