package com.lil.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="postlogger")
public class PostLogger
{
	@Id
	int id;
	String bodyPost;
	String timePost;
	
	
	
	public PostLogger() {
		super();
	}
	public PostLogger(int id, String bodyPost, String timePost) {
		super();
		this.id = id;
		this.bodyPost = bodyPost;
		this.timePost = timePost;
	}
	@Override
	public String toString() {
		return "PostLogger [id=" + id + ", bodyPost=" + bodyPost + ", timePost=" + timePost + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBodyPost() {
		return bodyPost;
	}
	public void setBodyPost(String bodyPost) {
		this.bodyPost = bodyPost;
	}
	public String getTimePost() {
		return timePost;
	}
	public void setTimePost(String timePost) {
		this.timePost = timePost;
	}
	
	
	
}
