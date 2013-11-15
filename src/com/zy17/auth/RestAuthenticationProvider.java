package com.zy17.auth;

import com.zy17.user.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-5 Time: 上午9:40 To
 * change this template use File | Settings | File Templates.
 */
public class RestAuthenticationProvider implements AuthenticationProvider {

//	@Autowired
//	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		RestToken restToken = (RestToken) authentication;

		String key = restToken.getKey();
		String credentials = restToken.getCredentials();

		User user = null;

		if (user == null) {
			throw new BadCredentialsException("Authenticate failed.");
		}
		return getAuthenticatedUser(user);
	}

	private Authentication getAuthenticatedUser(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(getRole(user.getRole())));

		return new RestToken(user.getUserName(), user.getPassword(),
				authorities);
	}

	private String getRole(int role) {
		if (role == 1)
			return "ROLE_ADMIN";
		else
			return "ROLE_USER";
	}

	@Override
	/*
	 * Determines if this class can support the token provided by the filter.
	 */
	public boolean supports(Class<?> authentication) {
		return RestToken.class.equals(authentication);
	}
}
