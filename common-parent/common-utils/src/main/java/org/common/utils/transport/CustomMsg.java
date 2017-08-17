package org.common.utils.transport;

public class CustomMsg {
	public static final int HEADER_SIZE = 4;
	// private byte type;
	//
	// private byte flag;

	private int length;

	private String body;

	public CustomMsg(String body) {
		this.length = body.length();
		this.body = body;
	}

	public CustomMsg(int length, String body) {
		// this.type = type;
		// this.flag = flag;
		this.length = length;
		this.body = body;
	}

	// public byte getType() {
	// return type;
	// }
	//
	// public void setType(byte type) {
	// this.type = type;
	// }
	//
	// public byte getFlag() {
	// return flag;
	// }
	//
	// public void setFlag(byte flag) {
	// this.flag = flag;
	// }

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
