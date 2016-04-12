
package com.sngk.lab.wumpus.model;

import com.avaje.ebean.annotation.PrivateOwned;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */
@Entity
@Table(name = Room.TABLE)
public class Room extends BaseModel implements Node<MazePath> {

	public static final String TABLE = "room";

	@ManyToOne
	@JoinColumn(name = "layout_id")
	private Layout layout;

	@Column(name = "label")
	private String label;

	@PrivateOwned
	@OneToMany(mappedBy = "roomFrom", cascade = CascadeType.ALL)
	private List<MazePath> mazePaths;

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<MazePath> getMazePaths() {
		return mazePaths;
	}

	public void setMazePaths(List<MazePath> mazePaths) {
		this.mazePaths = mazePaths;
	}

	@Transient
	@Override
	public String label() {
		return label;
	}

	@Transient
	@Override
	public List<MazePath> edges() {
		return mazePaths == null ? Collections.<MazePath>emptyList() : mazePaths;
	}
}
