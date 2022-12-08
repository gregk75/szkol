<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="petcatalog.model.Item" %>

<%
    ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

    Item pet = (Item)row.getObject();
%>
<img src="<%= request.getContextPath() %>/images/<%=pet.getImageThumbUrl() %>"/>
