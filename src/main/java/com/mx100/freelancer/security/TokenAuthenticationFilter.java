package com.mx100.freelancer.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;


public class TokenAuthenticationFilter  extends OncePerRequestFilter{
    private final Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    UserDetailsService userDetailsService;
    /*
     * The below paths will get ignored by the filter
     */
    public static final String ROOT_MATCHER = "/";
    public static final String FAVICON_MATCHER = "/favicon.ico";
    public static final String HTML_MATCHER = "/**/*.html";
    public static final String CSS_MATCHER = "/**/*.css";
    public static final String JS_MATCHER = "/**/*.js";
    public static final String IMG_MATCHER = "/images/*";
    public static final String LOGIN_MATCHER = "/auth/login";
    public static final String LOGOUT_MATCHER = "/auth/logout";
    private List<String> pathsToSkip = Arrays.asList(
            ROOT_MATCHER,
            HTML_MATCHER,
            FAVICON_MATCHER,
            CSS_MATCHER,
            JS_MATCHER,
            IMG_MATCHER,
            LOGIN_MATCHER,
            LOGOUT_MATCHER
    );
    
    private boolean skipPathRequest(HttpServletRequest request, List<String> pathsToSkip ) {
        Assert.notNull(pathsToSkip, "path cannot be null.");
        List<RequestMatcher> m = pathsToSkip.stream().map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
        OrRequestMatcher matchers = new OrRequestMatcher(m);
        return matchers.matches(request);
    }
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authToken = tokenHelper.getToken(request);
		log.debug("get token :"+authToken);
		
        if (authToken != null && !skipPathRequest(request, pathsToSkip)) {
            String username = tokenHelper.getUserNameFromToken(authToken);
            log.debug("get user:"+username);
            
            // get user
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // create authentication
            TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
            authentication.setToken(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }else {
            SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthetication());

        }
        filterChain.doFilter(request, response);

	}

}
