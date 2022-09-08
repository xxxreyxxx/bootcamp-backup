package com.parking.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.parking.dto.user.UserDto;
import com.parking.entity.user.MasterSaldoEntity;
import com.parking.entity.user.MasterUserEntity;
import com.parking.entity.user.MasterUserInfoEntity;
import com.parking.repository.user.MasterSaldoRepository;
import com.parking.repository.user.MasterUserInfoRepository;
import com.parking.repository.user.MasterUserRepository;
import com.parking.service.user.UserService;
import com.parking.service.user.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
	public UserServiceImplTest() {
	}

	@TestConfiguration
	static class UserServiceImplTestContextConfiguration {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	private UserService userService;

	@MockBean
	private MasterUserRepository userRepository;

	@MockBean
	private MasterUserInfoRepository UserInfoRepository;

	@MockBean
	private MasterSaldoRepository saldoRepository;

	private List<MasterUserEntity> userList = new ArrayList<>();
	private List<MasterUserInfoEntity> userInfoList = new ArrayList<>();
	private List<MasterSaldoEntity> saldoList = new ArrayList<>();
	private Page<MasterUserEntity> userPage = null;
//	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	private final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		for (int i = 0; i < 5; i++) {
			MasterUserInfoEntity user = new MasterUserInfoEntity();
			user.setId(Long.valueOf(String.valueOf(i)));
			user.setFullname("test "+ i);
			user.setPhoneNumber("29123" + i);
			user.setBirthdate(new Date(2019, 12, i));
			user.setVehicles(null);
			userInfoList.add(user);
		}
		
		for (int i = 0; i < 5; i++) {
			MasterSaldoEntity saldo = new MasterSaldoEntity();
			saldo.setId(Long.valueOf(String.valueOf(i)));
			saldo.setSaldo(new BigDecimal(0));
			saldoList.add(saldo);
		}
		
		for (int i = 0; i < 5; i++) {
			MasterUserEntity user = new MasterUserEntity();
			user.setId(Long.valueOf(String.valueOf(i)));
			user.setEmail("email" + i+"@gmail.com");
			user.setPassword("password" + i);
			user.setSaldo(saldoList.get(i));
			user.setUserInfo(userInfoList.get(i));
			userList.add(user);
		}
		
		userPage = new PageImpl<MasterUserEntity>(userList);
	}

	@Test
	public void findById() throws Exception {
		MasterUserEntity expected = userList.get(0);
		expected.setId(4l);
		Mockito.when(userService.findById(4l)).thenReturn(expected);
		Assert.assertEquals(expected, userService.findById(4l));
	}
	
	@Test
	public void saveUser() {
		UserDto userDto = new UserDto();
		userDto.setId(userList.get(0).getId());
		userDto.setEmail(userList.get(0).getEmail());
		userDto.setPassword(userList.get(0).getPassword());
		userDto.setFullname(userInfoList.get(0).getFullname());
		userDto.setPhoneNumber(userInfoList.get(0).getPhoneNumber());
		userDto.setBirthdate(userInfoList.get(0).getBirthdate());
		
		MasterUserEntity expected = userList.get(0);
		
		Mockito.when(userService.saveUser(userDto)).thenReturn(expected);
	}
	
	@Test
	public void findAllList() {
		Mockito.when(userService.findAll()).thenReturn(userList);
		Assert.assertEquals(userList, userService.findAll());
	}
	
	@Test
	public void findAllPage() {
		Mockito.when(userService.findAll(0, 3)).thenReturn(userPage);
		Assert.assertEquals(userPage, userService.findAll(0, 3));
	}
	
	@Test
	public void updateUserPassword() {
		UserDto userDto = new UserDto();
		userDto.setId(userList.get(0).getId());
		userDto.setPassword(userList.get(0).getPassword());
		
		Mockito.when(userService.updateUserPassword(userDto)).thenReturn(userList.get(0));
		Assert.assertEquals(userList.get(0), userService.updateUserPassword(userDto));
	}
	
//	@Test
//	public void findByEmailPasswordNegatif(){
//		MasterUserEntity user = userList.get(0);
//		Mockito.when(userService.findByEmailPassword(user)).thenReturn(null);
//	}
}
