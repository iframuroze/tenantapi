package com.iframuroze.tenant.tenantapi.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Util {
	
	/**
	 * Utilitario para criptografar uma string.
	 * deve ser usada ao inserir a string na base e na hora da leitura
	 * */
	
	public static String md5(String senha) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes()));
			return hash.toString(16);
		} catch (Exception e) {
			return "";
		}
	}

}
