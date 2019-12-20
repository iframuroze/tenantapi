package com.iframuroze.tenant.tenantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iframuroze.tenant.tenantapi.models.EmpresaEntity;
import com.iframuroze.tenant.tenantapi.models.TenantEntity;


public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long>{

	EmpresaEntity findById(long id);
	
	Iterable<EmpresaEntity> findByTenant(TenantEntity tenantEntity);
	

}
