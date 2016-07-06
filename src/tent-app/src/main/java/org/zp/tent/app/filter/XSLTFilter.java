package org.zp.tent.app.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

public class XSLTFilter implements Filter {
    private ServletContext servletContext;

    private static Logger log = Logger.getLogger(XSLTFilter.class);

    public void init(FilterConfig config) throws ServletException {
        servletContext = config.getServletContext();
    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 格式样本文件：/book.xsl
        Source styleSource = new StreamSource(servletContext
                .getRealPath("/MessageLog.xsl"));

        // 请求的 xml 文件
        Source xmlSource = new StreamSource(servletContext.getRealPath(request
                .getRequestURI().replace(request.getContextPath() + "", "")));
        try {

            // 转换器工厂
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();

            // 转换器
            Transformer transformer = transformerFactory
                    .newTransformer(styleSource);

            // 将转换的结果保存到该对象中
            CharArrayWriter charArrayWriter = new CharArrayWriter();
            StreamResult result = new StreamResult(charArrayWriter);

            // 转换
            transformer.transform(xmlSource, result);

            // 输出转换后的结果
            response.setContentType("text/html");
            response.setContentLength(charArrayWriter.toString().length());
            PrintWriter out = response.getWriter();
            out.write(charArrayWriter.toString());

            log.debug("[XSLTFilter]" + charArrayWriter.toString());

        } catch (Exception ex) {
        }
    }

    public void destroy() {
    }
}
