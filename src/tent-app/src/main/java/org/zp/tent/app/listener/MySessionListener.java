package org.zp.tent.app.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.zp.tent.common.util.StatisticsUtil;

public class MySessionListener implements HttpSessionListener,
        HttpSessionAttributeListener {

    public void sessionCreated(HttpSessionEvent sessionEvent) {

        HttpSession session = sessionEvent.getSession();

        // 将 session 放入 map
        StatisticsUtil.SESSION_MAP.put(session.getId(), session);
        // 总访问人数++
        StatisticsUtil.TOTAL_HISTORY_COUNT++;

        // 如果当前在线人数超过历史记录，则更新最大在线人数，并记录时间
        if (StatisticsUtil.SESSION_MAP.size() > StatisticsUtil.MAX_ONLINE_COUNT) {
            StatisticsUtil.MAX_ONLINE_COUNT = StatisticsUtil.SESSION_MAP
                    .size();
            StatisticsUtil.MAX_ONLINE_COUNT_DATE = new Date();
        }
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        // 将session从map中移除
        StatisticsUtil.SESSION_MAP.remove(session.getId());
    }

    public void attributeAdded(HttpSessionBindingEvent event) {

        if (event.getName().equals("personInfo")) {

            // 当前登录用户数++
            StatisticsUtil.CURRENT_LOGIN_COUNT++;
            HttpSession session = event.getSession();

            // 查找该帐号有没有在其他机器上登录
            for (HttpSession sess : StatisticsUtil.SESSION_MAP.values()) {

                // 如果该帐号已经在其他机器上登录，则以前的登录失效
                if (event.getValue().equals(sess.getAttribute("personInfo"))
                        && session.getId() != sess.getId()) {
                    sess.invalidate();
                }
            }
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {

        // 注销 当前登录用户数--
        if (event.getName().equals("personInfo")) {
            StatisticsUtil.CURRENT_LOGIN_COUNT--;
        }
    }

    public void attributeReplaced(HttpSessionBindingEvent event) {

        // 重新登录
        if (event.getName().equals("personInfo")) {
            HttpSession session = event.getSession();
            for (HttpSession sess : StatisticsUtil.SESSION_MAP.values()) {
                // 如果新帐号在其他机器上登录过，则以前登录失效
                if (event.getValue().equals(sess.getAttribute("personInfo"))
                        && session.getId() != sess.getId()) {
                    sess.invalidate();
                }
            }
        }
    }

}
