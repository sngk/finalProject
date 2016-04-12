package com.sngk.lab.wumpus.model.service;


import com.sngk.lab.wumpus.model.Layout;
import com.sngk.lab.wumpus.model.Room;

import java.util.Map;

/**
 * @author Nikolay Denisenko
 * @version 2015/09/30
 */
public interface LayoutService {

    Layout createDefault();

    Layout createLayout(String name, Map<String, Room> rooms);

    Layout findOne(long layoutId);

    Layout findDefault();

    boolean isDefaultExists();
}
