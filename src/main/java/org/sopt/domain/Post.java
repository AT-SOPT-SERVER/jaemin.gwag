package org.sopt.domain;

import org.sopt.Util.IDGenerator;

public class Post {
	private int id;
	private String title;

	public Post(int id, String title){
		this.id = id;
		this.title = title;
	}

	public int getId(){
		return this.id;
	}

	public String getTitle(){
		return this.title;
	}

	public void changeTitle(String title){
		this.title = title;
	}

	public static Post createPost(String title){
		return new Post(IDGenerator.generateId(), title);
	}

}
