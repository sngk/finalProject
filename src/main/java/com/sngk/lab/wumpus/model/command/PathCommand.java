package com.sngk.lab.wumpus.model.command;

import com.sngk.lab.wumpus.model.Room;
import com.sngk.lab.wumpus.controller.WorldController;

import static com.sngk.lab.wumpus.model.command.Result.PATH_FOUND;
import static com.sngk.lab.wumpus.model.command.Result.ROOM_NOT_EXISTS;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */

public class PathCommand extends Command {

	public PathCommand(String n) {
		super(n);
	}

	@Override
	public Result perform(WorldController w) {
		Room r = w.getRoom(target);
		if (r == null)
			return ROOM_NOT_EXISTS;
		return PATH_FOUND;
	}
}
