package com.andrefilho99.controlCProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Information {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFORMATION")
	@SequenceGenerator(name = "SEQ_INFORMATION", sequenceName = "public.id_seq_information", allocationSize = 1, initialValue = 1)
	@Column(name = "id")
	private Integer id;
	
	@Column(updatable = false)
	private String value;
	
	@Column(updatable = false)
	private String hash;
	
	@Column(updatable = false)
	private String key;

	public Information() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}