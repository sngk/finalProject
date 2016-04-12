package com.sngk.lab.wumpus.model.command;
/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */

import com.sngk.lab.wumpus.model.Room;
import com.sngk.lab.wumpus.controller.WorldController;

import static com.sngk.lab.wumpus.model.command.Result.*;

public class EnterCommand extends Command {

	public EnterCommand(String n) {
		super(n);
	}

	@Override
	public Result perform(WorldController w) {
		Room r = w.getRoom(target);
		if (r == null) {
			return ROOM_NOT_EXISTS;
		}
		if (r.getId().equals(w.getWumpusRoom().getId())) {
			if (!w.enter(r)) {
				return ROOM_NOT_ENTERED;
			}
			return PLAYER_DIED;
		}
		if (r.getId().equals(w.getGoodBatRoom().getId())) {
			if (!w.enter(r)) {
				return ROOM_NOT_ENTERED;
			}
			return GOOD_BAT;
		}
		if (r.getId().equals(w.getBadBatRoom().getId())) {
			if (!w.enter(r)) {
				return ROOM_NOT_ENTERED;
			}
			return BAD_BAT;
		}
		if (r.getId().equals(w.getPitRoom().getId())) {
			if (!w.enter(r)) {
				return ROOM_NOT_ENTERED;
			}
			return PLAYER_DIED_PIT;
		}
		if (r.getId().equals(w.getTreasureRoom().getId())) {
			if (!w.enter(r)) {
				return ROOM_NOT_ENTERED;
			}
			return TREASURE;
		}
		if (!w.enter(r)) {
			return ROOM_NOT_ENTERED;
		}
		return ROOM_ENTERED;

	}
}
