package org.zp.tent.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.zp.tent.app.filter.wrapper.GZipResponseWrapper;

public class GZipFilter implements Filter {
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String acceptEncoding = request.getHeader("Accept-Encoding");

		if (StringUtils.isNotBlank(acceptEncoding) && StringUtils.equalsIgnoreCase(acceptEncoding, "gzip")) {
			log.debug("[GZipFilter]Accept-Encoding: " + acceptEncoding);

			// 如果客户浏览器支持 GZIP 格式, 则使用 GZIP 压缩数据
			GZipResponseWrapper gzipResponse = new GZipResponseWrapper(response);
			chain.doFilter(request, gzipResponse);

			// 输出压缩数据
			gzipResponse.finishResponse();
			log.debug("[GZipFilter]压缩结束");
		} else {
			// 否则, 不压缩
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
