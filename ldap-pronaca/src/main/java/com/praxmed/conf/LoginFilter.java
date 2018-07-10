package com.praxmed.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praxmed.clinica.dto.User;
import com.praxmed.util.AesCipher;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	protected LoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		InputStream body = request.getInputStream();
		User user = new ObjectMapper().readValue(body, User.class);
		String password=user.getPassword().trim();
		String username=user.getUsuario().trim();
		try {
			password=AesCipher.decrypt(password);
			username=AesCipher.decrypt(username);
			user.setPassword(password);
			user.setUsuario(username);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsuario(),
				user.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		JwtUtil.addAuthentication(response, authResult.getName());
	}

}
