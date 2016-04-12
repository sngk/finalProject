package com.sngk.lab.wumpus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */

@Entity
@Table(name = MazeRunner.TABLE)
public class MazeRunner extends BaseModel {

	public static final String TABLE = "maze_runner";

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	@Column(name = "health")
	private int health;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
