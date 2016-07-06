package org.zp.tent.app.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.zp.tent.app.filter.wrapper.CacheResponseWrapper;

public class CacheFilter implements Filter {

	private ServletContext servletContext;

	// 缓存文件夹，使用Tomcat工作目录
	private File temporalDir;

	// 缓存时间，配置在Filter初始化参数中
	private long cacheTime = Long.MAX_VALUE;
	
	private static Logger log = Logger.getLogger(CacheFilter.class);

	public void init(FilterConfig config) throws ServletException {
		temporalDir = (File) config.getServletContext().getAttribute(
				"javax.servlet.context.tempdir");
		servletContext = config.getServletContext();
		cacheTime = new Long(config.getInitParameter("cacheTime"));
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 如果为 POST, 则不经过缓存
		if ("POST".equals(request.getMethod())) {
			chain.doFilter(request, response);
			return;
		}

		// 请求的 URI
		String uri = request.getRequestURI();
		if (uri == null)
			uri = "";
		uri = uri.replace(request.getContextPath() + "/", "");
		uri = uri.trim().length() == 0 ? "index.jsp" : uri;
		uri = request.getQueryString() == null ? uri : (uri + "?" + request
				.getQueryString());

		// 对应的缓存文件
		File cacheFile = new File(temporalDir, URLEncoder.encode(uri, "UTF-8"));
		log.debug("[CacheFilter]缓存文件:" + cacheFile.getPath());

		// 如果缓存文件不存在 或者已经超出缓存时间 则请求 Servlet
		if (!cacheFile.exists()
				|| cacheFile.length() == 0
				|| cacheFile.lastModified() < System.currentTimeMillis()
						- cacheTime) {

			CacheResponseWrapper cacheResponse = new CacheResponseWrapper(
					response);

			chain.doFilter(request, cacheResponse);

			// 将内容写入缓存文件
			char[] content = cacheResponse.getCacheWriter().toCharArray();

			temporalDir.mkdirs();
			cacheFile.createNewFile();

			Writer writer = new OutputStreamWriter(new FileOutputStream(
					cacheFile), "UTF-8");
			writer.write(content);
			writer.close();
		}

		// 请求的ContentType
		String mimeType = servletContext.getMimeType(request.getRequestURI());
		response.setContentType(mimeType);

		// 读取缓存文件的内容，写入客户端浏览器
		Reader ins = new InputStreamReader(new FileInputStream(cacheFile),
				"UTF-8");
		StringBuffer buffer = new StringBuffer();
		char[] cbuf = new char[1024];
		int len;
		while ((len = ins.read(cbuf)) > -1) {
			buffer.append(cbuf, 0, len);
		}
		ins.close();
        
		// 输出到客户端
		response.getWriter().write(buffer.toString());
	}

	public void destroy() {
	}
}
