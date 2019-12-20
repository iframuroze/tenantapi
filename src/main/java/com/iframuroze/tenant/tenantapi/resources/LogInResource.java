package com.iframuroze.tenant.tenantapi.resources;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iframuroze.tenant.tenantapi.models.AcessoEmpresaEntity;
import com.iframuroze.tenant.tenantapi.models.UsuarioEntity;
import com.iframuroze.tenant.tenantapi.repository.AcessoEmpresaRepository;
import com.iframuroze.tenant.tenantapi.repository.UsuarioRepository;
import com.iframuroze.tenant.tenantapi.util.Util;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class LogInResource {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AcessoEmpresaRepository acessoEmpresaRepository;
		
	
	@ApiOperation(value = "Autentica um usuario pelo seu email e senha e retorna o mapeamento de empresas que este tenha acesso")
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
