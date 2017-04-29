package com.ek.blog.controller;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ek.blog.config.SocialUserDetails;
import com.ek.blog.entity.FBUser;
import com.ek.blog.entity.User;
import com.ek.blog.service.UserService;

@Controller
public class FBUserController {

	private Facebook facebook;

	@Autowired
	private UserService userService;

	private ConnectionRepository connectionRepository;

	public FBUserController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping("/fblogin")
	public String fbLogin() {
		System.out.println("In Fblogin");
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "login";
		}

		// User fbuser = facebook.userOperations().getUserProfile();
		User user = new User();

		String[] fields = { "id", "email", "name" };
		try {
			FBUser fbuser = facebook.fetchObject("me", FBUser.class, fields);
			if (userService.finduser(fbuser.getId()) == null) {
				userService.saveFb(fbuser);
				System.out.println("User doesnot exists in user!!!!");
				System.out.println(fbuser.getId());
				System.out.println(fbuser.getName());
				System.out.println(fbuser.getEmail());
				user.setName(fbuser.getName());
				user.setEmail(fbuser.getEmail());
				user.setPassword(UUID.randomUUID().toString());
				userService.save(user);

				// http.authorizeRequests().antMatchers("/account**").hasRole("ROLE_USER").anyRequest().authenticated();
			} else {
				System.out.println("User exists in user!!!!");
				System.out.println(fbuser.getId());
				System.out.println(fbuser.getName());
				System.out.println(fbuser.getEmail());
				user.setName(fbuser.getName());
				// http.authorizeRequests().antMatchers("/account**").hasRole("ROLE_USER").anyRequest().authenticated();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		// HttpSession session = httpServletRequest.getSession();
		// Object principal = new Object();

		SocialUserDetails userDetails = new SocialUserDetails(user.getName(), Arrays.asList(new SimpleGrantedAuthority(
				"ROLE_USER")));

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		System.out.println("--->isAuth : " + authentication.isAuthenticated());
		System.out.println("--->getName : " + authentication.getName());
		SecurityContextHolder.clearContext();
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// session.setAttribute("username", user.getName());
		// session.setAttribute("authorities", authentication.getAuthorities());
		// httpServletResponse.setStatus(HttpServletResponse.SC_OK);

		return "redirect:/index.html";
	}
}
