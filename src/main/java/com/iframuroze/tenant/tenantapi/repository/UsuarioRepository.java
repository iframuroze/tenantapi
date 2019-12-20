package com.iframuroze.tenant.tenantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iframuroze.tenant.tenantapi.models.UsuarioEntity;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	UsuarioEntity findById(long id);
	UsuarioEntity findByEmailUsuarioAndSenha(String emailUsuario, String senha);

}
