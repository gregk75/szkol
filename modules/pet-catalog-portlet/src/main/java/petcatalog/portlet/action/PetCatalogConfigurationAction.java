package petcatalog.portlet.action;

import com.liferay.portal.kernel.portlet.BaseJSPSettingsConfigurationAction;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import petcatalog.constants.PetCatalogPortletKeys;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Component(
        immediate = true,
        property = "javax.portlet.name=" + PetCatalogPortletKeys.PETCATALOG,
        service = ConfigurationAction.class)
public class PetCatalogConfigurationAction extends BaseJSPSettingsConfigurationAction {
    @Override
    public String getJspPath(HttpServletRequest httpServletRequest) {
        return "/configuration.jsp";
    }

    @Override
    @Reference(target = "(osgi.web.symbolicname=petcatalog)", unbind = "-")
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }
}