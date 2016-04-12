package com.sngk.lab.wumpus.model.service;

import com.sngk.lab.wumpus.model.Room;

import java.util.List;

/**
 * @author Nikolay Denisenko
 * @version 2015/09/30
 */
public interface RoomService {

    List<Room> findRooms(long layoutId);

}
