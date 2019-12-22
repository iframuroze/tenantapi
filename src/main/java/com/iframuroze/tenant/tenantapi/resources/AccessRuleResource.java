package com.iframuroze.tenant.tenantapi.resources;

import java.sql.SQLException;

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

import com.iframuroze.tenant.tenantapi.models.AccessRuleEntity;
import com.iframuroze.tenant.tenantapi.models.CompanyEntity;
import com.iframuroze.tenant.tenantapi.models.UserEntity;
import com.iframuroze.tenant.tenantapi.repository.AccessRuleRepository;
import com.iframuroze.tenant.tenantapi.repository.CompanyRepository;
import com.iframuroze.tenant.tenantapi.repository.TenantRepository;
import com.iframuroze.tenant.tenantapi.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class AccessRuleResource {

	@Autowired
	TenantRepository tenantRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AccessRuleRepository accessRuleRepository;

	@ApiOperation(value = "Retorna Acessos a empresas por usuario: Enviando como parametro um usuario")
	@GetMapping("/userCompanies/{userId}")
	public Iterable<AccessRuleEntity> listUserCompanies(@PathVariable(value = "userId") long userId) {
		UserEntity userEntity = userRepository.findById(userId);
		return accessRuleRepository.findByUser(userEntity);
	}

	@ApiOperation(value = "Retorna Usuarios de uma Empresa")
	@GetMapping("/companyUsers/{companyId}")
	public Iterable<AccessRuleEntity> listCompanyUsers(@PathVariable(value = "companyId") long companyId) {
		CompanyEntity companyEntity = companyRepository.findById(companyId);
		return accessRuleRepository.findByCompany(companyEntity);
	}

	@ApiOperation(value = "Retorna Usuarios de um tenant: Enviando como parametro o id do tenant")
	@GetMapping("/tenantUsers/{tenantId}")
	public Iterable<AccessRuleEntity> listTenantUsers(@PathVariable(value = "tenantId") long tenantId) {
		return accessRuleRepository.findByTenant(tenantId);
	}

	@ApiOperation(value = "Adiciona uma associacao entre empresa e usuario e consequetemente o ID do seu tenant")
	@PostMapping("/saveAccessRule")
	public AccessRuleEntity saveAccessRule(@RequestBody @Valid AccessRuleEntity accessRuleEntity){
		accessRuleEntity.setTenant(accessRuleEntity.getCompany().getTenant().getTenantId());
		return accessRuleRepository.save(accessRuleEntity);
	}

	@ApiOperation(value = "Exclui uma Associacao entre empresa e usuario")
	@DeleteMapping("/deleteAccessRule")
	public void deleteAccessRule(@RequestBody @Valid AccessRuleEntity accessRuleEntity) throws SQLException{
		accessRuleRepository.delete(accessRuleEntity);
	}

	@ApiOperation(value = "Atualiza uma associacao entre empresa e usuario")
	@PutMapping("/updateAccessRule")
	public AccessRuleEntity updateAccessRule(@RequestBody @Valid AccessRuleEntity accessRuleEntity) {
		if(accessRuleRepository.findById(accessRuleEntity.getAccessId())!=null) {			
			accessRuleEntity.setTenant(accessRuleEntity.getCompany().getTenant().getTenantId());
			return accessRuleRepository.save(accessRuleEntity);
		}else {
			throw new EntityNotFoundException("Regra de Acesso Nao Encontrada");
		}
	}

}