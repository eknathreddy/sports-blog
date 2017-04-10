package com.ek.blog.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ek.blog.entity.Post;
import com.ek.blog.service.PostService;
import com.ek.blog.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@ModelAttribute("post")
	public Post constructPost() {
		return new Post();
	}

	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		System.out.print("-->name" + name);
		model.addAttribute("user", userService.findOneWithPosts(name));
		return "account";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddPost(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return account(model, principal);
		}
		String name = principal.getName();
		postService.save(post, name);
		return "redirect:/account.html";
	}

	@RequestMapping("/post/remove/{id}")
	public String removePost(@PathVariable int id) {
		Post post = postService.findOne(id);
		postService.delete(post);
		return "redirect:/account.html";
	}

}
