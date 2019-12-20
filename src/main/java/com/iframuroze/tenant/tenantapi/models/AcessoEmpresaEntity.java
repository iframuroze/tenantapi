package com.iframuroze.tenant.tenantapi.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TB_ACESSO_EMPRESA")
public class AcessoEmpresaEntity implements Serializable{
	
	/**
	 * Entidade que armazena as ligacao entre as empresas de um inquilino
	 * e seus respectivos usuarios
	 */
	private static final long serialVersionUID = -448374020657197877L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Id_acesso;

	@ManyToOne
	private UsuarioEntity usuario;
	
	@ManyToOne
	private EmpresaEntity empresa;
	
	private char active;
	
	private int acessLevel;
	
	private long tenant;


	public long getId_acesso() {
		return Id_acesso;
	}

	public void setId_acesso(long id_acesso) {
		Id_acesso = id_acesso;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public EmpresaEntity getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaEntity empresa) {
		this.empresa = empresa;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public int getAcessLevel() {
		return acessLevel;
	}

	public void setAcessLevel(int acessLevel) {
		this.acessLevel = acessLevel;
	}

	public long getTenant() {
		return tenant;
	}

	public void setTenant(long tenant) {
		this.tenant = tenant;
	}

	
}
