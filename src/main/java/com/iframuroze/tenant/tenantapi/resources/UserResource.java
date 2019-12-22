package com.iframuroze.tenant.tenantapi.resources;

import java.sql.SQLException;
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

import com.iframuroze.tenant.tenantapi.exceptions.ConfirmationEmailException;
import com.iframuroze.tenant.tenantapi.models.UserEntity;
import com.iframuroze.tenant.tenantapi.repository.UserRepository;
import com.iframuroze.tenant.tenantapi.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Tenant")
public class UserResource {
	
	@Autowired
	UserRepository userRepository;
	

	@ApiOperation(value = "Retorna uma lista de todos Usuarios")
	@GetMapping("/listUsers")
	public List<UserEntity> listUsers() {
		return userRepository.findAll();
	}

	@ApiOperation(value = "Retorna unico usuario pelo seu ID")
	@GetMapping("/listUserById/{id}")
	public UserEntity listUserById(@PathVariable(value = "id") long id) {
		return userRepository.findById(id);
	}

	@ApiOperation(value = "Salva uma Usuario")
	@PostMapping("/saveUser")
	public UserEntity saveUser(@RequestBody @Valid UserEntity userEntity) throws SQLException {
		userEntity.setUserPassword(Util.md5(userEntity.getUserPassword()));
		return userRepository.save(userEntity);
	}

	@ApiOperation(value = "Exclui um usuario")
	@DeleteMapping("/deleteUser")
	public void deleteUser(@RequestBody @Valid UserEntity userEntity) throws SQLException {
		userRepository.delete(userEntity);
	}

	@ApiOperation(value = "Atualiza um usuario")
	@PutMapping("/updateUser/{userEmail}")
	public UserEntity updateUser(@RequestBody @Valid UserEntity userEntity,
			@PathVariable(value = "userEmail") String userEmail) throws SQLException {

		UserEntity oldUser = userRepository.findById(userEntity.getUserId());

		if (oldUser.getUserEmail().equals(userEmail)) {

			userEntity.setUserPassword(Util.md5(userEntity.getUserPassword()));
			return userRepository.save(userEntity);
		} else {
			throw new ConfirmationEmailException("Email de confirmacao invalido");
		}
	}
	
}
