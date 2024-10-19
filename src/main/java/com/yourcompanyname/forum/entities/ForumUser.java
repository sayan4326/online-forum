package com.yourcompanyname.forum.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yourcompanyname.forum.customValidation.AlphaNumericPassword;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class ForumUser extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@NotBlank
	@Size(max = 30, min = 5, message = "username must be betwwen 5 to 30 charecters")
	private String userName;

	@Email
	@NotBlank(message = "valid email is required")
	private String email;

	@AlphaNumericPassword(message = "Password must be at least 6 characters long and include an uppercase letter, lowercase letter, number, and special character")
	private String password;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Role role;

	@OneToMany
	@JoinColumn(name = "postId")
	@JsonIgnoreProperties(value = { "author", "comments" })
	private List<Post> posts;

	enum Role {
		ROLE_USER, ROLE_ADMIN
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addPost(Post post) {
		if (this.posts == null)
			posts = new ArrayList<>();

		posts.add(post);
	}

}
