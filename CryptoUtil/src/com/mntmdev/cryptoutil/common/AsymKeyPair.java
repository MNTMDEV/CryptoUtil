package com.mntmdev.cryptoutil.common;

/**
 * 不对称加密的密钥对
 * 
 * @author MNTMDEV
 *
 */
public class AsymKeyPair {
	private byte[] byPub;
	private byte[] byPri;

	/**
	 * 获取密钥对中的公钥
	 * 
	 * @return
	 */
	public byte[] getByPub() {
		return byPub;
	}

	/**
	 * 设置密钥对中的公钥
	 * 
	 * @param byPub
	 */
	public void setByPub(byte[] byPub) {
		this.byPub = byPub;
	}

	/**
	 * 获取密钥对中的私钥
	 * 
	 * @return
	 */
	public byte[] getByPri() {
		return byPri;
	}

	/**
	 * 设置密钥对中的私钥
	 * 
	 * @param byPri
	 */
	public void setByPri(byte[] byPri) {
		this.byPri = byPri;
	}

	/**
	 * 获取密钥对中base64的公钥
	 * 
	 * @return
	 */
	public String getPubString() {
		EncodingUtil util = new EncodingUtil();
		return util.Base64Encode(byPub);
	}

	/**
	 * 获取密钥对中base64的私钥
	 * 
	 * @return
	 */
	public String getPriString() {
		EncodingUtil util = new EncodingUtil();
		return util.Base64Encode(byPri);
	}

	/**
	 * 设置密钥对中base64的公钥
	 * 
	 * @param str base64的密钥
	 */
	public void setPubString(String str) {
		EncodingUtil util = new EncodingUtil();
		byPub = util.Base64DecodeByte(str);
	}

	/**
	 * 设置密钥对中base64的私钥
	 * 
	 * @param str base64的密钥
	 */
	public void setPriString(String str) {
		EncodingUtil util = new EncodingUtil();
		byPri = util.Base64DecodeByte(str);
	}

}
