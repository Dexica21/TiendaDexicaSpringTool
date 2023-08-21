package edu.pe.idat.security;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import edu.pe.idat.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
//(securedEnabled = true,
//jsr250Enabled = true,
//prePostEnabled = true) // by default
public class WebSecurityConfig {
	
	private UserDetailsServiceImpl userDetailsService;
	
	private JWTFilter filter;
	
	@Autowired
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JWTFilter filter) {
		
	    this.userDetailsService = userDetailsService;
	    this.filter = filter;
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
	  
		return new BCryptPasswordEncoder();
	  
	}

	@Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall(); // Customize as needed
    }
  //@Bean
  /*SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> 
          auth.requestMatchers("/api/auth/**").permitAll()
              .requestMatchers("/api/test/**").permitAll()
              //.requestMatchers("/app/auth/**").permitAll()
              .requestMatchers("/css/**").permitAll()
              .requestMatchers("/js/**").permitAll()
              .requestMatchers("/images/**").permitAll()
              .requestMatchers("/").permitAll()
              .anyRequest().authenticated()
        );
    
    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
    return http.build();
  }*/


/*
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests((authorize) ->
              authorize.requestMatchers(
            		  "Web/Dexica/**"
            		  ,"/Dexica/home"
            		  , "css/**"
            		  , "Detalleproductoimg/**"
            		  , "Imagenes/**"
            		  , "/Producto/Catalogo/**"
            		  , "/Web/Administrador/**"
            		  , "Administrador/**"
            		  , "DetalleProducto/**"
            		  , "Administración/css/**"
            		  , "Administración/img/**"
            		  , "Administración/js/**"
            		  , "Administración/scss/**"
            		  , "Administración/vendor/**"
            		  , "Ventas/Formulario/**"
            		  , "CargarPago/**"
            		  , "/Catalogo/**"
            		  , "imag/**"
            		  , "Js/**").permitAll()
                      .anyRequest().hasAnyRole("USER"))
              .userDetailsService(userDetailsService)
              .formLogin(login -> login.loginPage("/Web/Dexica/Login")
                      .loginProcessingUrl("/process_login")
                      .defaultSuccessUrl("/Dexica/home", true)
                      .failureUrl("/login?error").successHandler((request, response, authentication) -> {
                          if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                              response.sendRedirect("/Web/Administrador"); 
                          } else {
                              response.sendRedirect("/Dexica/home"); 
                          }
                      }))
              .logout(logout -> logout
                      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                      .logoutSuccessUrl("/Web/Dexica/Login"));
      return http.build();
  }
  */
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	    	.csrf()
	    	.disable()
	    	.authorizeHttpRequests()
	                .requestMatchers(
	                    "/Web/Dexica/**",
	                    "/Dexica/home",
	                    "/css/**",
	                    "/Detalleproductoimg/**",
	                    "/Imagenes/**",
	                    "/Producto/Catalogo/**",
	                    "/DetalleProducto/**",
	                    "/Administración/css/**",
	                    "/Administración/img/**",
	                    "/Administración/js/**",
	                    "/Administración/scss/**",
	                    "/Administración/vendor/**",
	                    "/CargarPago/**",
	                    "/Catalogo/**",
	                    "/imag/**",
	                    "/Js/**"
	                ).permitAll()
	                .requestMatchers("/Administrador/**").hasAuthority("ADMIN")
	                .requestMatchers("/Usuario/**").hasAuthority("USER")
	                .anyRequest()
	                .authenticated()
	        .and()
	        .userDetailsService(userDetailsService)
	        .formLogin(login -> 
	            login
	                .loginPage("/Web/Dexica/Login")
	                .loginProcessingUrl("/process_login")
	                .defaultSuccessUrl("/Dexica/home", true)
	                .failureUrl("/login?error")
	                .successHandler((request, response, authentication) -> {
	                    if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
	                        response.sendRedirect("/Administrador/Web"); 
	                    } else {
	                        response.sendRedirect("/Dexica/home"); 
	                    }
	                })
	        )
	        .logout(logout -> 
	            logout
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/Web/Dexica/Login")
	        );
	        
	    return http.build();
	}
	
	

	

}

