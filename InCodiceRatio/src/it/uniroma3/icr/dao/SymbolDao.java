package it.uniroma3.icr.dao;

import java.util.List;

import it.uniroma3.icr.model.Symbol;

public interface SymbolDao {
	public void insertSymbol(Symbol symbol);
	public Symbol findSymbol(long id);
	public List<Symbol> findAll();
	public List<Symbol> findSymbolByManuscript(String manuscript);

}
