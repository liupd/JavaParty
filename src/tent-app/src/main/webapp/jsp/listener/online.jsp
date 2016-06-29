<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:directive.page import="com.zp.tent.common.util.StatisticsUtil" />
<jsp:directive.page import="java.util.Date" />
<jsp:directive.page import="java.text.DateFormat" />
<jsp:directive.page import="com.zp.tent.app.dto.PersonInfoDTO" />
<style>
body, td {
	font-size: 12px;
}
</style>

服务器启动时间：<%=DateFormat.getDateTimeInstance().format(StatisticsUtil.START_DATE)%>，
累计共接待过
<%=StatisticsUtil.TOTAL_HISTORY_COUNT%>
访客。
<br />
同时在线人数最多为
<%=StatisticsUtil.MAX_ONLINE_COUNT%>
人， 发生在
<%=DateFormat.getDateTimeInstance().format(StatisticsUtil.MAX_ONLINE_COUNT_DATE)%>。
<br />

目前在线总数：<%=StatisticsUtil.SESSION_MAP.size()%>，登录用户：<%=StatisticsUtil.CURRENT_LOGIN_COUNT%>。
<br />
<table border=1>
	<tr>
		<th>jsessionid</th>
		<th>account</th>
		<th>creationTime</th>
		<th>lastAccessedTime</th>
		<th>new</th>
		<th>activeTimes</th>
		<th>ip</th>
	</tr>
	<%
	    for (String id : StatisticsUtil.SESSION_MAP.keySet()) {
	        HttpSession sess = StatisticsUtil.SESSION_MAP.get(id);
	        PersonInfoDTO personInfo = (PersonInfoDTO) sess.getAttribute("personInfo");
	%>
	<tr <%=session == sess ? "bgcolor=#DDDDDD" : ""%>>
		<td><%=id%></td>
		<td><%=personInfo == null ? "&nbsp;" : personInfo.getAccount()%></td>
		<td><%=DateFormat.getDateTimeInstance().format(sess.getCreationTime())%></td>
		<td><%=DateFormat.getDateTimeInstance().format(
                        new Date(sess.getLastAccessedTime()))%></td>
		<td><%=sess.isNew()%></td>
		<td><%=sess.getAttribute("activeTimes")%></td>
		<td><%=sess.getAttribute("ip")%></td>
	</tr>
	<%
	    }
	%>
</table>


