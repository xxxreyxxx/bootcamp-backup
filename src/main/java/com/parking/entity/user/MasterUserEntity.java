package com.parking.entity.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_user")
public class MasterUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private Long roleId;

	@ManyToOne
	@JoinColumn(name = "saldo_id")
	private MasterSaldoEntity saldo;

	@ManyToOne
	@JoinColumn(name = "master_user_info_id")
	private MasterUserInfoEntity userInfo;

	public MasterUserEntity() {
	}

	
	public MasterUserEntity(String email, String password, Long roleId, MasterSaldoEntity saldo,
			MasterUserInfoEntity userInfo) {
		this.email = email;
		this.password = password;
		this.roleId = roleId;
		this.saldo = saldo;
		this.userInfo = userInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public MasterSaldoEntity getSaldo() {
		return saldo;
	}

	public void setSaldo(MasterSaldoEntity saldo) {
		this.saldo = saldo;
	}

	public MasterUserInfoEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(MasterUserInfoEntity userInfo) {
		this.userInfo = userInfo;
	}
}
