package com.study.test;

public class Dog {
	private String name;
	private boolean isLoyal;// 是忠诚的
	private boolean alive;// 活蹦乱跳的

	public Dog(boolean isLoyal, boolean alive) {
		this.isLoyal = isLoyal;
		this.alive = alive;
	}

	public Dog(String name, boolean isLoyal, boolean alive) {
		this.name = name;
		this.isLoyal = isLoyal;
		this.alive = alive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsLoyal() {
		return isLoyal;
	}

	public void setIsLoyal(boolean isLoyal) {
		this.isLoyal = isLoyal;
	}

	public boolean getIsAlive() {
		return alive;
	}

	public void setIsAlive(boolean alive) {
		this.alive = alive;
	}
	
	
}
