package com.osf.test.util;

import javax.servlet.http.HttpServletRequest;

public class URICommand {

	public static String getCommend(HttpServletRequest req) {
		// localhost/food/
		String rPath = req.getContextPath() + "/";
		
		String uri = req.getRequestURI();
		uri = uri.substring(rPath.length());
		return uri.substring(0,uri.indexOf("/"));
	}
	
	

}
