package com.andrefilho99.controlCProject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="master_key", schema = "public")
public class MasterKey {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MASTER_KEY")
	@SequenceGenerator(name = "SEQ_MASTER_KEY", sequenceName = "public.id_seq_master_key", allocationSize = 1, initialValue = 1)
	@Column(name = "id_master_key")
	private Integer id;
	
	@Column(updatable = false)
	private String key;
	
	@Column(updatable = false)
	private String label;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "masterKey")
	private List<Information> infos = new ArrayList<Information>();
	
	public MasterKey() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Information> getInfos() {
		return infos;
	}

	public void setInfos(List<Information> infos) {
		this.infos = infos;
	}
}
