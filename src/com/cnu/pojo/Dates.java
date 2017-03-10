package com.cnu.pojo;

import java.io.Serializable;

public class Dates implements Serializable

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9209655461571929883L;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String email;
	private String date;
	private String thing;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getThing() {
		return thing;
	}
	public void setThing(String thing) {
		this.thing = thing;
	}
	
}
