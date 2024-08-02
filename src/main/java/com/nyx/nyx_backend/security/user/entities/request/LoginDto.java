package com.nyx.nyx_backend.security.user.entities.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

	private String username;
	private String password;
	private String token;
}
