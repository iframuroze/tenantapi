package com.iframuroze.tenant.tenantapi.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="TB_COMPANY")
public class CompanyEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7689745135686855424L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long companyId;
	
	private String companyName;
	
	private String shortCompanyName;
	
	@ManyToOne
	private TenantEntity tenant;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShortCompanyName() {
		return shortCompanyName;
	}

	public void setShortCompanyName(String shortCompanyName) {
		this.shortCompanyName = shortCompanyName;
	}

	public TenantEntity getTenant() {
		return tenant;
	}

	public void setTenant(TenantEntity tenant) {
		this.tenant = tenant;
	}
	
	}
