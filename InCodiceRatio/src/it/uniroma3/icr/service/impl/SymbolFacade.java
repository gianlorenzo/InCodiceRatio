package it.uniroma3.icr.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.icr.dao.impl.SymbolDaoImpl;
import it.uniroma3.icr.insertImageInDb.InsertNegativeSampleInDb;
import it.uniroma3.icr.insertImageInDb.InsertSampleInDb;
import it.uniroma3.icr.insertImageInDb.InsertSymbolInDb;
import it.uniroma3.icr.model.Sample;
import it.uniroma3.icr.model.Symbol;

@Service
public class SymbolFacade {
	
	@Autowired
	private SymbolDaoImpl symbolDaoImpl;
	
	@Autowired
	private InsertSymbolInDb insertSymbol;
	
	@Autowired
	private InsertSampleInDb insertSample;
	
	@Autowired
	private InsertNegativeSampleInDb insertNegativeSample;
	
	public Symbol retrieveSymbol(long id) {
		return this.symbolDaoImpl.findSymbol(id);
		
	}
	
	public void insertSymbolInDb() throws FileNotFoundException, IOException {
		insertSymbol.insertSymbolInDb();
	}
	
	public List<Symbol> retrieveAllSymbols() {
		return this.symbolDaoImpl.findAll();
	}
	
	public void insertSymbol(Symbol symbol) {
		symbolDaoImpl.insertSymbol(symbol);
	}
	
	public void getSampleImage() throws FileNotFoundException, IOException {
		insertSample.getSampleImage();
	}
	
	public void getNegativeSampleImage() throws FileNotFoundException, IOException {
		insertNegativeSample.getNegativeSampleImage();
	}

	
	public List<Sample> findAllSamplesBySymbolId(long id) {
		return this.insertSample.findAllSamplesBySymbolId(id);
	}
	
	public List<Sample> findAllNegativeSamplesBySymbolId(long id) {
		return this.insertNegativeSample.findAllNegativeSamplesBySymbolId(id);
	}
	
}
