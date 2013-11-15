package com.zy17.auth;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-5 Time: 下午2:03 To
 * change this template use File | Settings | File Templates.
 */
public class CustomAccessDeniedHandler implements
		org.springframework.security.web.access.AccessDeniedHandler {

	public CustomAccessDeniedHandler() {
	}

	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		PrintWriter writer = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			json.put("status", HttpStatus.FORBIDDEN.getReasonPhrase());
			json.put("code", HttpServletResponse.SC_FORBIDDEN);
			json.put("message", "No access permission.");
			JSONObject object = new JSONObject();
			object.put("error", json);
			writer.println(object.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
