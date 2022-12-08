<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="petcatalog.model.Item"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<portlet:defineObjects/>
<liferay-theme:defineObjects/>

<%
    ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    Item pet = (Item)row.getObject();
%>

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/view.jsp"/>
</portlet:renderURL>

<liferay-ui:icon-menu >
    <c:if test="<%= true %>">
        <portlet:renderURL var="editURL">
            <portlet:param name="jspPage" value="/edit.jsp"/>
            <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EDIT %>"/>
            <portlet:param name="petId" value="<%= String.valueOf(pet.getItemId()) %>"/>
            <portlet:param name="redirect" value="<%= viewURL.toString() %>"/>

        </portlet:renderURL>

        <liferay-ui:icon image="edit" url="<%= editURL.toString() %>"/>
    </c:if>
</liferay-ui:icon-menu>
