package com.iframuroze.tenant.tenantapi.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TB_TENANT")
public class TenantEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5393950244686136327L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Id_tenant;
	
	private String name;
	
	private String shortName;
	
	@OneToMany
	private List<EmpresaEntity> empresaEntity;

	public long getId_tenant() {
		return Id_tenant;
	}

	public void setId_tenant(long id_tenant) {
		Id_tenant = id_tenant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<EmpresaEntity> getEmpresaEntity() {
		return empresaEntity;
	}

	public void setEmpresaEntity(List<EmpresaEntity> empresaEntity) {
		this.empresaEntity = empresaEntity;
	}
	
	

}
