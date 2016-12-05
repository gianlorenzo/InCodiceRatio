package it.uniroma3.icr.insertImageInDb;





import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.ServletContextAware;

@Repository
public class GetSamplePath implements ServletContextAware{
	
    private ServletContext servletContext;
    
    public String getSamplePath() {
    	String path = servletContext.getInitParameter("pathSample");
    	return path;
    }
    
    
    public List<String> getManuscript() throws FileNotFoundException, IOException {
		List<String> manuscripts = new ArrayList<>();
		
		String path = this.getSamplePath();
		
		File[] files = new File(path).listFiles();
		for(int i=0;i<files.length;i++) {
			String manuscriptName = files[i].getName();
			manuscripts.add(manuscriptName);
		}
		return manuscripts;
		
	}


	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	
	public String getPath() {
		String path = this.getSamplePath();
		return path;
	}

}
