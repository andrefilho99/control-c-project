package com.andrefilho99.controlCProject.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class HashUtils {
	
	public String stringToSha1(String string){
		
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] result = messageDigest.digest(string.getBytes());
		StringBuffer stringBuffer = new StringBuffer();
		
		for(int i = 0; i < result.length; i++) 
			stringBuffer.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		
		return stringBuffer.toString();
	}
}
