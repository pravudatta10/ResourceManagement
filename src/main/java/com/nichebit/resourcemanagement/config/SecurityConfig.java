package com.nichebit.resourcemanagement.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

	 private final AuthenticationTokenFilter authFilter;

 
     public SecurityConfig(AuthenticationTokenFilter authFilter) {
             this.authFilter = authFilter;
     }

	@Bean
	public UserDetailsService userDetailsService() {	
		return new EmployeeUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		CorsConfiguration corsCfg = new CorsConfiguration();
		corsCfg.addAllowedOriginPattern("*");

		corsCfg.addAllowedMethod(HttpMethod.GET.name());
		corsCfg.addAllowedMethod(HttpMethod.HEAD.name());
		corsCfg.addAllowedMethod(HttpMethod.POST.name());
		corsCfg.addAllowedMethod(HttpMethod.PUT.name());
		corsCfg.addAllowedMethod(HttpMethod.DELETE.name());
		 
		

		return httpSecurity
				.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
						.configurationSource(request -> new CorsConfiguration(corsCfg).applyPermitDefaultValues()))
				.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> 
					auth.requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
							.requestMatchers(new AntPathRequestMatcher("/project/**"),
									new AntPathRequestMatcher("/document/**"),
									new AntPathRequestMatcher("/employee/**"), 
									new AntPathRequestMatcher("/task/**"),
									new AntPathRequestMatcher("/timesheet/**"),
									new AntPathRequestMatcher("/jobcard/**"),
									new AntPathRequestMatcher("/holidaymaster/**"),
									new AntPathRequestMatcher("/clientMaster/**"),
									new AntPathRequestMatcher("/commonprojects/**"),
									new AntPathRequestMatcher("/masterdata/**"),
									new AntPathRequestMatcher("/commontasks/**"),
									new AntPathRequestMatcher("/sendmail")).authenticated()
				).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
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
