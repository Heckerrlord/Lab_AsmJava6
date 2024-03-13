package com.poly.service;

import java.util.List;

import com.poly.entity.Account;

public interface AccountService {
	public List<Account> findAll();

	public Account findById(String username);

	public List<Account> getAdministrators();

	Account create(Account account);

	Account update(Account account);

	void delete(String username);
}
