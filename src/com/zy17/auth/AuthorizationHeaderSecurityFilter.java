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
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-5 Time: 上午11:16 To
 * change this template use File | Settings | File Templates.
 */
public class AuthorizationHeaderSecurityFilter extends GenericFilterBean {
    private AuthenticationManager authenticationManager;
    private AuthenticationEntryPoint authenticationEntryPoint;

    public AuthorizationHeaderSecurityFilter(AuthenticationManager authenticationManager) {
        this(authenticationManager, new CustomAuthenticationEntryPoint());
    }

    public AuthorizationHeaderSecurityFilter(AuthenticationManager authenticationManager,
                                             CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // Pull out the Authorization header
        String authorization = request.getHeader("Authorization");

        // If the Authorization header is null, let the
        // ExceptionTranslationFilter provided by
        // the <http> namespace kick of the BasicAuthenticationEntryPoint to
        // provide the username and password dialog box
        if (authorization == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            //Todo 测试代码
            chain.doFilter(request, response);
//			String[] credentials = decodeHeader(authorization);
//			Assert.isTrue(credentials.length == 2);
//			Authentication authentication = new RestToken(credentials[0],
//					credentials[1]);
//
//			// Request the authentication manager to authenticate the token
//			Authentication successfulAuthentication = authenticationManager
//					.authenticate(authentication);
//			// Pass the successful token to the SecurityHolder where it can be
//			// retrieved by this thread at any stage.
//			SecurityContextHolder.getContext().setAuthentication(
//					successfulAuthentication);
//			// Continue with the Filters
//			chain.doFilter(request, response);
        } catch (AuthenticationException authenticationException) {
            // If it fails clear this threads context and kick off the
            // authentication entry point process.
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response,
                    authenticationException);
        }
    }

    public String[] decodeHeader(String authorization) {
        // Decode the Auth Header to get the username and password
        String credentials = Encrypt.decrypt(authorization);
        return credentials.split(":");
    }
}
