package org.zp.tent.app.filter;

import java.io.IOException;

import javax.security.auth.login.AccountException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.zp.tent.common.exception.BusinessException;

public class ExceptionHandlerFilter implements Filter {
    private static Logger log = Logger.getLogger(ExceptionHandlerFilter.class);

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {

            Throwable rootCause = e;

            while (rootCause.getCause() != null) {
                rootCause = rootCause.getCause();
            }

            String message = rootCause.getMessage();

            message = message == null ? "异常：" + rootCause.getClass().getName() : message;

            request.setAttribute("message", message);
            request.setAttribute("e", e);

            if (rootCause instanceof AccountException) {
                request.getRequestDispatcher("/accountException.jsp").forward(request, response);
            } else if (rootCause instanceof BusinessException) {
                request.getRequestDispatcher("/businessException.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/exception.jsp").forward(request, response);
            }

            log.debug("[ExceptionHandlerFilter]" + message);
        }
    }

    public void destroy() {
    }
}
