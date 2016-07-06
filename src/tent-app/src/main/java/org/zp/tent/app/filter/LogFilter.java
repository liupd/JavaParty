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

import org.apache.log4j.Logger;

public class LogFilter implements Filter {
    private static Logger log = Logger.getLogger(LogFilter.class);

    private String filterName;

    public void init(FilterConfig config) throws ServletException {
        // 获取 Filter 的 name，配置在 web.xml 中
        filterName = config.getFilterName();
        log.info("启动 Filter: " + filterName);
    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        long startTime = System.currentTimeMillis();
        String requestURI = request.getRequestURI();

        requestURI = request.getQueryString() == null ? requestURI
                : (requestURI + "?" + request.getQueryString());

        chain.doFilter(request, response);

        long endTime = System.currentTimeMillis();

        log.info("[LogFilter]" + request.getRemoteAddr() + " 访问了 " + requestURI + ", 总用时 "
                + (endTime - startTime) + " 毫秒。");
    }

    public void destroy() {
        log.info("关闭 Filter: " + filterName);
    }

}
