package com.iframuroze.tenant.tenantapi.resources;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iframuroze.tenant.tenantapi.models.TenantEntity;
import com.iframuroze.tenant.tenantapi.repository.TenantRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class TenantResource {

	@Autowired
	TenantRepository tenantRepository;

	@ApiOperation(value = "Retorna uma lista de Tenants")
	@GetMapping("/listTenants")
	public List<TenantEntity> lisTenants() {
		return tenantRepository.findAll();
	}

	@ApiOperation(value = "Retorna um unico Tenant. Enviando como parametro o seu ID")
	@GetMapping("/lisTenantById/{id}")
	public TenantEntity listTenantById(@PathVariable(value = "id") long id) {
		return tenantRepository.findById(id);
	}

	@ApiOperation(value = "Salva um Tenant")
	@PostMapping("/saveTenant")
	public TenantEntity saveTenant(@RequestBody @Valid TenantEntity tenantEntity) throws SQLException {
		return tenantRepository.save(tenantEntity);
	}

	@ApiOperation(value = "Exclui um Tenant")
	@DeleteMapping("/deleteTenant")
	public void deleteTenant(@RequestBody @Valid TenantEntity tenantEntity) throws SQLException {
		tenantRepository.delete(tenantEntity);
	}

	@ApiOperation(value = "Atualiza um inquilino")
	@PutMapping("/updateTenant")
	public TenantEntity updateTenanty(@RequestBody @Valid TenantEntity tenantEntity) throws SQLException{
		if(tenantRepository.findById(tenantEntity.getTenantId())!=null) {			
			return tenantRepository.save(tenantEntity);
		}else {
			throw new EntityNotFoundException("Tenant nao encontrado");
		}
	}

}
