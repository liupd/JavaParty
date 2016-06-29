package com.zp.tent.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 防盜链Filter
 */
public class ImageRedirectFilter implements Filter {
	private static Logger log = Logger.getLogger(ImageRedirectFilter.class);

	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		log.info(req.getRemoteAddr() + " 访问了 " + request.getRequestURL().toString());

		// 禁止缓存
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);

		// 链接来源地址
		String referer = request.getHeader("referer");

		if (referer == null || !referer.contains(request.getServerName())) {

			/**
			 * 如果 链接地址来自其他网站，则返回错误图片
			 */
			request.getRequestDispatcher("/error.gif").forward(request, response);

		} else {

			/**
			 * 图片正常显示
			 */
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}
}
