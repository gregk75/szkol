<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.UnicodeFormatter"%>
<%@ page import="petcatalog.model.Item" %>
<%@ page import="petcatalog.service.ItemLocalServiceUtil" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>


<portlet:defineObjects />

<%
    // pobierz element
    Item pet = (Item)request.getAttribute("pet");
    if (pet == null) {
        long petId = ParamUtil.getLong(request, "pet_id");
        pet = ItemLocalServiceUtil.getItem(petId);
    }
    String redirect = request.getParameter("redirect");
%>

<table class="lfr-table">
    <tr>
        <td class="lfr-label"><liferay-ui:message key="name" />:</td>
        <td><%= pet.getName() %></td>
    </tr>
    <tr>
        <td class="lfr-label"><liferay-ui:message key="description" />:</td>
        <td><%= pet.getDescription() %></td>
    </tr>
    <tr>
        <td class="lfr-label"><liferay-ui:message key="photo" />:</td>
        <td><img src="<%= request.getContextPath() %>/images/<%=pet.getImageUrl() %>"/></td>
    </tr>
    <tr>
        <td class="lfr-label"><liferay-ui:message key="price" />:</td>
        <td><%= pet.getPrice() %></td>
    </tr>
</table>
<input id="cancelButton" type="button" value="<liferay-ui:message key='cancel' />"
           onclick="location.href = '<%= UnicodeFormatter.toString(redirect) %>'"/>
