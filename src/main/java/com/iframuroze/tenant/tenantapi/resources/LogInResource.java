package com.iframuroze.tenant.tenantapi.resources;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iframuroze.tenant.tenantapi.exceptions.AuthenticationFailedException;
import com.iframuroze.tenant.tenantapi.models.AccessRuleEntity;
import com.iframuroze.tenant.tenantapi.models.UserEntity;
import com.iframuroze.tenant.tenantapi.repository.AccessRuleRepository;
import com.iframuroze.tenant.tenantapi.repository.UserRepository;
import com.iframuroze.tenant.tenantapi.util.Util;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class LogInResource {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccessRuleRepository accessRuleRepository;
		
	
	@ApiOperation(value = "Autentica um usuario pelo seu email e senha e retorna o mapeamento de empresas que este tenha acesso")
	@GetMapping("/login/{userEmail}/{userPassword}")
	public Iterable<AccessRuleEntity> logIn(@PathVariable(value = "userEmail") String userEmail,
			@PathVariable(value = "userPassword") String userPassword, HttpSession session) {
		UserEntity userEntity = new UserEntity();
		try {			
			userEntity = userRepository.findByUserEmailAndUserPassword(userEmail, Util.md5(userPassword));
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		if (userEntity!= null) {
			session.setAttribute("loggedUser", userEntity);
			return accessRuleRepository.findByUser(userEntity);
		} else {
			throw new AuthenticationFailedException("Email ou senha invalidos");
		}

	}
	
	@ApiOperation(value = "Destroi a sessao do usuario logado")
	@PostMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "out";
	}
}
