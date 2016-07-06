package org.zp.tent.app.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class SiteHitsFilter implements Filter {
	private int hitCount;

	private static Logger log = Logger.getLogger(SiteHitsFilter.class);

	public void init(FilterConfig config) throws ServletException {
		// 重置点击计数器
		hitCount = 0;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		// 把计数器的值增加 1
		hitCount++;

		// 输出计数器
		log.debug("[SiteHitsFilter]网站访问统计：" + hitCount);

		// 把请求传回到过滤器链
		chain.doFilter(request, response);
	}

	public void destroy() {
		// 这一步是可选的，但是如果需要，您可以把 hitCount 的值写入到数据库
	}
}