package com.parking.entity.user;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "saldo")
public class MasterSaldoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal saldo;

	@OneToMany
	@OrderBy("id DESC")
	@JoinColumn(name = "saldoId")
	Set<MasterSaldoHistoriesEntity> histories;
	public MasterSaldoEntity() {

	}

	public MasterSaldoEntity(BigDecimal saldo) {
		this.saldo = saldo;
	}
}
