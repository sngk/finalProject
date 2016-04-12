package com.sngk.lab.wumpus.model.service.impl;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import com.sngk.lab.wumpus.model.service.BaseService;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * This is the base service, which is in fact a super class for all other services.
 * Each of services is needed to work with the data layer, using the SQLite database instance.
 */
public abstract class EbeanBaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	// All models have these members
	public static final String ID = "id";

	@Inject
	protected EbeanServer ebeanServer;

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	protected EbeanBaseServiceImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public T create(T entity) {
		ebeanServer.save(entity);
		return entity;
	}

	public List<T> createAll(List<T> entities) {
		ebeanServer.saveAll(entities);
		return entities;
	}

	public T find(ID id) {
		return ebeanServer.find(getPersistentClass()).setId(id).findUnique();
	}

	public T findByQuery(Query<T> query) {
		return query.findUnique();
	}

	public T update(T entity) {
		ebeanServer.update(entity);
		return entity;
	}

	public List<T> updateAll(List<T> entities) {
		ebeanServer.updateAll(entities);
		return entities;
	}

	public void delete(T entity) {
		ebeanServer.delete(entity);
	}

	public void deleteById(ID id) {
		T entity = find(id);
		ebeanServer.delete(entity);
	}

	public void deleteByIds(List<ID> ids) {
		ebeanServer.deleteAll(getPersistentClass(), ids);
	}

	public void deleteByQuery(Query<T> query) {
		List<T> entities = findAllByQuery(query);
		ebeanServer.deleteAll(entities);
	}

	public List<T> findAllByQuery(Query<T> query) {
		return query.findList();
	}

	public List<T> findByExample(T example) {
		Query<T> query = ebeanServer.createQuery(getPersistentClass());
		if (example != null) {
			query.where().iexampleLike(example);
		}
		return query.findList();
	}

	public List<T> findAll() {
		return ebeanServer.find(getPersistentClass()).findList();
	}

	public Query<T> query() {
		return ebeanServer.createQuery(getPersistentClass());
	}

}
