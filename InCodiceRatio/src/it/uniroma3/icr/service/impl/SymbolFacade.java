package it.uniroma3.icr.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.icr.dao.impl.SymbolDaoImpl;
import it.uniroma3.icr.insertImageInDb.GetNegativeSamplePath;
import it.uniroma3.icr.insertImageInDb.GetSamplePath;
import it.uniroma3.icr.insertImageInDb.InsertNegativeSampleInDb;
import it.uniroma3.icr.insertImageInDb.InsertSampleInDb;
import it.uniroma3.icr.insertImageInDb.InsertSymbolInDb;
import it.uniroma3.icr.model.Sample;
import it.uniroma3.icr.model.Symbol;

@Service
public class SymbolFacade {
	
	@Autowired
	private GetNegativeSamplePath negativeSamplePath;
	
	@Autowired
	private GetSamplePath getSamplePath;
	
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
	
	public void insertSymbolInDb(String p) throws FileNotFoundException, IOException {
		insertSymbol.insertSymbolInDb(p);
	}
	
	public List<Symbol> retrieveAllSymbols() {
		return this.symbolDaoImpl.findAll();
	}
	
	public void insertSymbol(Symbol symbol) {
		symbolDaoImpl.insertSymbol(symbol);
	}
	
	public void getSampleImage(String p) throws FileNotFoundException, IOException {
		insertSample.getSampleImage(p);
	}
	
	public String getPath() {
		return this.getSamplePath.getPath();
	}
	
    public String getNegativePath() {
    	return this.negativeSamplePath.getNegativePath();
    }
    
    public List<String> getManuscript() throws FileNotFoundException, IOException {
    	return this.getSamplePath.getManuscript();
    }
    
    public List<String> getNegativeManuscript() throws FileNotFoundException, IOException {
    	return this.negativeSamplePath.getNegativeManuscript();
    }
	
	public void getNegativeSampleImage(String p) throws FileNotFoundException, IOException {
		insertNegativeSample.getNegativeSampleImage(p);
	}

	
	public List<Sample> findAllSamplesBySymbolId(long id) {
		return this.insertSample.findAllSamplesBySymbolId(id);
	}
	
	public List<Sample> findAllNegativeSamplesBySymbolId(long id) {
		return this.insertNegativeSample.findAllNegativeSamplesBySymbolId(id);
	}
	
	public List<Symbol> findSymbolByManuscript(String manuscript) {
		return this.symbolDaoImpl.findSymbolByManuscript(manuscript);
	}
	
}
