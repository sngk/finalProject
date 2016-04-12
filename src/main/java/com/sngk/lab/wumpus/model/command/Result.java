package com.sngk.lab.wumpus.model.command;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * List of enums for all the commands, which return any state.
 */
public enum Result {
	GAME_OVER,
	WUMPUS_KILLED,
	WUMPUS_NOT_KILLED,
	PLAYER_DIED,
	ROOM_ENTERED,
	ROOM_NOT_ENTERED,
	ROOM_NOT_EXISTS,
	PATH_FOUND,
	HELP,
	UNKNOWN,
	GOOD_BAT,
	BAD_BAT,
	PLAYER_DIED_PIT,
	TREASURE,
	WORLD_SAVED,
	WORLD_NOT_SAVED,
}
