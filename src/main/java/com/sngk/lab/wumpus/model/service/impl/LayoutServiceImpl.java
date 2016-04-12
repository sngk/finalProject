package com.sngk.lab.wumpus.model.service.impl;

import com.sngk.lab.wumpus.model.Layout;
import com.sngk.lab.wumpus.model.MazePath;
import com.sngk.lab.wumpus.model.Room;
import com.sngk.lab.wumpus.model.service.LayoutService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * This service works with layout table.
 */
public class LayoutServiceImpl extends EbeanBaseServiceImpl<Layout, Long> implements LayoutService {

	@Override
	public Layout createDefault() {
		Map<String, Room> rooms = Arrays.stream(nodes).map(node -> {
			Room room = new Room();
			room.setLabel(node);
			room.setMazePaths(new ArrayList<>());
			return room;
		}).collect(Collectors.toMap(Room::getLabel, Function.identity()));

		Arrays.stream(edges).forEach(edge -> {
			MazePath mazePath = new MazePath();
			mazePath.setRoomFrom(rooms.get(edge[0]));
			mazePath.setRoomTo(rooms.get(edge[1]));
			mazePath.setDistance(Integer.parseInt(edge[2]));

			rooms.get(edge[0]).getMazePaths().add(mazePath);
		});

		return createLayout("default", rooms);
	}

	@Override
	public Layout createLayout(String name, Map<String, Room> rooms) {
		Layout layout = new Layout();
		layout.setName(name);
		layout.setRooms(rooms);

		rooms.values().stream().
				forEach(room -> room.setLayout(layout));

		return create(layout);
	}

	@Override
	public Layout findOne(long layoutId) {
		return query().setId(layoutId).
				fetch("rooms").
				fetch("rooms.mazePaths").
				fetch("rooms.mazePaths.roomFrom").
				fetch("rooms.mazePaths.roomTo").
				findUnique();
	}

	@Override
	public Layout findDefault() {
		return query().
				fetch("rooms").
				fetch("rooms.mazePaths").
				fetch("rooms.mazePaths.roomFrom").
				fetch("rooms.mazePaths.roomTo").
				where().eq("name", "default").findUnique();
	}

	@Override
	public boolean isDefaultExists() {
		return query().where().eq("name", "default").findRowCount() == 1;
	}

	// Room layout is represented as a node list and an edge list.
	// Nodes (rooms) are labelled 1 to 20. Each node has 3 edges.
	// As in the original game, the layout is isomorphic to the vertices
	// of dodecahedron.

	static String[] nodes = {

			"1",
			"2",
			"3",
			"4",
			"5",
			"6",
			"7",
			"8",
			"9",
			"10",
			"11",
			"12",
			"13",
			"14",
			"15",
			"16",
			"17",
			"18",
			"19",
			"20",
	};

	// Edge format is <from, to, distance>
	// Because we are using an array, the types of all elements must be the same.
	// Hence distance is a string, not an int.
	static String[][] edges = {
			{"1", "11", "1"},
			{"1", "10", "1"},
			{"1", "2", "1"},
			{"2", "12", "1"},
			{"2", "1", "1"},
			{"2", "3", "1"},
			{"3", "13", "1"},
			{"3", "2", "1"},
			{"3", "4", "1"},
			{"4", "14", "1"},
			{"4", "13", "1"},
			{"4", "5", "1"},
			{"5", "15", "1"},
			{"5", "4", "1"},
			{"5", "6", "1"},
			{"6", "16", "1"},
			{"6", "5", "1"},
			{"6", "7", "1"},
			{"7", "17", "1"},
			{"7", "6", "1"},
			{"7", "8", "1"},
			{"8", "18", "1"},
			{"8", "7", "1"},
			{"8", "9", "1"},
			{"9", "19", "1"},
			{"9", "8", "1"},
			{"9", "10", "1"},
			{"10", "20", "1"},
			{"10", "9", "1"},
			{"10", "1", "1"},
			{"11", "1", "1"},
			{"11", "19", "1"},
			{"11", "13", "1"},
			{"12", "2", "1"},
			{"12", "20", "1"},
			{"12", "14", "1"},
			{"13", "3", "1"},
			{"13", "11", "1"},
			{"13", "15", "1"},
			{"14", "4", "1"},
			{"14", "12", "1"},
			{"14", "16", "1"},
			{"15", "5", "1"},
			{"15", "13", "1"},
			{"15", "17", "1"},
			{"16", "6", "1"},
			{"16", "14", "1"},
			{"16", "18", "1"},
			{"17", "7", "1"},
			{"17", "15", "1"},
			{"17", "19", "1"},
			{"18", "8", "1"},
			{"18", "16", "1"},
			{"18", "20", "1"},
			{"19", "9", "1"},
			{"19", "17", "1"},
			{"19", "11", "1"},
			{"20", "10", "1"},
			{"20", "18", "1"},
			{"20", "12", "1"},
	};
}
