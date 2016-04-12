package com.sngk.lab.wumpus.model.command;

import com.sngk.lab.wumpus.controller.WorldController;

import static com.sngk.lab.wumpus.model.command.Result.HELP;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */

public class HelpCommand extends Command {

	public HelpCommand(String n) {
		super(n);
	}

	@Override
	public Result perform(WorldController w) {
		return HELP;
	}
}
