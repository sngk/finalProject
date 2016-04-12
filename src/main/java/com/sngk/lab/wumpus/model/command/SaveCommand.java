package com.sngk.lab.wumpus.model.command;

import com.sngk.lab.wumpus.controller.WorldController;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */
public class SaveCommand extends Command {

	public SaveCommand(String n) {
		super(n);
	}

	@Override
	public Result perform(WorldController w) {
		if (w.save()) {
			return Result.WORLD_SAVED;
		}
		return Result.WORLD_NOT_SAVED;
	}
}
