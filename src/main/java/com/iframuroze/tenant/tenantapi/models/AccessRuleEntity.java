package com.iframuroze.tenant.tenantapi.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TB_ACCESS_RULE")
public class AccessRuleEntity implements Serializable{
	
	/**
	 * Entidade que armazena as ligacao entre as empresas de um inquilino
	 * e seus respectivos usuarios
	 */
	private static final long serialVersionUID = -448374020657197877L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long accessId;

	@ManyToOne
	private UserEntity user;
	
	@ManyToOne
	private CompanyEntity company;
	
	private char active;
	
	private int accessLevel;
	
	private long tenant;


	public long getAccessId() {
		return accessId;
	}

	public void setAccessId(long accessId) {
		this.accessId = accessId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public long getTenant() {
		return tenant;
	}

	public void setTenant(long tenant) {
		this.tenant = tenant;
	}

}
