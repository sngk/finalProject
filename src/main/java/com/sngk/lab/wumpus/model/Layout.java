package com.sngk.lab.wumpus.model;

import com.avaje.ebean.annotation.PrivateOwned;

import javax.persistence.*;
import java.util.Map;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */
@Entity
@Table(name = Layout.TABLE)
public class Layout extends BaseModel {

	public static final String TABLE = "layout";

	@Column(name = "name")
	private String name;

	@PrivateOwned
	@OneToMany(mappedBy = "layout", cascade = CascadeType.ALL)
	@MapKey(name = "label")
	private Map<String, Room> rooms;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Room> getRooms() {
		return rooms;
	}

	public void setRooms(Map<String, Room> rooms) {
		this.rooms = rooms;
	}
}
