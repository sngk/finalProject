package com.sngk.lab.wumpus.model.command;

import com.sngk.lab.wumpus.model.Room;
import com.sngk.lab.wumpus.controller.WorldController;

import static com.sngk.lab.wumpus.model.command.Result.*;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */

public class ShootCommand extends Command {

	public ShootCommand(String n) {
		super(n);
	}

	@Override
	public Result perform(WorldController w) {
		Room n = w.getRoom(target);
		if (n == null)
			return ROOM_NOT_EXISTS;
		if (w.shoot(n)) {
			return WUMPUS_KILLED;
		}
		return WUMPUS_NOT_KILLED;
	}
}
