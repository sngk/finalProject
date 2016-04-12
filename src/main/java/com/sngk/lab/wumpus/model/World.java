
package com.sngk.lab.wumpus.model;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * This class is for all models of our world.
 */
public class World extends Graph<Room, MazePath> {

	public Save lastSave;
	public MazeRunner mazeRunner;
	public Room wumpusRoom;
	public Room goodBatRoom;
	public Room badBatRoom;
	public Room pitRoom;
	public Room treasureRoom;

	public World(Save save) {
		super(save.getLayout().getRooms());
		lastSave = save;
		wumpusRoom = save.getWumpusRoom();
		mazeRunner = save.getMazeRunner();
		goodBatRoom = save.getGoodBatRoom();
		badBatRoom = save.getBadBatRoom();
		pitRoom = save.getPitRoom();
		treasureRoom = save.getTreasureRoom();
	}

}
