package com.zy17.auth;

import org.springframework.security.core.AuthenticationException;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-9 Time: 下午4:46 To
 * change this template use File | Settings | File Templates.
 */
public class RestAuthenticationException extends AuthenticationException {

	public RestAuthenticationException(String msg) {
		super(msg);
	}

	public RestAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}
}
