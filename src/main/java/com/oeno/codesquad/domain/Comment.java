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
public class Comment {
	@Id
	@GeneratedValue
	private Long index;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_comment_to_question"))
	private Question question;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_comment_to_user"))
	private User user;

	@Column(nullable = false)
	private String contents;
	@Column(nullable = false, length = 16)
	private String time;

	public Comment() {
		this.index = 0L;
		this.question = null;
		this.user = null;
		this.contents = "";
		this.time = "";
	}

	public Comment(Question question, User user, String contents) {
		this.question = question;
		this.user = user;
		this.contents = contents;
		this.time = CodesquadApplication.getCurrentTime("yyyy-MM-dd HH:mm");
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
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

	public void update(Comment comment) {
		this.contents = comment.contents;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.index, this.question, this.user, this.contents, this.time);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Comment comment = (Comment) obj;

		return Objects.equal(this.index, comment.getIndex()) && Objects.equal(this.question, comment.getQuestion())
				&& Objects.equal(this.user, comment.getUser()) && Objects.equal(this.contents, comment.getContents())
				&& Objects.equal(this.time, comment.getTime());
	}
}
