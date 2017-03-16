package com.ek.blog.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, message = "This is not a valid Post!")
	private String post_msg;

	@Size(min = 1, message = "Name must atleast 1 character!")
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "published_date")
	private Date publishedDate;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Item> items;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPost_msg() {
		return post_msg;
	}

	public void setPost_msg(String post_msg) {
		this.post_msg = post_msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

}
