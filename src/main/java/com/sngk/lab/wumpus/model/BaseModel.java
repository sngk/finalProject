package com.sngk.lab.wumpus.model;

import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 */
@MappedSuperclass
public class BaseModel implements Identifiable<Long>, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;

	@WhenCreated
	@Column(name = "creation_date", nullable = false, updatable = false, insertable = true)
	protected DateTime creationDate;

	@WhenModified
	@Column(name = "last_update", nullable = false)
	protected DateTime lastUpdate;

	@Version
	@Column(name = "version", nullable = false)
	protected Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public DateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(DateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BaseModel)) {
			return false;
		}
		BaseModel baseModel = (BaseModel) o;
		return Objects.equals(id, baseModel.id) &&
				Objects.equals(creationDate, baseModel.creationDate) &&
				Objects.equals(lastUpdate, baseModel.lastUpdate) &&
				Objects.equals(version, baseModel.version);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, creationDate, lastUpdate, version);
	}

	@Override
	public String toString() {
		return "BaseModel{" +
				"id=" + id +
				", creationDate=" + creationDate +
				", lastUpdate=" + lastUpdate +
				", version=" + version +
				"} " + super.toString();
	}
}
