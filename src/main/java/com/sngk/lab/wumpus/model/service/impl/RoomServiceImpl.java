package com.sngk.lab.wumpus.model.service.impl;

import com.sngk.lab.wumpus.model.Room;
import com.sngk.lab.wumpus.model.service.RoomService;

import java.util.List;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * this service works with Room table.
 */
public class RoomServiceImpl extends EbeanBaseServiceImpl<Room, Long> implements RoomService {

	@Override
	public List<Room> findRooms(long layoutId) {
		return query().where().eq("layout.id", layoutId).findList();
	}

}
