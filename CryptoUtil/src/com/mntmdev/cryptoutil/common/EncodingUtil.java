package com.mntmdev.cryptoutil.common;

import java.util.Base64;

/**
 * ������븨����
 * 
 * @author MNTMDEV
 *
 */
public class EncodingUtil {
	/**
	 * ������������ת��Ϊ16���Ʊ�ʾ���ַ���
	 * 
	 * @param byArr
	 * @return
	 */
	public String Byte2Hex(byte[] byArr) {
		String hex = "0123456789ABCDEF";
		String ret = "";
		// ���ܺ���ַ���
		try {
			if (byArr == null) {
				return null;
			}
			for (int i = 0; i < byArr.length; i++) {
				byte by = byArr[i];
				// ȡ�ø�λ�͵�λ
				int h = 0x0f & (by >>> 4);
				int l = 0x0f & by;
				ret += hex.substring(h, h + 1);
				ret += hex.substring(l, l + 1);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��16�����ַ���תΪ�����Ƹ�ʽ(������)
	 * 
	 * @param str
	 * @return
	 */
	public byte[] Hex2Byte(String str) {
		char[] chArr = str.toUpperCase().toCharArray();
		if (chArr.length % 2 != 0) {
			return null;
		}
		byte[] byArr = new byte[chArr.length / 2];
		int i = 0;
		for (char ch : chArr) {
			int index = i / 2;
			int bit = (i % 2 == 0) ? 4 : 0;
			int val = 0;
			if ((ch >= 'A') && (ch <= 'F')) {
				val = ch - 'A' + 10;
			} else if ((ch >= '0') && (ch <= '9')) {
				val = ch - '0';
			} else {
				return null;
			}
			val = val << bit;
			byArr[index] = (byte) (byArr[index] | val);
			i++;
		}
		return byArr;
	}

	/**
	 * �������ƽ���base64����
	 * 
	 * @param byArr
	 * @return
	 */
	public String Base64Encode(byte[] byArr) {
		try {
			String ret = Base64.getEncoder().encodeToString(byArr);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���ַ�������base64����
	 * 
	 * @param str
	 * @return
	 */
	public String Base64Encode(String str) {
		try {
			byte[] byArr = str.getBytes("utf-8");
			String ret = Base64Encode(byArr);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ȡĳbase64�������Ķ�����
	 * 
	 * @param str
	 * @return
	 */
	public byte[] Base64DecodeByte(String str) {
		return Base64.getDecoder().decode(str);
	}

	/**
	 * ��ȡĳbase64���������ַ���
	 * 
	 * @param str
	 * @return
	 */
	public String Base64Decode(String str) {
		byte[] byArr = Base64DecodeByte(str);
		try {
			String ret = new String(byArr, "utf-8");
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����Դ���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "123456";
		String enc = "MTIzNDU2";
		EncodingUtil util = new EncodingUtil();

		System.out.println(util.Base64Encode(str));
		System.out.println(util.Base64Decode(enc));

		String hex = util.Byte2Hex(str.getBytes());
		System.out.println(hex);
		System.out.println(new String(util.Hex2Byte(hex)));

	}

}
