package com.adrianmorais.workshopmongo.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.adrianmorais.workshopmongo.domain.Post;
import com.adrianmorais.workshopmongo.domain.User;
import com.adrianmorais.workshopmongo.dto.AuthorDTO;
import com.adrianmorais.workshopmongo.dto.CommentDTO;
import com.adrianmorais.workshopmongo.dto.UserDTO;
import com.adrianmorais.workshopmongo.repository.PostRepository;
import com.adrianmorais.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, LocalDate.now(),  "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, LocalDate.now(), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO comment1 = new CommentDTO("Boa viagem mano!", LocalDate.now(), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Aproveite!", LocalDate.now(), new AuthorDTO(alex));
		CommentDTO comment3 = new CommentDTO("Tenha um otimo Dia!", LocalDate.now(), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().add(comment3);
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
