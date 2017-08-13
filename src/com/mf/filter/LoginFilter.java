package com.mf.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class LoginFilter implements Filter {
	private String sessionKey;
	private String redirectUrl;
	private Pattern excepUrlPattern;

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		// TODO Auto-generated method stub
		sessionKey = cfg.getInitParameter("sessionKey");
		redirectUrl = cfg.getInitParameter("redirectUrl");
		String excepUrlRegex = cfg.getInitParameter("excepUrlRegex");
		if (!StringUtils.isBlank(excepUrlRegex)) {
			excepUrlPattern = Pattern.compile(excepUrlRegex);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		response.setCharacterEncoding("UTF-8"); 
		String servletPath = request.getServletPath();
		if (servletPath.equals("/login") ||servletPath.equals(redirectUrl) || excepUrlPattern.matcher(servletPath).lookingAt()) {
			//System.out.println(servletPath);
			chain.doFilter(req, resp);
			return;
		}
		
		Object sessionObj = request.getSession().getAttribute(sessionKey);

		if (sessionObj == null) {
			//String contextPath = request.getContextPath();
			//String redirect = servletPath + "?" + StringUtils.defaultString(request.getQueryString());
			//response.sendRedirect("/login.html");
			  PrintWriter out = response.getWriter();  
		        out.println("<html>");      
		        out.println("<script>");      
		        out.println("window.open ('/login.html','_top')");      
		        out.println("</script>");      
		        out.println("</html>");
		} else {
			chain.doFilter(req, resp);
		}
	}

}
