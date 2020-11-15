package com.andrefilho99.controlCProject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="information", schema = "public")
public class Information {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INFORMATION")
	@SequenceGenerator(name = "SEQ_INFORMATION", sequenceName = "public.id_seq_information", allocationSize = 1, initialValue = 1)
	@Column(name = "id_info")
	private Integer id;
	
	@Column(updatable = false)
	private String value;
	
	@Column(updatable = false)
	private String hash;
	
	@Column(updatable = false)
	private String key;
	
	@Column(updatable = false)
	private String masterKey;
	
	@Column(updatable = false)
	private boolean isLimited;
	
	@Column(updatable = true)
	private int remainingUses;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date lastUse;
	
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

	public String getMasterKey() {
		return masterKey;
	}
	
	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}
	
	public void setIsLimited(boolean isLimited) {
		this.isLimited = isLimited;
	}
	
	public boolean getIsLimited() {
		return isLimited;
	}

	public void setRemainingUses(int remainingUses) {
		this.remainingUses = remainingUses;
	}
	
	public int getRemainingUses() {
		return remainingUses;
	}
	
	public void setLastUse(Date lastUse) {
		this.lastUse = lastUse;
	}
	
	public Date getLastUse() {
		return lastUse;
	}
	
}
