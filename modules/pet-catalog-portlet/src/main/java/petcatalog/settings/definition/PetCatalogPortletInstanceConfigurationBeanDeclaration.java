package petcatalog.settings.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import org.osgi.service.component.annotations.Component;
import petcatalog.configuration.PetCatalogPortletInstanceConfiguration;


@Component(service = ConfigurationBeanDeclaration.class)
public class PetCatalogPortletInstanceConfigurationBeanDeclaration
        implements ConfigurationBeanDeclaration {
    @Override
    public Class<?> getConfigurationBeanClass() {
        return PetCatalogPortletInstanceConfiguration.class;
    }
}