package com.nichebit.resourcemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("http://localhost:4200")
public class AuthenticateTokenController {
	@Autowired
	private AuthenticationManager authenticationManager	;
	@Autowired
	AuthenticateTokenService tokenService;
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@PostMapping("/authenticate")
	public AuthenticateTokenResponse authenticateAndGetToken(@RequestBody AuthenticateTokenRequest authRequest)
	{
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		if(authentication.isAuthenticated())
		{
			String token = tokenService.generateToken(authRequest.getUsername(),authRequest.getPassword());
			RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
			return AuthenticateTokenResponse.builder().accessToken(token).token(refreshToken.getToken()).build();
			
		}
		else
		{
			throw new UsernameNotFoundException("User not found "+authRequest.getUsername());
		}
	}
	@PostMapping("/refreshToken")
	public AuthenticateTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest)
	{
		return refreshTokenService.findByToken(refreshTokenRequest.getToken())
		.map(refreshTokenService::verifyExpiration)
		.map(RefreshToken::getEmp)
		.map(emp -> {
			String accessToken = tokenService.generateToken(emp.getEmpname(), emp.getPassword());
			return AuthenticateTokenResponse.builder().accessToken(accessToken)
			.token(refreshTokenRequest.getToken()).build();
		}).orElseThrow(()->new RuntimeException(refreshTokenRequest.getToken()+" refresh token not found in database"));
		
	}
}
