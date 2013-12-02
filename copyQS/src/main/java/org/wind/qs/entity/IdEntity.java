package org.wind.qs.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment,only for mysql,sqlserver
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
