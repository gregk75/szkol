<%@page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys" %>
<%@page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="petcatalog.model.Item" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>

<portlet:defineObjects/>
<liferay-theme:defineObjects/>

<%
    ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    Item pet = (Item) row.getObject();
%>

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/view.jsp"/>
</portlet:renderURL>

<liferay-ui:icon-menu
        direction="left-side"
        icon=""
        markupView="lexicon"
        message=""
        showWhenSingleIcon="<%= false %>"
>
    <c:if test="<%= permissionChecker.hasPermission(themeDisplay.getCompanyId(), Item.class.getName(), pet.getItemId(), ActionKeys.UPDATE) %>">
        <portlet:renderURL var="editURL">
            <portlet:param name="jspPage" value="/edit.jsp"/>
            <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EDIT %>"/>
            <portlet:param name="petId" value="<%= String.valueOf(pet.getItemId()) %>"/>
            <portlet:param name="redirect" value="<%= viewURL.toString() %>"/>

        </portlet:renderURL>

        <liferay-ui:icon image="edit" url="<%= editURL.toString() %>"/>
    </c:if>

    <c:if test="<%= permissionChecker.hasPermission(themeDisplay.getCompanyId(), Item.class.getName(), pet.getItemId(), ActionKeys.PERMISSIONS) %>">
        <liferay-security:permissionsURL
                modelResource="<%= Item.class.getName() %>"
                modelResourceDescription="<%= pet.getName() %>"
                resourcePrimKey="<%= String.valueOf(pet.getItemId()) %>"
                var="permissionsItemURL"
                windowState="<%= LiferayWindowState.POP_UP.toString() %>"
        />

        <liferay-ui:icon
                image="permissions"
                message="permissions"
                method="get"
                url="<%= permissionsItemURL %>"
                useDialog="<%= true %>"
        />
    </c:if>

    <c:if test="<%= permissionChecker.hasPermission(themeDisplay.getCompanyId(), Item.class.getName(), pet.getItemId(), ActionKeys.DELETE) %>">
        <portlet:actionURL name="deleteItem" var="deleteURL">
            <portlet:param name="redirect" value="<%= viewURL.toString() %>"/>
            <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>"/>
            <portlet:param name="itemId" value="<%= String.valueOf(pet.getItemId()) %>" />
        </portlet:actionURL>

        <liferay-ui:icon-delete
                image="delete"
                url="<%= deleteURL %>"
        />
    </c:if>
</liferay-ui:icon-menu>
