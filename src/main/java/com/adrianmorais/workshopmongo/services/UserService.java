package com.adrianmorais.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianmorais.workshopmongo.domain.User;
import com.adrianmorais.workshopmongo.dto.UserDTO;
import com.adrianmorais.workshopmongo.repository.UserRepository;
import com.adrianmorais.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User u) {
		return repo.insert(u);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO u) {
		return new User(u.getId(), u.getName(), u.getEmail());
	}
	
	
}
