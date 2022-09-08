package com.parking.entity.sysmenu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "sys_menu_children")
public class SysMenuChildren {
	@Id
	private Long id;
	private String name;
	private String path;
	private String icon;
	@Column(name = "header_id")
	private Long headerId;
	private Long adminRole;

	public SysMenuChildren() {

	}

	public SysMenuChildren(String name, String path, String icon) {
		this.name = name;
		this.path = path;
		this.icon = icon;
	}
}
