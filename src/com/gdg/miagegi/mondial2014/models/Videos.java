package com.gdg.miagegi.mondial2014.models;

public class Videos {
	
	private String title;
	private String description;
	private String link;
	private String picture;
	
	public Videos(){
		
	}
	public Videos(String title, String description, String link, String picture){
		this.title = title;
		this.description = description;
		this.link = link;
		this.picture = picture;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	

}
