package com.zy17.auth;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-5 Time: 下午1:35 To
 * change this template use File | Settings | File Templates.
 */
public class CustomAuthenticationEntryPoint extends
        BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Origin", "null");
		response.addHeader("WWW-Authenticate", "Basic realm=\""
				+ getRealmName() + "\"");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		PrintWriter writer = response.getWriter();
//		JSONObject json = new JSONObject();
//		try {
//			json.put("code", HttpServletResponse.SC_UNAUTHORIZED);
//			json.put("message", authException.getMessage());
//			JSONObject object = new JSONObject();
//			object.put("error", json);
//			writer.println(object.toString());
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
	}
}
