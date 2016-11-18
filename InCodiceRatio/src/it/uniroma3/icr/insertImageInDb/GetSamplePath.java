package it.uniroma3.icr.insertImageInDb;


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


	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	
	public String getPath() {
		String path = this.getSamplePath();
		return path;
	}

}
