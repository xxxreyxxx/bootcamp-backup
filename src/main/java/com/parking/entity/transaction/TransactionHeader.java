package com.parking.entity.transaction;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.parking.entity.building.SlotEntity;
import com.parking.entity.user.MasterVehicleEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "transaction_header")
public class TransactionHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date bookingDate;
	private Date expiredBooking;
	private Long status;

	@ManyToOne
	@JoinColumn(name = "master_vehicle_id")
	private MasterVehicleEntity vehicle;

	@ManyToOne
	@JoinColumn(name = "slot_id")
	private SlotEntity slot;
}
