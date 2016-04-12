package com.sngk.lab.wumpus.model.service.impl;

import com.sngk.lab.wumpus.model.Player;
import com.sngk.lab.wumpus.model.service.PlayerService;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * This service works with Player table.
 */
public class PlayerServiceImpl extends EbeanBaseServiceImpl<Player, Long> implements PlayerService {

    @Override
    public Player createOne(String name, String login) {
	    Player player = new Player();
        player.setName(name);
        player.setLogin(login);
        return create(player);
    }

    @Override
    public Player findOne(String login) {
		return query().
                where().eq("login", login).findUnique();
    }

    @Override
    public Player findOne(long id) {
        return query().
                setId(id).findUnique();
    }
}
