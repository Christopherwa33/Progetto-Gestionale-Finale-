package model;

import java.util.Map;

import utils.IMappable;

public class Utente implements IMappable {
	private int id;
	private String username;
	private String password;

	public Utente(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Utente(Map<String, String> map) {
		fromMap(map);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
