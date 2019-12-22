package com.iframuroze.tenant.tenantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iframuroze.tenant.tenantapi.models.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findById(long id);
	UserEntity findByUserEmailAndUserPassword(String userEmail, String userPassword);

}
