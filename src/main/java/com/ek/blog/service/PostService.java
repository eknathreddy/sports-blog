package com.ek.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ek.blog.entity.Post;
import com.ek.blog.entity.User;
import com.ek.blog.repository.PostRepository;
import com.ek.blog.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	public void save(Post post, String name) {
		User user = userRepository.findByName(name);
		post.setUser(user);
		postRepository.save(post);
	}

	@PreAuthorize("#post.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("post") Post post) {
		postRepository.delete(post);
	}

	public Post findOne(int id) {
		return postRepository.findOne(id);
	}

}
