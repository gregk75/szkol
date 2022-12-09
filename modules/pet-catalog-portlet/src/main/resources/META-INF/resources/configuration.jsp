<%@ page import="com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil" %>
<%@ page import="com.liferay.portal.kernel.settings.ParameterMapSettingsLocator" %>
<%@ page import="com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="petcatalog.configuration.PetCatalogPortletInstanceConfiguration" %>
<%@page contentType="text/html; charset=UTF-8" %>

<%@ include file="/init.jsp" %>

<%
    PetCatalogPortletInstanceConfiguration petCatalogPortletInstanceConfiguration =
            ConfigurationProviderUtil.getConfiguration(PetCatalogPortletInstanceConfiguration.class,
                    new ParameterMapSettingsLocator(request.getParameterMap(),
                            new PortletInstanceSettingsLocator(themeDisplay.getLayout(),
                                    portletDisplay.getPortletResource())));
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL"/>

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL"/>

<liferay-frontend:edit-form
        action="<%= configurationActionURL %>"
        method="post"
        name="fm"
>
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>"/>
    <aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>"/>

    <liferay-frontend:edit-form-body>

        <liferay-frontend:fieldset-group>
            <liferay-frontend:fieldset
                    collapsible="<%= false %>"
                    label="settings"
            >
                <aui:input name="preferences--imagesUrl--" label="images-url" value="<%= petCatalogPortletInstanceConfiguration.imagesUrl() %>"/>
            </liferay-frontend:fieldset>
        </liferay-frontend:fieldset-group>

    </liferay-frontend:edit-form-body>

    <liferay-frontend:edit-form-footer>
        <aui:button type="submit"/>

        <aui:button type="cancel"/>
    </liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>