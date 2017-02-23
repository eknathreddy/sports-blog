package com.ek.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ek.blog.entity.Blog;
import com.ek.blog.entity.User;
import com.ek.blog.repository.BlogRepository;
import com.ek.blog.repository.UserRepository;

@Service
public class BlogService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
	}

	public void delete(int id) {
		blogRepository.delete(id);
	}

}
