package com.iframuroze.tenant.tenantapi.resources;

import java.util.List;

import javax.servlet.http.HttpSession;
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
import com.iframuroze.tenant.tenantapi.models.TenantEntity;
import com.iframuroze.tenant.tenantapi.models.UsuarioEntity;
import com.iframuroze.tenant.tenantapi.repository.AcessoEmpresaRepository;
import com.iframuroze.tenant.tenantapi.repository.EmpresaRepository;
import com.iframuroze.tenant.tenantapi.repository.TenantRepository;
import com.iframuroze.tenant.tenantapi.repository.UsuarioRepository;
import com.iframuroze.tenant.tenantapi.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class TenantResource {

	@Autowired
	TenantRepository tenantRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AcessoEmpresaRepository acessoEmpresaRepository;
	
	HttpSession session;

	/*
	 * INQUILINO
	 */
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

	/*
	 * EMPRESA
	 */

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

	/*
	 * USUARIO
	 */

	@ApiOperation(value = "Retorna uma lista de usuarios")
	@GetMapping("/usuarios")
	public List<UsuarioEntity> listaUsuarios() {
		return usuarioRepository.findAll();
	}

	@ApiOperation(value = "Retorna usuario por id")
	@GetMapping("/usuario/{id}")
	public UsuarioEntity listaUsusarioporID(@PathVariable(value = "id") long id) {
		return usuarioRepository.findById(id);
	}

	@ApiOperation(value = "Salva uma Usuario")
	@PostMapping("/usuario")
	public UsuarioEntity salvaUsuario(@RequestBody @Valid UsuarioEntity usuarioEntity) {
		usuarioEntity.setSenha(Util.md5(usuarioEntity.getSenha()));
		return usuarioRepository.save(usuarioEntity);
	}

	@ApiOperation(value = "Exclui um usuario")
	@DeleteMapping("/usuario")
	public void excluiUsuario(@RequestBody @Valid UsuarioEntity usuarioEntity) {
		usuarioRepository.delete(usuarioEntity);
	}

	@ApiOperation(value = "Atualiza um usuario")
	@PutMapping("/usuario/{emailUsuario}")
	public UsuarioEntity atualizaUsuario(@RequestBody @Valid UsuarioEntity usuarioEntity,
			@PathVariable(value = "emailUsuario") String emailUsuario) {

		UsuarioEntity oldUsuario = usuarioRepository.findById(usuarioEntity.getIdUsuario());

		if (oldUsuario.getEmailUsuario().equals(emailUsuario)) {

			usuarioEntity.setSenha(Util.md5(usuarioEntity.getSenha()));
			return usuarioRepository.save(usuarioEntity);
		} else {
			System.out.println("Email de confirmacao nao conpativel");
			return oldUsuario;
		}
	}
	
	
	/*
	 * ACESSO_EMPRESA
	 */
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

	/*
	 * LogIN
	 */
	@ApiOperation(value = "Autentica um usuario e retorna o mapeamento de empresas que tenha acesso")
	@GetMapping("/login/{emailUsuario}/{senha}")
	public Iterable<AcessoEmpresaEntity> efectuarLogIn(@PathVariable(value = "emailUsuario") String emailUsuario,
			@PathVariable(value = "senha") String senha, HttpSession session) {
		UsuarioEntity usuarioEntity = usuarioRepository.findByEmailUsuarioAndSenha(emailUsuario, Util.md5(senha));
		if (usuarioEntity != null) {
			session.setAttribute("usuarioLogado", usuarioEntity);
			return acessoEmpresaRepository.findByUsuario(usuarioEntity);
		} else {
			return null;
		}

	}
	
	@ApiOperation(value = "Destroi a sessao do usuario logado")
	@PostMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "out";
	}
}
