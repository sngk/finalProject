package com.sngk.lab.wumpus.model.service.impl;

import com.sngk.lab.wumpus.model.MazeRunner;
import com.sngk.lab.wumpus.model.Room;
import com.sngk.lab.wumpus.model.Save;
import com.sngk.lab.wumpus.model.service.LayoutService;
import com.sngk.lab.wumpus.model.service.PlayerService;
import com.sngk.lab.wumpus.model.service.RoomService;
import com.sngk.lab.wumpus.model.service.SaveService;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * This service works with Save table.
 */
public class SaveServiceImpl extends EbeanBaseServiceImpl<Save, Long> implements SaveService {

    @Inject
    private LayoutService layoutService;

    @Inject
    private PlayerService playerService;

    @Inject
    private RoomService roomService;

    @Override
    public Save createOne(long playerId, long layoutId, String name) {
	    Save save = new Save();
        save.setName(name);
        save.setLayout(layoutService.findOne(layoutId));
        save.setPlayer(playerService.findOne(playerId));

        //init game 6 objects
        List<Room> rooms = roomService.findRooms(layoutId);
        Set<Integer> except = new HashSet<>();

        //init maze runner
        MazeRunner mazeRunner = new MazeRunner();
        mazeRunner.setHealth(1);
        mazeRunner.setRoom(chooseRandomRoom(rooms, except));
        save.setMazeRunner(mazeRunner);

        //init wumpus room
        save.setWumpusRoom(chooseRandomRoom(rooms, except));

        //init bad bat room
        save.setBadBatRoom(chooseRandomRoom(rooms, except));

        //init good bat room
        save.setGoodBatRoom(chooseRandomRoom(rooms, except));

        //init treasure bat room
        save.setTreasureRoom(chooseRandomRoom(rooms, except));

        //init pit bat room
        save.setPitRoom(chooseRandomRoom(rooms, except));

        return create(save);
    }

    private Room chooseRandomRoom(List<Room> rooms, Set<Integer> except) {
        Random rng = new Random();
        Integer next;
        do {
            next = rng.nextInt(rooms.size());
        } while (except.contains(next));
        except.add(next);
        return rooms.get(next.intValue());
    }

    @Override
    public Save updateOne(Save save) {
        return update(save);
    }

    @Override
    public Save findOne(long saveId) {
        return query().setId(saveId).
                fetch("layout").
                fetch("layout.rooms").
                fetch("layout.rooms.mazePaths").
                fetch("layout.rooms.mazePaths.roomFrom").
                fetch("layout.rooms.mazePaths.roomTo").
                fetch("mazeRunner").
                fetch("mazeRunner.room").
                fetch("mazeRunner.room.mazePaths").
                fetch("mazeRunner.room.mazePaths.roomFrom").
                fetch("mazeRunner.room.mazePaths.roomTo").
                fetch("wumpusRoom").
                fetch("wumpusRoom.mazePaths").
                fetch("wumpusRoom.mazePaths.roomFrom").
                fetch("wumpusRoom.mazePaths.roomTo").
                fetch("goodBatRoom").
                fetch("goodBatRoom.mazePaths").
                fetch("goodBatRoom.mazePaths.roomFrom").
                fetch("goodBatRoom.mazePaths.roomTo").
                fetch("badBatRoom").
                fetch("badBatRoom.mazePaths").
                fetch("badBatRoom.mazePaths.roomFrom").
                fetch("badBatRoom.mazePaths.roomTo").
                fetch("pitRoom").
                fetch("pitRoom.mazePaths").
                fetch("pitRoom.mazePaths.roomFrom").
                fetch("pitRoom.mazePaths.roomTo").
                fetch("treasureRoom").
                fetch("treasureRoom.mazePaths").
                fetch("treasureRoom.mazePaths.roomFrom").
                fetch("treasureRoom.mazePaths.roomTo").
                findUnique();
    }

    @Override
    public List<Save> findAll(long playerId) {
        return query().setDisableLazyLoading(true).where().eq("player.id", playerId).findList();
    }
}
