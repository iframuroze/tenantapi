package com.iframuroze.tenant.tenantapi.exceptions;

/**
 * Para ser usado em caso de confirmacao de email*/
public class ConfirmationEmailException extends RuntimeException{
	
	private static final long serialVersionUID = 9104179882131870135L;

	public ConfirmationEmailException(String message){
		super(message);
	}
}
