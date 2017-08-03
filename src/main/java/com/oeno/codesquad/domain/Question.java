package com.oeno.codesquad.domain;

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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
