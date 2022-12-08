<%@ page import="com.liferay.portal.kernel.exception.NestableException" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="petcatalog.model.Item" %>
<%@ page import="petcatalog.service.ItemLocalServiceUtil" %>
<%@ page import="javax.portlet.PortletMode" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>
<%@ page import="com.liferay.portal.kernel.portlet.PortletURLUtil" %>
<%@ page import="com.liferay.portal.kernel.util.UnicodeFormatter" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<portlet:defineObjects/>
<liferay-theme:defineObjects/>

<%
    WindowState windowState = null;
    PortletMode portletMode = null;

    PortletURL currentURLObj = null;

    if (renderRequest != null) {
        windowState = renderRequest.getWindowState();
        portletMode = renderRequest.getPortletMode();

        currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);
    }
    else if (resourceRequest != null) {
        windowState = resourceRequest.getWindowState();
        portletMode = resourceRequest.getPortletMode();

        currentURLObj = PortletURLUtil.getCurrent(resourceRequest, resourceResponse);
    }

    String currentURL = currentURLObj.toString();

    String redirect = ParamUtil.getString(request, "redirect");
    String backURL = ParamUtil.getString(request, "backURL", redirect);
    PortletURL portletURL = renderResponse.createRenderURL();


    Item item = null;
    String cmd = (String)ParamUtil.getString(request, Constants.CMD);
    long petId = ParamUtil.getLong(request, "petId");
    if (petId != 0 && Constants.EDIT.equals(cmd)) {
        try {
            item = ItemLocalServiceUtil.getItem(petId);
        }
        catch (NestableException ne) {
            ne.printStackTrace();
        }
    }
%>

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/view.jsp" />
</portlet:renderURL>

<portlet:renderURL var="returnURL">
    <portlet:param name="jspPage" value="/edit.jsp" />
    <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EDIT %>" />
    <portlet:param name="petId" value="<%= String.valueOf(petId) %>" />
    <portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:renderURL>

<portlet:actionURL name="editItem" var="editURL">
    <portlet:param name="jspPage" value="/edit.jsp" />
    <portlet:param name="redirect" value="<%= viewURL.toString() %>"/>
</portlet:actionURL>

<aui:form action="<%=editURL%>" method="POST" name="fm" >
    <aui:fieldset>
        <aui:input name="redirect" type="hidden" value="<%=redirect%>" />
        <aui:input name="backURL" type="hidden" value="<%=backURL%>" />
        <aui:input name="petId" type="hidden" value="<%=petId%>" />
        <aui:input name="<%=Constants.CMD%>" type="hidden"
                   value="<%=(item == null) ? Constants.ADD : Constants.UPDATE%>" />

        <liferay-ui:header
                title='<%=(item == null) ? LanguageUtil.get(request, "new") : LanguageUtil.get(request, "edit") + ": " + item.getName()%>'
                backURL="<%=redirect%>" />

        <aui:model-context bean="<%=item%>" model="<%=Item.class%>" />

        <aui:input label="name" name="name" />
        <aui:input label="description" name="description" />

    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" value='save' />
        <aui:button type="button" value='cancel' href="<%= redirect %>"/>
    </aui:button-row>
</aui:form>
