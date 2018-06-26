package com.mx100.freelancer.security;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHelper {
	@Value("${app.name}")
	private String appName;
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expires}")
	private int expiresIn;

	@Value("${jwt.header}")
	private String authHeader;

	@Value("${jwt.cookie}")
	private String authCookie;
	@Autowired
    UserDetailsService userDetailsService;

	private SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS512;

	public String getUserNameFromToken(String token){
		final Claims claims=this.getClaimFromToken(token);
		return claims.getSubject();

	}
    public String generateToken(String username) {
        return Jwts.builder()
                .setIssuer( appName )
                .setSubject(username)
                .setIssuedAt(generateCurrentDate())
                .setExpiration(generateExpirationDate())
                .signWith( this.signatureAlgorithm, this.secret )
                .compact();
    }
	private Claims getClaimFromToken(String token){
		//		Claims claims=null;
		return Jwts.parser().setSigningKey(this.secret)
				.parseClaimsJws(token)
				.getBody();

		//return token;

	}


	String generateToken(Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith( signatureAlgorithm, secret )
				.compact();
	}
    public Boolean canTokenBeRefreshed(String token) {
    	 try {
             final Date expirationDate = getClaimsFromToken(token).getExpiration();
             String username = getUserNameFromToken(token);
             UserDetails userDetails = userDetailsService.loadUserByUsername(username);
             return expirationDate.compareTo(generateCurrentDate()) > 0;
         } catch (Exception e) {
             return false;
         }
    	
    }

	private Date generateExpirationDate() {

		return new Date(getCurrentTimeMillis() + this.expiresIn * 1000);
	}

	private long getCurrentTimeMillis() {
		return DateTime.now().getMillis();
	}

	private Date generateCurrentDate() {
		return new Date(getCurrentTimeMillis());
	}
	

    public String getToken( HttpServletRequest request ) {
        /**
         *  Getting the token from Cookie store
         */
        Cookie authCookie = getCookieValueByName( request, this.authCookie );
        if ( authCookie != null ) {
            return authCookie.getValue();
        }
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        String authHeader = request.getHeader(this.authHeader);
        if ( authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
    
    /**
     * Find a specific HTTP cookie in a request.
     *
     * @param request
     *            The HTTP request object.
     * @param name
     *            The cookie name to look for.
     * @return The cookie, or <code>null</code> if not found.
     */
    public Cookie getCookieValueByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals(name)) {
                return request.getCookies()[i];
            }
        }
        return null;
    }
    

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.setIssuedAt(generateCurrentDate());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


}
