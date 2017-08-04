package com.oeno.codesquad.domain;

import com.google.common.base.Objects;

public class Question {
	private int index;
	private String writer;
	private String title;
	private String contents;
	private String time;

	public Question() {}

	public Question(int index, String writer, String title, String contents, String time) {
		this.index = index;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.time = time;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
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
