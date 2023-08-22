package com.nichebit.resourcemanagement.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichebit.resourcemanagement.entity.RefreshToken;
import com.nichebit.resourcemanagement.repository.EmployeeRepository;
import com.nichebit.resourcemanagement.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public RefreshToken createRefreshToken(String username)
	{
		
		RefreshToken refreshToken=refreshTokenRepository.getByEmpId(employeeRepository.getByEmpname(username).get().getId());
		if(refreshToken!=null)
			return refreshToken;
		else
		{
			refreshToken = RefreshToken.builder().emp(employeeRepository.findByEmpname(username).get()).token(UUID.randomUUID().toString()).expiryDate(Instant.now().plusMillis(6000000)).build();
				
			return refreshTokenRepository.save(refreshToken);
		}
	}
	
	public Optional<RefreshToken> findByToken(String token)
	{
		return refreshTokenRepository.findByToken(token);
	}
	

	 
	public RefreshToken verifyExpiration(RefreshToken token)
	{
		if(token.getExpiryDate().compareTo(Instant.now())<0)
		{
			refreshTokenRepository.delete(token);
			throw new RuntimeException(token.getToken()+" refersh token is expired");
		}
		return token;
	}

}
