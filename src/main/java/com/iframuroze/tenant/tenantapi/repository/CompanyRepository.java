package com.iframuroze.tenant.tenantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iframuroze.tenant.tenantapi.models.CompanyEntity;
import com.iframuroze.tenant.tenantapi.models.TenantEntity;


public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>{

	CompanyEntity findById(long id);
	
	Iterable<CompanyEntity> findByTenant(TenantEntity tenantEntity);
	

}
