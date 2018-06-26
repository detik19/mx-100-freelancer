package com.mx100.freelancer.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx100.freelancer.domain.UserTokenState;
import com.mx100.freelancer.domain.Users;



@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final Logger log = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    @Value("${jwt.expires}")
    private int EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;
	@Autowired
	TokenHelper tokenHelper;

	@Autowired
	ObjectMapper objectMapper;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.debug("AuthenticationSuccessHandler");
		log.debug("TOKEN_COOKIE "+TOKEN_COOKIE);
		log.debug("EXPIRES_IN "+EXPIRES_IN);

		Users users = (Users)authentication.getPrincipal();

		String jws = tokenHelper.generateToken( users.getUserName() );

        // Create token auth Cookie
        Cookie authCookie = new Cookie( TOKEN_COOKIE, ( jws ) );

		authCookie.setHttpOnly( true );

		authCookie.setMaxAge( EXPIRES_IN );

		authCookie.setPath( "/" );
		// Add cookie to response
		response.addCookie( authCookie );

		// JWT is also in the response
		UserTokenState userTokenState = new UserTokenState(jws, EXPIRES_IN);
		String jwtResponse = objectMapper.writeValueAsString( userTokenState );
		response.setContentType("application/json");
		response.getWriter().write( jwtResponse );

	}
    
}
