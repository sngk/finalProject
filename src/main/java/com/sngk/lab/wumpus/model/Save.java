package com.sngk.lab.wumpus.model;

import com.avaje.ebean.annotation.PrivateOwned;

import javax.persistence.*;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */
@Entity
@Table(name = Save.TABLE)
public class Save extends BaseModel {

	public static final String TABLE = "save";

	@ManyToOne
	@JoinColumn(name = "layout_id")
	private Layout layout;

	@Column(name = "label")
	private String name;

	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;

	@PrivateOwned
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maze_runner_id")
	private MazeRunner mazeRunner;

	@ManyToOne
	@JoinColumn(name = "wumpus_room_id")
	private Room wumpusRoom;

	@ManyToOne
	@JoinColumn(name = "good_bat_room_id")
	private Room goodBatRoom;

	@ManyToOne
	@JoinColumn(name = "bad_bat_room_id")
	private Room badBatRoom;

	@ManyToOne
	@JoinColumn(name = "pit_room_id")
	private Room pitRoom;

	@ManyToOne
	@JoinColumn(name = "treasure_room_id")
	private Room treasureRoom;

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public MazeRunner getMazeRunner() {
		return mazeRunner;
	}

	public void setMazeRunner(MazeRunner mazeRunner) {
		this.mazeRunner = mazeRunner;
	}

	public Room getWumpusRoom() {
		return wumpusRoom;
	}

	public void setWumpusRoom(Room wumpusRoom) {
		this.wumpusRoom = wumpusRoom;
	}

	public Room getGoodBatRoom() {
		return goodBatRoom;
	}

	public void setGoodBatRoom(Room goodBatRoom) {
		this.goodBatRoom = goodBatRoom;
	}

	public Room getBadBatRoom() {
		return badBatRoom;
	}

	public void setBadBatRoom(Room badBatRoom) {
		this.badBatRoom = badBatRoom;
	}

	public Room getPitRoom() {
		return pitRoom;
	}

	public void setPitRoom(Room pitRoom) {
		this.pitRoom = pitRoom;
	}

	public Room getTreasureRoom() {
		return treasureRoom;
	}

	public void setTreasureRoom(Room treasureRoom) {
		this.treasureRoom = treasureRoom;
	}
}
