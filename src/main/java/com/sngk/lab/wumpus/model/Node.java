package com.sngk.lab.wumpus.model;

import java.util.List;

public interface Node<E extends Edge> {
	String label();

	List<E> edges();
}
