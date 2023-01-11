package com.modubiz.modu.common.util;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ConvertUtil {

	private final static String CHARSET = Charset.defaultCharset().displayName();
	
	/********************************************************/
	/* Bytes And String										*/
	/********************************************************/
	
	public static byte[] StringToBytes(String strData, String charset) {
		if(charset==null) charset = CHARSET;
		return strData.getBytes(Charset.forName(charset));
	}
	
	public static String BytesToString(byte[] bytes, String charset) {
		if(charset==null) charset = CHARSET;
		return new String(bytes, Charset.forName(charset));
	}

	/********************************************************/
	/* Base64 Encrypt And Decrypt							*/
	/********************************************************/
	
	public static String Base64EncryptToString(byte[] bytes, String charset) {
		if(charset==null) charset = CHARSET;
		return BytesToString(Base64EncryptToBytes(bytes), charset);
	}
	
	public static String Base64EncryptToString(String strData, String charset) {
		if(charset==null) charset = CHARSET;
		return BytesToString(Base64EncryptToBytes(StringToBytes(strData, charset)), charset);
	}
	
	public static byte[] Base64EncryptToBytes(byte[] bytes) {
		return Base64.getEncoder().encode(bytes);
	}
	
	public static byte[] Base64DecryptToBytes(String strData, String charset) {
		if(strData==null) return null;
		if(charset==null) charset = CHARSET;
		return Base64DecryptToBytes(StringToBytes(strData,charset));
	}
	
	public static String Base64DecryptToString(String strData, String charset) {
		if(strData==null) return null;
		if(charset==null) charset = CHARSET;
		return BytesToString(Base64DecryptToBytes(StringToBytes(strData,charset)), charset);
	}
	
	public static byte[] Base64DecryptToBytes(byte[] bytes) {
		return Base64.getDecoder().decode(bytes);
	}
	
	/********************************************************/
	/* HexString And Bytes 									*/
	/********************************************************/
	
	public static byte[] HexStringToBytes(String s) {
		return HexStringToBytes(s, "");
	}

	public static byte[] HexStringToBytes(String s, String delimeter) {
		s = s.replace(delimeter, "");
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
	  
	public static String BytesToHexString(byte[] b) {
		return BytesToHexString(b, "");
	}

	public static String BytesToHexString(byte[] b, String delimeter) {
		int len = b.length;
		StringBuilder data = new StringBuilder();

		for (int i = 0; i < len; i++) {
			data.append(Integer.toHexString((b[i] >> 4) & 0xf));
			data.append(Integer.toHexString(b[i] & 0xf));
			data.append(delimeter);
		}
		return data.toString().toUpperCase();
	}
	
	/********************************************************/
	/* String To UTF-8, EUC-KR								*/
	/********************************************************/
	
	public static String StringToUTF8(String strData) throws Exception {
		return (new String(strData.getBytes(Charset.forName("utf-8")), "utf-8"));
	}
	
	public static String StringToEUCKR(String strData) throws Exception {
		return (new String(strData.getBytes(Charset.forName("euc-kr")), "euc-kr"));
	}
	
	/********************************************************/
	/* Map To UrlEncoding QueryString						*/
	/********************************************************/
	
	public static String MapToUrlEncoding(Map<String,String> mapData, String charset) throws Exception {
		StringBuilder sb = new StringBuilder();
		for(HashMap.Entry<String, String> e : mapData.entrySet()){
			if(sb.length() > 0){
				sb.append('&');
			}
			sb.append(URLEncoder.encode(e.getKey(), charset)).append('=').append(URLEncoder.encode(e.getValue(),charset));
		}
		return sb.toString();
	}
	
	/********************************************************/
	/* Map To Url QueryString								*/
	/********************************************************/
	
	public static String MapToUrlString(Map<String,String> mapData) throws Exception {
		StringBuilder sb = new StringBuilder();
		for(HashMap.Entry<String, String> e : mapData.entrySet()){
			if(sb.length() > 0){
				sb.append('&');
			}
			sb.append(e.getKey()).append('=').append(e.getValue());
		}
		return sb.toString();
	}
	
}
