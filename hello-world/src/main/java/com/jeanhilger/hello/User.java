package com.jeanhilger.hello;

public class User {
	
	private final long id;
	private final String name;
	private final int age;
	private final String genre;
	
	public User(long id, String name, int age, String genre) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.genre = genre;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}
	
	public String getGenre() {
		return this.genre;
	}

}
