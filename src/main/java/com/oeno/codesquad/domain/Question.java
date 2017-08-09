package com.oeno.codesquad.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.common.base.Objects;
import com.oeno.codesquad.CodesquadApplication;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long index;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_to_user"))
	private User user;

	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String contents;
	@Column(nullable = false, length = 16)
	private String time;

	public Question() {
		this.index = 0L;
		this.user = null;
		this.title = "";
		this.contents = "";
		this.time = "";
	}

	public Question(User user, String title, String contents) {
		this.user = user;
		this.title = title;
		this.contents = contents;
		this.time = CodesquadApplication.getCurrentTime("yyyy-MM-dd HH:mm");
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public void update(Question question) {
		this.title = question.title;
		this.contents = question.contents;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.index, this.user, this.title, this.contents, this.time);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Question question = (Question) obj;

		return Objects.equal(this.index, question.index) && Objects.equal(this.user, question.user)
				&& Objects.equal(this.title, question.title) && Objects.equal(this.contents, question.contents)
				&& Objects.equal(this.time, question.time);
	}
}
