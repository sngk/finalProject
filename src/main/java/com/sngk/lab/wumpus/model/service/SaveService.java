package com.sngk.lab.wumpus.model.service;

import com.sngk.lab.wumpus.model.Save;

import java.util.List;

/**
 * @author Nikolay Denisenko
 * @version 2015/09/29
 */
public interface SaveService {

    Save createOne(long playerId, long layoutId, String name);

    Save updateOne(Save save);

    Save findOne(long saveId);

    List<Save> findAll(long playerId);

}
