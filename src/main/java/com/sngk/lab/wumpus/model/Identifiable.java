package com.sngk.lab.wumpus.model;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */
public interface Identifiable<T> {

	T getId();

	void setId(T id);
}
