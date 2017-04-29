package com.ek.blog.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
@PropertySource("classpath:application.properties")
public class SocialConfig {
	@Inject
	private Environment environment;

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();

		registry.addConnectionFactory(new FacebookConnectionFactory(environment
				.getProperty("spring.social.facebook.appId"), environment
				.getProperty("spring.social.facebook.appSecret")));

		return registry;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		// System.out.println("--->" + authentication.getName());
		return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook(ConnectionRepository repository) {
		Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
		return connection != null ? connection.getApi() : null;
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		// System.out.println("---> UserConnection");
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), textEncryptor);
	}

	@Inject
	private DataSource dataSource;

	@Inject
	private TextEncryptor textEncryptor;

	@Bean
	public ConnectController connectController() {
		// System.out.print("-----> Connectcontrol Bean Successful!!!");
		ConnectController controller = new ConnectController(connectionFactoryLocator(), connectionRepository());
		// controller.setApplicationUrl(environment.getProperty("application.url"));

		controller.setViewPath("");
		return controller;
	}

}