<%@ page import="javax.portlet.WindowState" %>
<%@ page import="petcatalog.service.ItemLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ include file="/init.jsp" %>

<p>
    <b><liferay-ui:message key="petcatalog.caption"/></b>
</p>

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/view.jsp"/>
</portlet:renderURL>


<%
    String name = ParamUtil.getString(renderRequest, "name");
    String desc = ParamUtil.getString(renderRequest, "desc");
%>

<aui:form action="<%= viewURL %>" method="post" name="fm">
    <div class="search">
        <aui:fieldset>
            <aui:input name="name" value="<%= name %>"/>
        </aui:fieldset>
        <aui:button-row>
            <aui:button type="submit" value="search"/>
            <aui:button type="button" value="clear" href="<%= PortalUtil.getCurrentURL(request) %>" cssClass="clear-button"/>
        </aui:button-row>
    </div>

    <liferay-ui:search-container
            curParam="cur2"
            emptyResultsMessage="Nic nie ma"
            total="<%=  ItemLocalServiceUtil.countByName(name) %>"
    >
        <liferay-ui:search-container-results
                results="<%= ItemLocalServiceUtil.findByName(name, searchContainer.getStart(),
                searchContainer.getEnd()) %>"
        />
        <liferay-ui:search-container-row className="petcatalog.model.Item" escapedModel="<%= true %>"
                                         modelVar="pet" indexVar="index">

            <portlet:renderURL var="petURL1" windowState="<%= WindowState.MAXIMIZED.toString() %>">
                <portlet:param name="jspPage" value="/view_pet.jsp"/>
                <portlet:param name="pet_id" value="<%= String.valueOf(pet.getItemId()) %>"/>
                <portlet:param name="redirect" value="<%= viewURL.toString() %>"/>
            </portlet:renderURL>


            <liferay-ui:search-container-column-text name="name" property="name" href="<%= petURL1 %>"/>
            <liferay-ui:search-container-column-jsp name="photo" path="/pet_image.jsp"/>
            <liferay-ui:search-container-column-text name="price" property="price"/>
            <liferay-ui:search-container-column-jsp path="/pet_actions.jsp" align="right"/>

        </liferay-ui:search-container-row>

        <liferay-ui:search-iterator/>

    </liferay-ui:search-container>
</aui:form>