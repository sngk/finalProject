package com.sngk.lab.wumpus.controller;

import com.sngk.lab.wumpus.model.MazePath;
import com.sngk.lab.wumpus.model.Room;
import com.sngk.lab.wumpus.model.Save;
import com.sngk.lab.wumpus.model.service.SaveService;
import com.sngk.lab.wumpus.model.Edge;
import com.sngk.lab.wumpus.model.World;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Stack;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * This class is controller for our world.
 */
@Singleton
public class WorldController {

	@Inject
	private SaveService saveService;

	private World world;

	public World createWorld(long playerId, long layoutId) {
		Save save = saveService.createOne(playerId, layoutId, "Save#" + System.currentTimeMillis());
		if (save != null) {
			world = new World(save);
			return world;
		}
		return null;
	}

	public World loadWorld(long saveId) {
		Save save = saveService.findOne(saveId);
		if (save != null) {
			world = new World(save);
			return world;
		}
		return null;
	}

	public Room getRoom(String r) {
		return (Room) world.getNode(r);
	}

	public Room getWumpusRoom() {
		return world.wumpusRoom;
	}

	public Room getGoodBatRoom() {
		return world.goodBatRoom;
	}

	public Room getBadBatRoom() {
		return world.badBatRoom;
	}

	public Room getPitRoom() {
		return world.pitRoom;
	}

	public Room getTreasureRoom() {
		return world.treasureRoom;
	}

	public boolean enter(Room r) {
		List<MazePath> ale = world.mazeRunner.getRoom().getMazePaths();
		for (MazePath mazePath : ale) {
			if ((r.getLabel()).equals(mazePath.getRoomTo().getLabel())) {
				world.mazeRunner.setRoom(r);
				return true;
			}
		}
		return false;
	}

	public boolean shoot(Room r) {
		if ((r.getLabel()).equals(world.wumpusRoom.getLabel()))
			return true;
		Stack<? extends Edge> sp = world.findShortestPath(world.mazeRunner.getRoom(), r);
		return false;
	}

	public Stack<MazePath> shortestPath(Room to) {
		Stack<MazePath> es = world.findShortestPath(world.mazeRunner.getRoom(), to);
		return es;
	}

	public boolean save() {
		//if only player moves then just save only his new position
		//if no than should implement logic
		world.lastSave.setMazeRunner(world.mazeRunner);
		world.lastSave = saveService.updateOne(world.lastSave);
		return true;
	}
}
