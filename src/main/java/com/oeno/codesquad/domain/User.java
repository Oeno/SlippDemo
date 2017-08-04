package com.oeno.codesquad.domain;

import com.google.common.base.Objects;

public class User {
	private String userId;
	private String password;
	private String name;
	private String email;

	public User() {
		this.userId = "";
		this.password = "";
		this.name = "";
		this.email = "";
	}

	public User(String userId, String password, String name, String email) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public User(User copy) {
		this.userId = copy.userId;
		this.password = copy.password;
		this.name = copy.name;
		this.email = copy.email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.userId, this.password, this.name, this.email);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final User user = (User) obj;

		return Objects.equal(this.userId, user.userId) && Objects.equal(this.password, user.password)
				&& Objects.equal(this.name, user.name) && Objects.equal(this.email, user.email);
	}
}
