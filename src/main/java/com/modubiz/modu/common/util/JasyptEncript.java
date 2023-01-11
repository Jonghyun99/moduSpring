package com.modubiz.modu.common.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptEncript {

	public static void main(String[] args) {
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("happy1125");      //암호화 키
        jasypt.setAlgorithm("PBEWithMD5AndDES");
 
 
        String encryptedText = jasypt.encrypt("");    //암호화
        String plainText = jasypt.decrypt(encryptedText);  //복호화
        System.out.println("encryptedText = " + encryptedText);
        System.out.println("plainText = " + plainText);
 
	}

}
