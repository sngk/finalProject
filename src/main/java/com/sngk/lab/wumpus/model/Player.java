package com.sngk.lab.wumpus.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */
@Entity
@Table(name = Player.TABLE)
public class Player extends BaseModel {

	public static final String TABLE = "player";

	@Column(name = "name")
	private String name;

	@Column(name = "login")
	private String login;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
