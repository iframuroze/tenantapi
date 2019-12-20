package com.iframuroze.tenant.tenantapi.resources;

import java.util.List;

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

	@ApiOperation(value = "Retorna uma lista de inquilinos")
	@GetMapping("/tenants")
	public List<TenantEntity> listaTenants() {
		return tenantRepository.findAll();
	}

	@ApiOperation(value = "Retorna um unico inquilino")
	@GetMapping("/tenant/{id}")
	public TenantEntity listaTenantyUnico(@PathVariable(value = "id") long id) {
		return tenantRepository.findById(id);
	}

	@ApiOperation(value = "Salva um inquilino")
	@PostMapping("/tenant")
	public TenantEntity salvaTenant(@RequestBody @Valid TenantEntity tenantEntity) {
		return tenantRepository.save(tenantEntity);
	}

	@ApiOperation(value = "Exclui um inquilino")
	@DeleteMapping("/tenant")
	public void excluiTenant(@RequestBody @Valid TenantEntity tenantEntity) {
		tenantRepository.delete(tenantEntity);
	}

	@ApiOperation(value = "Atualiza um inquilino")
	@PutMapping("/tenant")
	public TenantEntity atualizaTenanty(@RequestBody @Valid TenantEntity tenantEntity) {
		return tenantRepository.save(tenantEntity);
	}

}
