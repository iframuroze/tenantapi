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

import com.iframuroze.tenant.tenantapi.models.CompanyEntity;
import com.iframuroze.tenant.tenantapi.models.TenantEntity;
import com.iframuroze.tenant.tenantapi.repository.CompanyRepository;
import com.iframuroze.tenant.tenantapi.repository.TenantRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class CompanyResource {

	@Autowired
	TenantRepository tenantRepository;

	@Autowired
	CompanyRepository companyRepository;
	
	@ApiOperation(value = "Retorna uma lista de Empresas sem nenhum parametro")
	@GetMapping("/listCompanies")
	public List<CompanyEntity> listCompanies() throws SQLException{
		return companyRepository.findAll();
	}

	@ApiOperation(value = "Retorna a entidade empresa: enviando como parametro o seu ID")
	@GetMapping("/listCompanyById/{id}")
	public CompanyEntity listCompanyById(@PathVariable(value = "id") long id) throws SQLException {
		return companyRepository.findById(id);
	}

	@ApiOperation(value = "Retorna uma lista de Empresas que pertencam a um tenant: enviando como parametro o tenant")
	@GetMapping("/listTenantCompanies/{tenantId}")
	public Iterable<CompanyEntity> listTenantCompaniesByTenantId(@PathVariable(value = "tenantId") long tenantId) throws SQLException {
		TenantEntity tenantEntity = tenantRepository.findById(tenantId);
		return companyRepository.findByTenant(tenantEntity);
	}

	@ApiOperation(value = "Salva uma empresa")
	@PostMapping("/saveCompany")
	public CompanyEntity saveCompany(@RequestBody @Valid CompanyEntity companyEntity) throws SQLException{
		return companyRepository.save(companyEntity);
	}

	@ApiOperation(value = "Exclui uma empresa")
	@DeleteMapping("/deleteCompany")
	public void deleteCompany(@RequestBody @Valid CompanyEntity companyEntity) throws SQLException {
		companyRepository.delete(companyEntity);
	}

	@ApiOperation(value = "Atualiza os detelhes de uma empresa")
	@PutMapping("/updateCompany")
	public CompanyEntity updateCompany(@RequestBody @Valid CompanyEntity companyEntity) throws SQLException {
		if(companyRepository.findById(companyEntity.getCompanyId())!=null) {			
			return companyRepository.save(companyEntity);
		}else {
			throw new EntityNotFoundException("Empresa Nao Encontrada");
		}
	}

}
