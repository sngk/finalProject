
package com.sngk.lab.wumpus.model.command;

import com.sngk.lab.wumpus.controller.WorldController;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * This class is a super class for all the commands. We've decided to make each command as separate class, not the
 * sub-class as it was before. To avoid future problems of working with subclasses.
 */

public abstract class Command {

	public static final String[] names = {
			"save_game", "save",
			"enter", "e",
			"path", "p",
			"shoot", "s",
			"help", "?",
			"quit", "q"
	};

	public final String target;

	public Command(String n) {
		target = n;
	}

	public abstract Result perform(WorldController w);

	public static Command create(String s) {
		// split on whitespace
		String[] tokens = s.split("\\s+");

		if (tokens.length == 0 || tokens.length > 2)
			return new UnknownCommand(null);

		switch (tokens.length) {
			case 1:
				if (tokens[0].equals("help") || tokens[0].equals("?"))
					return new HelpCommand(null);
				if (tokens[0].equals("quit") || tokens[0].equals("q"))
					return new QuitCommand(null);
				if (tokens[0].equals("save") || tokens[0].equals("save_game"))
					return new SaveCommand(null);
				return new UnknownCommand(null);
			case 2:
				if (tokens[0].equals("enter") || tokens[0].equals("e"))
					return new EnterCommand(tokens[1]);
				if (tokens[0].equals("path") || tokens[0].equals("p"))
					return new PathCommand(tokens[1]);
				if (tokens[0].equals("shoot") || tokens[0].equals("s"))
					return new ShootCommand(tokens[1]);
				return new UnknownCommand(null);
			default:
				return new UnknownCommand(null);
		}
	}

	public static String list() {
		String s = "";
		for (int i = 0; i < names.length; i++) {
			s += names[i];
			if (i < names.length - 1)
				s += ", ";
		}
		return s;
	}
}

