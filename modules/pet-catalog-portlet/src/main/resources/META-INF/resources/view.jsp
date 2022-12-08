<%@ page import="javax.portlet.WindowState" %>
<%@ page import="petcatalog.service.ItemLocalServiceUtil" %>
<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="petcatalog.caption"/></b>
</p>

<portlet:renderURL var="viewURL">
	<portlet:param name="jspPage" value="/view.jsp"/>
</portlet:renderURL>

<liferay-ui:search-container
		delta="5"
		curParam="cur2"
		emptyResultsMessage="Nic nie ma"
		total="<%=  ItemLocalServiceUtil.getItemsCount() %>"
>
	<liferay-ui:search-container-results
			results="<%= ItemLocalServiceUtil.getItems(searchContainer.getStart(),
                searchContainer.getEnd()) %>"
				/>
	<liferay-ui:search-container-row className="petcatalog.model.Item" escapedModel="<%= true %>"
									 modelVar="pet" indexVar="index" >

		<portlet:renderURL var="petURL1" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="jspPage" value="/view_pet.jsp"/>
			<portlet:param name="pet_id" value="<%= String.valueOf(pet.getItemId()) %>"/>
			<portlet:param name="redirect" value="<%= viewURL.toString() %>"/>
		</portlet:renderURL>




		<liferay-ui:search-container-column-text name="name" property="name" href="<%= petURL1 %>" />
		<liferay-ui:search-container-column-jsp name="photo" path="/pet_image.jsp" />
		<liferay-ui:search-container-column-text name="price" property="price"/>
		<liferay-ui:search-container-column-jsp path="/pet_actions.jsp" align="right" />

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>
