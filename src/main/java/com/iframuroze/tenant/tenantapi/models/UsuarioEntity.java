package com.iframuroze.tenant.tenantapi.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TB_USUARIO")
public class UsuarioEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5305464627101889220L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long IdUsuario;
	
	private String nameUsuario;
	
	private String sobrenomeUsuario;
	
	@Column(unique=true)
	private String emailUsuario;
	
	private String senha;
	
	
	@OneToMany
	private List<AcessoEmpresaEntity> acessoEmpresaEntity;

	public long getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		IdUsuario = idUsuario;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}

	public String getSobrenomeUsuario() {
		return sobrenomeUsuario;
	}

	public void setSobrenomeUsuario(String sobrenomeUsuario) {
		this.sobrenomeUsuario = sobrenomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<AcessoEmpresaEntity> getAcessoEmpresaEntity() {
		return acessoEmpresaEntity;
	}

	public void setAcessoEmpresaEntity(List<AcessoEmpresaEntity> acessoEmpresaEntity) {
		this.acessoEmpresaEntity = acessoEmpresaEntity;
	}

}
