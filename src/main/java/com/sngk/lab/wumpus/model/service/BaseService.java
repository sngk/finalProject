package com.sngk.lab.wumpus.model.service;


import java.io.Serializable;
import java.util.List;

/**
 * @author Nikolay Denisenko
 * @version 2015/09/30
 */
public interface BaseService<T, ID extends Serializable> {

    Class<T> getPersistentClass();

    T create(T entity);

    List<T> createAll(List<T> entities);

    T find(ID id);

    T update(T entity);

    List<T> updateAll(List<T> entities);

    void delete(T entity);

    void deleteById(ID id);

    void deleteByIds(List<ID> ids);

    List<T> findByExample(T example);

    List<T> findAll();

}
