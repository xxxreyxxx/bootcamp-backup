package com.parking.dto.user;

public class CheckExist {
	private boolean response;

	public CheckExist() {
	}

	public CheckExist(boolean response) {
		this.response = response;
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}
}
