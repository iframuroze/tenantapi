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

import com.iframuroze.tenant.tenantapi.models.EmpresaEntity;
import com.iframuroze.tenant.tenantapi.models.TenantEntity;
import com.iframuroze.tenant.tenantapi.repository.EmpresaRepository;
import com.iframuroze.tenant.tenantapi.repository.TenantRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class EmpresaResource {

	@Autowired
	TenantRepository tenantRepository;

	@Autowired
	EmpresaRepository empresaRepository;
	
	@ApiOperation(value = "Retorna uma lista de Empresas")
	@GetMapping("/empresas")
	public List<EmpresaEntity> listaEmpresas() {
		return empresaRepository.findAll();
	}

	@ApiOperation(value = "Retorna empresa por id")
	@GetMapping("/empresa/{id}")
	public EmpresaEntity listaEmpresaporID(@PathVariable(value = "id") long id) {
		return empresaRepository.findById(id);
	}

	@ApiOperation(value = "Retorna empresa por inquilino")
	@GetMapping("/empresas/{tenantId}")
	public Iterable<EmpresaEntity> listaEmpresaporTenant(@PathVariable(value = "tenantId") long tenantId) {
		TenantEntity tenantEntity = tenantRepository.findById(tenantId);
		return empresaRepository.findByTenant(tenantEntity);
	}

	@ApiOperation(value = "Salva uma empresa")
	@PostMapping("/empresa")
	public EmpresaEntity salvaEmpresa(@RequestBody @Valid EmpresaEntity empresaEntity) {
		return empresaRepository.save(empresaEntity);
	}

	@ApiOperation(value = "Exclui uma empresa")
	@DeleteMapping("/empresa")
	public void excluiEmpresa(@RequestBody @Valid EmpresaEntity empresaEntity) {
		empresaRepository.delete(empresaEntity);
	}

	@ApiOperation(value = "Atualiza uma empresa")
	@PutMapping("/empresa")
	public EmpresaEntity atualizaEmpresa(@RequestBody @Valid EmpresaEntity empresaEntity) {
		return empresaRepository.save(empresaEntity);
	}

}
