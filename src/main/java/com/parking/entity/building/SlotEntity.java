package com.parking.entity.building;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "slot")
public class SlotEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String slotName;
	private Long slotType;
	private Long status;

	@ManyToOne
	@JoinColumn(name = "floor_id")
	private MasterFloorEntity floor;
}
