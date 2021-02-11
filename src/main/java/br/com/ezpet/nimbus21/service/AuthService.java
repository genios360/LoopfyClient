package br.com.ezpet.nimbus21.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ezpet.nimbus21.client.RestService;
import br.com.ezpet.nimbus21.domain.UsuarioAdmin;
import br.com.ezpet.nimbus21.domain.UsuarioColaborador;
import br.com.ezpet.nimbus21.domain.enums.Role;

@Service
public class AuthService implements UserDetailsService {
	
	@Autowired
	RestService restService;
	
	public UsuarioColaborador findColabByEmail(String email) {
		
		String url1 = "https://ezpet-api.herokuapp.com/usuarioColab/email/" + email;
		RestTemplate restTemplate = restService.getObject();
		
		UsuarioColaborador usuarioColab = restTemplate.getForObject(url1, UsuarioColaborador.class);
		
		return usuarioColab;
	}
	
	public UsuarioAdmin findAdminByEmail(String email){
		
		String url1 = "https://ezpet-api.herokuapp.com/usuarioAdmin/email/" + email;
		RestTemplate restTemplate = restService.getObject();
		
		UsuarioAdmin usuarioAdmin = restTemplate.getForObject(url1, UsuarioAdmin.class);
	
		return usuarioAdmin;
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, InternalAuthenticationServiceException {
				
		UsuarioAdmin usuarioAdmin = null;
		UsuarioColaborador usuarioColab = null;
		
		try {
			usuarioAdmin = findAdminByEmail(email);
		} catch (Exception e) {
			usuarioColab = findColabByEmail(email);
		}
		
		
		if (usuarioAdmin == null && usuarioColab == null) {
			throw new UsernameNotFoundException("Email ou senha inválidos.");
		}
		
		if (!(usuarioAdmin == null)) {
			return new org.springframework.security.core.userdetails.User(
					usuarioAdmin.getEmail(), 
					usuarioAdmin.getSenha(), 
					mapRolesToAuthorities(usuarioAdmin.getRole())
			);
		}
		
		if(!(usuarioColab == null)) {
			return new org.springframework.security.core.userdetails.User(
					usuarioColab.getEmail(), 
					usuarioColab.getSenha(),
					mapRolesToAuthorities(usuarioColab.getRole())
			);
		}
		
		else {
			return null;
		}
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
		return Arrays.asList(new SimpleGrantedAuthority(role.name()));
	}

}
