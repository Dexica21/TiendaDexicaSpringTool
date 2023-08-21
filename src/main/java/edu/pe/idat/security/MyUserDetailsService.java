package edu.pe.idat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import edu.pe.idat.Model.ERol;
import edu.pe.idat.Model.Rol;
import edu.pe.idat.Model.Usuario;
import edu.pe.idat.repository.*;


import java.util.Collections;
import java.util.Optional;



@Component
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UsuarioRepository usuarioRepo;
    
    
    /*public MyUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }*/


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userRes = usuarioRepo.findByUsername(username);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Usuario no encontrado = " + username);
        Usuario user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
        		username,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
    

	

}
