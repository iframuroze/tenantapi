package com.iframuroze.tenant.tenantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iframuroze.tenant.tenantapi.models.AcessoEmpresaEntity;
import com.iframuroze.tenant.tenantapi.models.EmpresaEntity;
import com.iframuroze.tenant.tenantapi.models.UsuarioEntity;

public interface AcessoEmpresaRepository extends JpaRepository<AcessoEmpresaEntity, Long> {

	AcessoEmpresaEntity findById(long id);

	Iterable<AcessoEmpresaEntity> findByUsuario(UsuarioEntity usuarioEntity);
	
	Iterable<AcessoEmpresaEntity> findByEmpresa(EmpresaEntity empresaEntity);
	
	Iterable<AcessoEmpresaEntity> findByTenant(Long tenantId);

}
	