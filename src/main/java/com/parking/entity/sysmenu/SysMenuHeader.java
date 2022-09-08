package com.parking.entity.sysmenu;

import java.util.Set;

import javax.persistence.Entity;
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
@Table(name = "sys_menu_header")
public class SysMenuHeader {
	@Id
	private Long id;
	private String header;
	private Long adminRole;

	@OneToMany
	@OrderBy("id ASC")
	@JoinColumn(name = "header_id")
	Set<SysMenuChildren> children;
}
