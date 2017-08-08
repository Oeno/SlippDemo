package com.oeno.codesquad.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.base.Objects;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long index;

	@Column(nullable = false, length = 25)
	private String writer;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String contents;
	@Column(nullable = false, length = 16)
	private String time;

	public Question() {
	}

	public Question(String writer, String title, String contents, String time) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.time = time;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	@Override
	public int hashCode() {
		return Objects.hashCode(index, writer, title, contents, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Question question = (Question) obj;

		return Objects.equal(this.index, question.index) && Objects.equal(this.writer, question.writer)
				&& Objects.equal(this.title, question.title) && Objects.equal(this.contents, question.contents)
				&& Objects.equal(this.time, question.time);
	}

}
