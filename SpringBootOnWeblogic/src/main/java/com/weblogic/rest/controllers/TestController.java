package com.weblogic.rest.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weblogic.entities.User;

//@Transactional
@RestController
public class TestController {
	 @Autowired
	 @Qualifier("jdbcTemplate1")
	 private JdbcTemplate jdbcTemplate1;
	 
	 @Autowired
	 @Qualifier("jdbcTemplate2")
	 private JdbcTemplate jdbcTemplate2;
	
	@RequestMapping("/users")
	public List<User> findAll(){
		String sql = "SELECT * FROM USERS";
		List<User> list = jdbcTemplate1.query(sql, BeanPropertyRowMapper.newInstance(User.class));
		List<User> list2 = jdbcTemplate2.query(sql, BeanPropertyRowMapper.newInstance(User.class));
		List<User> listAll = Stream.concat(list.stream(), list2.stream())
			       .collect(Collectors.toList());
		return listAll;
	}
	
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public String test() {
		return "Test";
	}
}