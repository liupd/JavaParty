package com.zp.tent.app.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

// 实现 Filter 类
public class AuthenFilter implements Filter {
    private static Logger log = Logger.getLogger(AuthenFilter.class);

    public void init(FilterConfig config)
            throws ServletException {
        // 获取初始化参数
        String testParam = config.getInitParameter("test-param");

        // 输出初始化参数
        log.debug("[AuthenFilter]Test Param: " + testParam);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String result = String.format("[AuthenFilter]IP:%s:%s, ", request.getRemoteAddr(), httpServletRequest.getRemotePort());

        // 记录 IP 地址和当前时间戳
        log.debug(result);

        // 把请求传回过滤链
        chain.doFilter(request, response);
    }

    public void destroy() {
        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
    }
}