package com.iframuroze.tenant.tenantapi.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="TB_EMPRESA")
public class EmpresaEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7689745135686855424L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Id_empresa;
	
	private String name_empresa;
	
	private String shortName_empresa;
	
	@ManyToOne
	private TenantEntity tenant;

	public long getId_empresa() {
		return Id_empresa;
	}

	public void setId_empresa(long id_empresa) {
		Id_empresa = id_empresa;
	}

	public String getName_empresa() {
		return name_empresa;
	}

	public void setName_empresa(String name_empresa) {
		this.name_empresa = name_empresa;
	}

	public String getShortName_empresa() {
		return shortName_empresa;
	}

	public void setShortName_empresa(String shortName_empresa) {
		this.shortName_empresa = shortName_empresa;
	}

	public TenantEntity getTenant() {
		return tenant;
	}

	public void setTenant(TenantEntity tenant) {
		this.tenant = tenant;
	}
	
	}
