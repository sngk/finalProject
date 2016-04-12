package com.sngk.lab.wumpus.model;

import javax.persistence.*;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */
@Entity
@Table(name = MazePath.TABLE)
public class MazePath extends BaseModel implements Edge {

	public static final String TABLE = "maze_path";

	@ManyToOne
	@JoinColumn(name = "room_from_id")
	private Room roomFrom;

	@ManyToOne
	@JoinColumn(name = "room_to_id")
	private Room roomTo;

	@Column(name = "distance")
	private int distance;

	public Room getRoomFrom() {
		return roomFrom;
	}

	public void setRoomFrom(Room roomFrom) {
		this.roomFrom = roomFrom;
	}

	public Room getRoomTo() {
		return roomTo;
	}

	public void setRoomTo(Room roomTo) {
		this.roomTo = roomTo;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}


	@Transient
	@Override
	public String from() {
		return getRoomFrom().getLabel();
	}

	@Transient
	@Override
	public String to() {
		return getRoomTo().getLabel();
	}

	@Transient
	@Override
	public int distance() {
		return distance;
	}
}
