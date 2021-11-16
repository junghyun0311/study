package com.example.demo.Security;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 암복호화 UTIL 클레스.
 * 
 * @author Admin
 *
 */
@Component
public class KnsfEncUtil {

	
	private String AES_INFO="test123";

	/**
	 * AES256 복호화 처리.
	 * 
	 * @param param 복호화 대상 문자열
	 * @return 복호화 처리후 문자열
	 */
	public String aesDecrypt(String param) {
		return AESCrypto.decrypt(AES_INFO, param);
	}

	/**
	 * AES256 암호화 처리.
	 * 
	 * @param param 암호화 대상 문자열
	 * @return 암호화 처리후 문자열
	 */
	public String aesEncrypt(String param) {

		return AESCrypto.encrypt(AES_INFO, param);
	}

	public String shaEncrypt(String param) {
		String toReturn = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.reset();
			messageDigest.update(param.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, messageDigest.digest()));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return toReturn;
	}
}
