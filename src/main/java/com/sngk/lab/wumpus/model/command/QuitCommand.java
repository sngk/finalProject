package com.sngk.lab.wumpus.model.command;

import com.sngk.lab.wumpus.controller.WorldController;

import static com.sngk.lab.wumpus.model.command.Result.GAME_OVER;
/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */

public class QuitCommand extends Command {

	public QuitCommand(String n) {
		super(n);
	}

	@Override
	public Result perform(WorldController w) {
		return GAME_OVER;
	}
}
