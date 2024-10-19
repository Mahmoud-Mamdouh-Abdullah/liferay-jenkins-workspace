<%@ include file="/init.jsp"%>

<p class="last-updated">
	<%
		String lastModifiedDate = request.getAttribute("lastModifiedDate").toString();
		if (lastModifiedDate != null) {
	%>

<p style="padding: 0px; margin: 0px">
	<%=lastModifiedDate%></p>
<%
	} else {
%>
<p>
	<liferay-ui:message key="last.update.info" />
</p>
<%
	}
%>
</p>