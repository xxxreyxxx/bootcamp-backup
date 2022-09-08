package com.parking.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.entity.sysmenu.SysMenuHeader;
import com.parking.service.admin.impl.AdminServiceImpl;

@RestController
public class SysMenuController {
	
	@Autowired
	AdminServiceImpl adminServiceImpl;
	
	@GetMapping("/menu")
	public ResponseEntity<List<SysMenuHeader>> getListMenu(@RequestParam("role") Long role){
		try {
			return new ResponseEntity<>(adminServiceImpl.getlistMenu(role),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
