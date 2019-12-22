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
	private long tenantId;
	
	private String tenantName;
	
	private String shortTenantName;
	
	/*@OneToMany
	private List<CompanyEntity> companyEntity;
	*/

	public long getTenantId() {
		return tenantId;
	}

	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getShortTenantName() {
		return shortTenantName;
	}

	public void setShortTenantName(String shortTenantName) {
		this.shortTenantName = shortTenantName;
	}

	/*public List<CompanyEntity> getCompanyEntity() {
		return companyEntity;
	}

	public void setCompanyEntity(List<CompanyEntity> companyEntity) {
		this.companyEntity = companyEntity;
	}*/

}
