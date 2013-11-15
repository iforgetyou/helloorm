package com.zy17.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-7-8 Time: 下午1:52 To
 * change this template use File | Settings | File Templates.
 */
public class AllSecurityFilter extends GenericFilterBean {

	private AuthenticationManager authenticationManager;
	private AuthenticationEntryPoint authenticationEntryPoint;

	public AllSecurityFilter(AuthenticationManager authenticationManager) {
		this(authenticationManager, new CustomAuthenticationEntryPoint());
	}

	public AllSecurityFilter(AuthenticationManager authenticationManager,
			CustomAuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		try {
			// Pull out the Authorization header
			String appkey = request.getHeader("AppKey");

			// If the Authorization header is null, let the
			// ExceptionTranslationFilter provided by
			// the <http> namespace kick of the BasicAuthenticationEntryPoint to
			// provide the username and password dialog box
			if (appkey == null) {
				throw new RestAuthenticationException(
						"Application key is not correct.");
			}

			if (!"dGVzdDp0ZXN0".equals(appkey)) {
				throw new RestAuthenticationException(
						"Application key is not correct.");
			}

			// Continue with the Filters
			chain.doFilter(request, response);

		} catch (AuthenticationException authenticationException) {
			// If it fails clear this threads context and kick off the
			// authentication entry point process.
			SecurityContextHolder.clearContext();
			authenticationEntryPoint.commence(request, response,
					authenticationException);
		}
	}
}
