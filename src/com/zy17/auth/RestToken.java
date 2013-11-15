package com.zy17.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-5 Time: 上午11:17 To
 * change this template use File | Settings | File Templates.
 */
public class RestToken extends UsernamePasswordAuthenticationToken {

	public RestToken(String key, String credentials) {
		super(key, credentials);
	}

	public RestToken(String key, String credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(key, credentials, authorities);
	}

	public String getKey() {
		return (String) super.getPrincipal();
	}

	public String getCredentials() {
		return (String) super.getCredentials();
	}
}
