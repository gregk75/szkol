package petcatalog.route;

import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import org.osgi.service.component.annotations.Component;
import petcatalog.constants.PetCatalogPortletKeys;

@Component(
        property = {
                "com.liferay.portlet.friendly-url-routes=META-INF/routes/petcatalog-routes.xml",
                "javax.portlet.name=" + PetCatalogPortletKeys.PETCATALOG
        },
        service = FriendlyURLMapper.class
)
public class PetCatalogFriendlyURLMapper extends DefaultFriendlyURLMapper {
    private static final String MAPPING = "pet";

    @Override
    public String getMapping() {
        return MAPPING;
    }
}