package com.nichebit.resourcemanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import com.nichebit.resourcemanagement.filter.AuthenticationTokenFilter;
import com.nichebit.resourcemanagement.service.EmployeeUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationTokenFilter authFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		/*
		 * UserDetails
		 * venkat=User.withUsername("venkat").password(passwordEncoder.encode("pwd")).
		 * roles("ADMIN").build(); UserDetails
		 * pravudatta=User.withUsername("pravudatta").password(passwordEncoder.encode(
		 * "pwd")).roles("USER").build(); return new
		 * InMemoryUserDetailsManager(venkat,pravudatta);
		 */
		return new EmployeeUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
				.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
				.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> {
					auth.requestMatchers(new AntPathRequestMatcher("/login/authenticate"),
							new AntPathRequestMatcher("/login/refreshToken")).permitAll()
							.requestMatchers(new AntPathRequestMatcher("/project/add"),
									new AntPathRequestMatcher("/project/all"),
									new AntPathRequestMatcher("/project/update"),
									new AntPathRequestMatcher("/project/delete/**"),
									new AntPathRequestMatcher("/updatedocument"),
									new AntPathRequestMatcher("/adddocument"),
									new AntPathRequestMatcher("/deletedocument/**"),
									new AntPathRequestMatcher("/getalldoc"), new AntPathRequestMatcher("/addEmployee"),
									new AntPathRequestMatcher("/updateEmployee"),
									new AntPathRequestMatcher("/employees"),
									new AntPathRequestMatcher("/deleteEmployee/**"),
									new AntPathRequestMatcher("/uploaddoc"),
									new AntPathRequestMatcher("/downloaddoc/{filename}"))
							.authenticated();
				}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

}
