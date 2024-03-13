package com.poly.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.dao.AuthorityDAO;
import com.poly.dao.RoleDAO;
import com.poly.entity.Authority;

@RestController
public class AuthorityRestController {

	@Autowired
	AuthorityDAO authorityDao;

	@Autowired
	RoleDAO roleDao;

	@Autowired
	AccountDAO accountDao;

	@GetMapping("/rest/authorities")
	public Map<String, Object> getAuthorities() {
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authorityDao.findAll());
		data.put("roles", roleDao.findAll());
		data.put("accounts", accountDao.findAll());
		return data;
	}

	@PostMapping("/rest/authorities")
	public Authority create(@RequestBody Authority authority) {
		return authorityDao.save(authority);
	}

	@DeleteMapping("/rest/authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityDao.deleteById(id);
	}
}
