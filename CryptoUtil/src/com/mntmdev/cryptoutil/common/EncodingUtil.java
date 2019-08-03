package com.mntmdev.cryptoutil.common;

import java.util.Base64;

/**
 * 可逆编码辅助类
 * 
 * @author MNTMDEV
 *
 */
public class EncodingUtil {
	/**
	 * 将二进制数组转化为16进制表示的字符串
	 * 
	 * @param byArr
	 * @return
	 */
	public String Byte2Hex(byte[] byArr) {
		String hex = "0123456789ABCDEF";
		String ret = "";
		// 加密后的字符串
		try {
			if (byArr == null) {
				return null;
			}
			for (int i = 0; i < byArr.length; i++) {
				byte by = byArr[i];
				// 取得高位和低位
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
	 * 将16进制字符串转为二进制格式
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
				val = ch - 'A';
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
	 * 将二进制进行base64编码
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
	 * 将字符串进行base64编码
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
	 * 获取某base64结果解码的二进制
	 * 
	 * @param str
	 * @return
	 */
	public byte[] Base64DecodeByte(String str) {
		return Base64.getDecoder().decode(str);
	}

	/**
	 * 获取某base64结果解码的字符串
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
	 * 类测试代码
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "123456";
		String enc = "MTIzNDU2";
		EncodingUtil util = new EncodingUtil();

		System.out.println(util.Base64Encode(str));
		System.out.println(util.Base64Decode(enc));
	}

}
