package com.example.bookstorefrontend;

import com.example.bookstorefrontend.login.Role;
import com.example.bookstorefrontend.login.RoleRepository;
import com.example.bookstorefrontend.login.User;
import com.example.bookstorefrontend.login.UserRepository;
import com.example.bookstorefrontend.model.Books;
import com.example.bookstorefrontend.service.BookApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class BookstoreFrontendApplication implements CommandLineRunner {

	@Autowired
	private BookApiClient client;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreFrontendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(client.fetchAllBooks());
//		Books books = client.fetchAllBooks();
//		books.getBooks().forEach(
//				book -> System.out.println(client.fetchBookInfo(book.getId()))
//		);
		Role adminRole = roleRepository.save(new Role("ADMIN"));
		Role userRole = roleRepository.save(new Role("USER"));

		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminRoles.add(userRole);

		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);

		userRepository.save(new User("admin",passwordEncoder.encode("admin"),adminRoles));
		userRepository.save(new User("user",passwordEncoder.encode("user"),userRoles));

		userRepository.findByUserName("user").ifPresent(System.out::println);
		roleRepository.findRolesByUserName("user").forEach(System.out::println);
	}
}
