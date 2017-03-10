package com.ek.blog.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ek.blog.entity.Post;
import com.ek.blog.entity.Role;
import com.ek.blog.entity.User;
import com.ek.blog.repository.ItemRepository;
import com.ek.blog.repository.PostRepository;
import com.ek.blog.repository.RoleRepository;
import com.ek.blog.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private PostRepository postRepository;

	@PostConstruct
	public void init() {

		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

		Post postSports = new Post();
		postSports.setName("Cricket One Day");
		postSports.setPost_msg("Match Between AUS and IND");
		postSports.setUser(userAdmin);
		postRepository.save(postSports);

		// Item item1 = new Item();
		// item1.setPost(postSports);
		// item1.setTitle("first");
		// item1.setLink("http://www.javavids.com");
		// item1.setPublishedDate(new Date());
		// itemRepository.save(item1);
		//
		// Item item2 = new Item();
		// item2.setPost(postSports);
		// item2.setTitle("second");
		// item2.setLink("http://www.javavids.com");
		// item2.setPublishedDate(new Date());
		// itemRepository.save(item2);

	}
}
