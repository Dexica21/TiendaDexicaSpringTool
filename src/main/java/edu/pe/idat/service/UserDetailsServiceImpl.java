package edu.pe.idat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pe.idat.Model.*;
import edu.pe.idat.security.UserDetailsImpl;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
    private final UsuarioService userService;
    
    @Autowired
    public UserDetailsServiceImpl(UsuarioService userService) {
    	
        this.userService = userService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Usuario user = userService.findbyUsername(username);

        return UserDetailsImpl.build(user);
    }
}
