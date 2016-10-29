package it.uniroma3.icr.dao;

import it.uniroma3.icr.model.Administrator;

public interface AdminDao {

	public void insertAdmin(Administrator administrator);
	public Administrator findAdmin(String username);

}
