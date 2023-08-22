package com.nichebit.resourcemanagement.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.AuthenticateTokenRequest;
import com.nichebit.resourcemanagement.dto.AuthenticateTokenResponse;
import com.nichebit.resourcemanagement.dto.RefreshTokenRequest;
import com.nichebit.resourcemanagement.entity.RefreshToken;
import com.nichebit.resourcemanagement.service.AuthenticateTokenService;
import com.nichebit.resourcemanagement.service.RefreshTokenService;

@RestController
@RequestMapping("/login")
public class AuthenticateTokenController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	AuthenticateTokenService tokenService;
	@Autowired
	private RefreshTokenService refreshTokenService;

	AuthenticateTokenResponse returnResponse = new AuthenticateTokenResponse();

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticateTokenResponse> authenticateAndGetToken(
			@RequestBody AuthenticateTokenRequest authRequest) throws AuthenticationException {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			if (authentication.isAuthenticated()) {
				String token = tokenService.generateToken(authRequest.getUsername());
				RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());

				AuthenticateTokenResponse response = AuthenticateTokenResponse.builder().accessToken(token)
						.token(refreshToken.getToken()).build();

				return ResponseEntity.ok(response);
			} else {
				returnResponse.setStatus("Invalid Credentials");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
			}
		} catch (Exception e) {
			returnResponse.setStatus("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		}
	}

	@PostMapping("/refreshToken")
	public AuthenticateTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

		return refreshTokenService.findByToken(refreshTokenRequest.getToken())
				.map(refreshTokenService::verifyExpiration).map(RefreshToken::getEmp).map(emp -> {

					String accessToken = tokenService.generateToken(emp.getEmpname());

					return AuthenticateTokenResponse.builder().accessToken(accessToken)
							.token(refreshTokenRequest.getToken()).build();

				}).orElseThrow(() -> new RuntimeException(
						refreshTokenRequest.getToken() + " refresh token not found in database"));

	}

}
