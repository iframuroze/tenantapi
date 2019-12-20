package com.iframuroze.tenant.tenantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iframuroze.tenant.tenantapi.models.TenantEntity;


public interface TenantRepository extends JpaRepository<TenantEntity, Long>{

	TenantEntity findById(long id);

}
