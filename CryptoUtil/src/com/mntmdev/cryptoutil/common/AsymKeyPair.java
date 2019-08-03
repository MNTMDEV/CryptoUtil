package com.mntmdev.cryptoutil.common;

/**
 * ���ԳƼ��ܵ���Կ��
 * 
 * @author MNTMDEV
 *
 */
public class AsymKeyPair {
	private byte[] byPub;
	private byte[] byPri;

	/**
	 * ��ȡ��Կ���еĹ�Կ
	 * 
	 * @return
	 */
	public byte[] getByPub() {
		return byPub;
	}

	/**
	 * ������Կ���еĹ�Կ
	 * 
	 * @param byPub
	 */
	public void setByPub(byte[] byPub) {
		this.byPub = byPub;
	}

	/**
	 * ��ȡ��Կ���е�˽Կ
	 * 
	 * @return
	 */
	public byte[] getByPri() {
		return byPri;
	}

	/**
	 * ������Կ���е�˽Կ
	 * 
	 * @param byPri
	 */
	public void setByPri(byte[] byPri) {
		this.byPri = byPri;
	}

	/**
	 * ��ȡ��Կ����base64�Ĺ�Կ
	 * 
	 * @return
	 */
	public String getPubString() {
		EncodingUtil util = new EncodingUtil();
		return util.Base64Encode(byPub);
	}

	/**
	 * ��ȡ��Կ����base64��˽Կ
	 * 
	 * @return
	 */
	public String getPriString() {
		EncodingUtil util = new EncodingUtil();
		return util.Base64Encode(byPri);
	}

	/**
	 * ������Կ����base64�Ĺ�Կ
	 * 
	 * @param str base64����Կ
	 */
	public void setPubString(String str) {
		EncodingUtil util = new EncodingUtil();
		byPub = util.Base64DecodeByte(str);
	}

	/**
	 * ������Կ����base64��˽Կ
	 * 
	 * @param str base64����Կ
	 */
	public void setPriString(String str) {
		EncodingUtil util = new EncodingUtil();
		byPri = util.Base64DecodeByte(str);
	}

}
