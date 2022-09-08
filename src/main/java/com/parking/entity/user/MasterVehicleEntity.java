package com.parking.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "master_vehicle")
public class MasterVehicleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String vehicleName;
	private String vehicleType;
	private String licensePlate;
	@Column(name = "master_user_info_id")
	private Long masterUserInfoId;
	private Long status;
}
