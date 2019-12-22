package com.iframuroze.tenant.tenantapi.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TB_USER")
public class UserEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5305464627101889220L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userId;
	
	private String userName;
	
	private String userSurname;
	
	@Column(unique=true)
	private String userEmail;
	
	private String userPassword;
	
	/*@ManyToOne
	private List<AccessRuleEntity> accessRuleEntity;*/

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/*public List<AccessRuleEntity> getAccessRuleEntity() {
		return accessRuleEntity;
	}

	public void setAccessRuleEntity(List<AccessRuleEntity> accessRuleEntity) {
		this.accessRuleEntity = accessRuleEntity;
	}*/
}