package it.uniroma3.icr.insertImageInDb;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.ServletContextAware;

@Repository
public class GetNegativeSamplePath implements ServletContextAware{
	
    private ServletContext servletContext;
    
    public String getNegativeSamplePath() {
    	String path = servletContext.getInitParameter("pathNegativeSample");
    	return path;
    }
    
    public String getNegativePath() {
    	String path = this.getNegativeSamplePath();
    	return path;
    }


	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

}
