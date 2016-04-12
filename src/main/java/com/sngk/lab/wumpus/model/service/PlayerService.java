package com.sngk.lab.wumpus.model.service;

import com.sngk.lab.wumpus.model.Player;

/**
 * @author Nikolay Denisenko
 * @version 2015/09/29
 */
public interface PlayerService {

    Player createOne(String name, String login);

    Player findOne(String login);

    Player findOne(long id);
}
