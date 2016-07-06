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

/**
 * 字符编码Filter
 */
public class CharacterEncodingFilter implements Filter {
    private String characterEncoding;

    private boolean enabled;

    private static Logger log = Logger.getLogger(CharacterEncodingFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        characterEncoding = config.getInitParameter("characterEncoding");
        enabled = "true".equalsIgnoreCase(characterEncoding.trim()) || "1".equalsIgnoreCase(characterEncoding.trim());
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        log.info("[CharacterEncodingFilter]默认编码方式: " + characterEncoding);
        if (enabled || characterEncoding != null) {
            request.setCharacterEncoding(characterEncoding);
            response.setCharacterEncoding(characterEncoding);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        characterEncoding = null;
    }
}
