package com.taipeihot.jazzlist.model;

public class Category {
	private String title;
	private int icon;
	private int count = 0;
	
	public Category() {}
	
	public Category(String title, int icon) {
		this.title = title;
		this.icon = icon;
	}
	
	public Category(String title, int icon, int count) {
		this.title = title;
		this.icon = icon;
		this.count = count;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getIcon() {
		return this.icon;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
}