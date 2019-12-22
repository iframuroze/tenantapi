package com.iframuroze.tenant.tenantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iframuroze.tenant.tenantapi.models.AccessRuleEntity;
import com.iframuroze.tenant.tenantapi.models.CompanyEntity;
import com.iframuroze.tenant.tenantapi.models.UserEntity;

public interface AccessRuleRepository extends JpaRepository<AccessRuleEntity, Long> {

	AccessRuleEntity findById(long id);

	Iterable<AccessRuleEntity> findByUser(UserEntity userEntity);
	
	Iterable<AccessRuleEntity> findByCompany(CompanyEntity companyEntity);
	
	Iterable<AccessRuleEntity> findByTenant(Long tenantId);

}
	