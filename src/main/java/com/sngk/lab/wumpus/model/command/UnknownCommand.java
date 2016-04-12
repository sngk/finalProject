package com.sngk.lab.wumpus.model.command;

import com.sngk.lab.wumpus.controller.WorldController;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */

public class UnknownCommand extends Command {

	public UnknownCommand(String n) {
		super(n);
	}

	@Override
	public Result perform(WorldController w) {
		return Result.UNKNOWN;
	}
}
