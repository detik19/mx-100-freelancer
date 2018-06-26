package com.mx100.freelancer.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AnonymousAuthetication extends AbstractAuthenticationToken {

	public AnonymousAuthetication() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4159433964519283046L;

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Override
    public boolean isAuthenticated() {
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        return true;
    }


}
