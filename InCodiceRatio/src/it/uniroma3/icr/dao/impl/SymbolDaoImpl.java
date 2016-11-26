package it.uniroma3.icr.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.uniroma3.icr.dao.SymbolDao;
import it.uniroma3.icr.model.Symbol;

@Repository
public class SymbolDaoImpl implements SymbolDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Symbol findSymbol(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Symbol s = (Symbol) session.get(Symbol.class, id);
		session.close();
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Symbol> findAll() {
		Session session = sessionFactory.openSession();
		String hql = "FROM Symbol";
		Query query = session.createQuery(hql);
		List<Symbol> empList = query.list();
		session.close();
		return empList;
		
	}

	@Override
	public void insertSymbol(Symbol symbol) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(symbol);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Symbol> findSymbolByManuscript(String manuscript) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String s = "FROM Symbol s where s.manuscript = :manuscript";
		Query query = session.createQuery(s);
		query.setParameter("manuscript", manuscript);
		List<Symbol> symbols = query.list();
		return symbols;
	}

}
