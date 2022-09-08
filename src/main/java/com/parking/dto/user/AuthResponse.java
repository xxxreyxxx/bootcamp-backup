package com.parking.dto.user;

import java.io.Serializable;

import com.parking.entity.user.MasterUserEntity;

public class AuthResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;
	private final MasterUserEntity user;

	public AuthResponse(String token, MasterUserEntity user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public MasterUserEntity getUser() {
		return user;
	}
}
