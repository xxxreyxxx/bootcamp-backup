package com.parking.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.parking.config.jwt.JwtTokenUtil;
import com.parking.dto.user.AuthRequest;
import com.parking.dto.user.AuthResponse;
import com.parking.entity.user.MasterUserEntity;
import com.parking.service.user.impl.MyUserDetailsService;
import com.parking.service.user.impl.UserServiceImpl;

@RestController
@RequestMapping("/auth")
@EnableWebMvc
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest)
			throws Exception {
		authenticateAdmin(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final MasterUserEntity user = userServiceImpl.findByEmail(userDetails.getUsername());
		return ResponseEntity.ok(new AuthResponse(token, user));
	}

	private void authenticateAdmin(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
