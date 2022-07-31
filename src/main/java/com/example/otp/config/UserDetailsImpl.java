package com.example.otp.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.example.otp.models.Person;
import com.example.otp.services.PersonService;

@Repository
@Transactional
public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	private PersonService personService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Person person = personService.findByEmail(email);

		if (person == null) {
			throw new UsernameNotFoundException("User não encontrada");
		}

		return new User(person.getUsername(), person.getPassword(), true, true, true, true, person.getAuthorities());
	}

}