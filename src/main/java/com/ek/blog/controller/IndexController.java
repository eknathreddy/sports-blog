package com.ek.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ek.blog.service.PostService;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	public PostService postService;

	@RequestMapping
	public String index(Model model) {
		model.addAttribute("posts", postService.getPosts());
		return "index";
	}

	@RequestMapping("/{id}")
	public String postdetails(Model model, @PathVariable int id) {
		model.addAttribute("post", postService.findOne(id));
		return "post-detail";
	}

}
