package org.zp.tent.app.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.zp.tent.app.filter.wrapper.HttpCharacterResponseWrapper;

public class OutputReplaceFilter implements Filter {
    private Properties pp = new Properties();

    private static Logger log = Logger.getLogger(OutputReplaceFilter.class);

    public void init(FilterConfig config) throws ServletException {
        String file = config.getInitParameter("file");
        String realPath = config.getServletContext().getRealPath(file);
        try {
            pp.load(new FileInputStream(realPath));
        } catch (IOException e) {
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        // 自定义的 response
        HttpCharacterResponseWrapper response = new HttpCharacterResponseWrapper(
                (HttpServletResponse) res);

        // 提交给 Servlet 或者下一个 Filter
        chain.doFilter(req, response);

        // 得到缓存在自定义 response 中的输出内容
        String output = response.getCharArrayWriter().toString();

        // 修改，替换
        String content = "[OutputReplaceFilter]替换敏感词\n";
        for (Object obj : pp.keySet()) {
            String key = (String) obj;
            output = output.replace(key, pp.getProperty(key));
            content += String.format("%s -> %s\n", key, pp.getProperty(key));
        }

        log.debug(content);
        // 输出
        PrintWriter out = res.getWriter();
        out.write(output);
    }

    public void destroy() {
    }
}
