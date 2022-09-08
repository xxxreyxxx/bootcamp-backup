package com.parking.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.entity.sysmenu.SysMenuHeader;

public interface AdminMenuRepository extends JpaRepository<SysMenuHeader, Long> {
	List<SysMenuHeader> findAllByAdminRole(Long role);
}
