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

import com.iframuroze.tenant.tenantapi.models.UsuarioEntity;
import com.iframuroze.tenant.tenantapi.repository.UsuarioRepository;
import com.iframuroze.tenant.tenantapi.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class UsuarioResource {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	

	@ApiOperation(value = "Retorna uma lista de todos Usuarios")
	@GetMapping("/usuarios")
	public List<UsuarioEntity> listaUsuarios() {
		return usuarioRepository.findAll();
	}

	@ApiOperation(value = "Retorna unico usuario pelo seu ID")
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
	
}
