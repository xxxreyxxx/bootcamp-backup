package com.parking.dto.user;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	private Long id;
	private String email;
	private String password;
	private String fullname;
	private Date birthdate;
	private String phoneNumber;
}
