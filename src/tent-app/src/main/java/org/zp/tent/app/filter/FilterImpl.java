package org.zp.tent.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterImpl implements Filter {
	private boolean enable;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("call init()");
		enable = "true".equals(filterConfig.getInitParameter("enable"));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(String.format("enable = %s", Boolean.toString(enable)));
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("call destroy()");
	}
}
