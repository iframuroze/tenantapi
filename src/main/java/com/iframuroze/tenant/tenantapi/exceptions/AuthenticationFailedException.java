package com.iframuroze.tenant.tenantapi.exceptions;

/**
 * Para ser usado em caso de confirmacao de email*/
public class AuthenticationFailedException extends RuntimeException{

	private static final long serialVersionUID = -3649711298632597175L;

	public AuthenticationFailedException(String message){
		super(message);
	}
}
