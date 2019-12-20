package com.iframuroze.tenant.tenantapi.resources;

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

import com.iframuroze.tenant.tenantapi.models.AcessoEmpresaEntity;
import com.iframuroze.tenant.tenantapi.models.EmpresaEntity;
import com.iframuroze.tenant.tenantapi.models.UsuarioEntity;
import com.iframuroze.tenant.tenantapi.repository.AcessoEmpresaRepository;
import com.iframuroze.tenant.tenantapi.repository.EmpresaRepository;
import com.iframuroze.tenant.tenantapi.repository.TenantRepository;
import com.iframuroze.tenant.tenantapi.repository.UsuarioRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class AcessoEmpresaResource {

	@Autowired
	TenantRepository tenantRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	AcessoEmpresaRepository acessoEmpresaRepository;

	@ApiOperation(value = "Retorna Acessos a empresas por usuario")
	@GetMapping("/empresasAutorizadas/{usuarioId}")
	public Iterable<AcessoEmpresaEntity> listaEmpresasDoUsuario(@PathVariable(value = "usuarioId") long usuarioId) {
		UsuarioEntity usuarioEntity = usuarioRepository.findById(usuarioId);
		return acessoEmpresaRepository.findByUsuario(usuarioEntity);
	}

	@ApiOperation(value = "Retorna Usuarios De Uma Empresa")
	@GetMapping("/acessosDaEmpresa/{empresaId}")
	public Iterable<AcessoEmpresaEntity> listaUsuariosDaEmpresa(@PathVariable(value = "empresaId") long empresaId) {
		EmpresaEntity empresaEntity = empresaRepository.findById(empresaId);
		return acessoEmpresaRepository.findByEmpresa(empresaEntity);
	}

	@ApiOperation(value = "Retorna Usuarios De Um tenant")
	@GetMapping("/usuariosTenant/{tenantId}")
	public Iterable<AcessoEmpresaEntity> listaUsuariosDoTenant(@PathVariable(value = "tenantId") long tenantId) {
		return acessoEmpresaRepository.findByTenant(tenantId);
	}

	@ApiOperation(value = "Adiciona uma associacao entre empresa e usuario")
	@PostMapping("/empresaUsuario")
	public AcessoEmpresaEntity SalvaEmpresaDoUsuario(@RequestBody @Valid AcessoEmpresaEntity acessoEmpresaEntity) {
		acessoEmpresaEntity.setTenant(acessoEmpresaEntity.getEmpresa().getTenant().getId_tenant());
		return acessoEmpresaRepository.save(acessoEmpresaEntity);
	}

	@ApiOperation(value = "Exclui uma Associacao entre empresa e usuario")
	@DeleteMapping("/empresaUsuario")
	public void excluiEmpresaDoUsuario(@RequestBody @Valid AcessoEmpresaEntity acessoEmpresaEntity) {
		acessoEmpresaRepository.delete(acessoEmpresaEntity);
	}

	@ApiOperation(value = "Atualiza uma associacao entre empresa e usuario")
	@PutMapping("/empresaUsuario")
	public AcessoEmpresaEntity atualizaEmpresaDoUsuario(@RequestBody @Valid AcessoEmpresaEntity acessoEmpresaEntity) {
		acessoEmpresaEntity.setTenant(acessoEmpresaEntity.getEmpresa().getTenant().getId_tenant());
		return acessoEmpresaRepository.save(acessoEmpresaEntity);
	}

}
