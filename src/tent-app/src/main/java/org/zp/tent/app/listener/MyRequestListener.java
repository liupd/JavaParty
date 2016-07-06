package org.zp.tent.app.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyRequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent event) {
    }

    public void requestInitialized(ServletRequestEvent event) {

        HttpServletRequest request = (HttpServletRequest) event
                .getServletRequest();

        HttpSession session = request.getSession(true);

        // 记录IP地址
        session.setAttribute("ip", request.getRemoteAddr());

        // 记录访问次数，只记录访问 .html, .do, .jsp, .action 的累计次数
        String uri = request.getRequestURI();
        String[] suffix = {
                ".html", ".do", ".jsp", ".action"
        };
        for (int i = 0; i < suffix.length; i++) {
            if (uri.endsWith(suffix[i])) {
                break;
            }
            if (i == suffix.length - 1)
                return;
        }

        Integer activeTimes = (Integer) session.getAttribute("activeTimes");

        if (activeTimes == null) {
            activeTimes = 0;
        }

        session.setAttribute("activeTimes", activeTimes + 1);
    }
}
