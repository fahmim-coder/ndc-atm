package com.leunca.ndc;

public class NDCParameter {
	public static final byte FIELD_SEPARATOR = 0x1C;
	public static final byte GROUP_SEPARATOR = 0x1D;
	public static byte[] messageHeader = ("IDJT00001ATM" + "FFFFFFFF").getBytes();               // https://id.wikipedia.org/wiki/ISO_3166-2:ID     https://en.wikipedia.org/wiki/IPv4
	public static int messageHeaderLength = messageHeader.length;
	
	
}
