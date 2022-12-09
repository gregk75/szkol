package petcatalog.configuration.defintion;

import com.liferay.portal.kernel.settings.definition.ConfigurationPidMapping;
import org.osgi.service.component.annotations.Component;
import petcatalog.configuration.PetCatalogPortletInstanceConfiguration;
import petcatalog.constants.PetCatalogPortletKeys;

@Component(service = ConfigurationPidMapping.class)
public class PetCatalogPortletInstanceConfigurationPidMapping
        implements ConfigurationPidMapping {
    @Override
    public Class<?> getConfigurationBeanClass() {
        return PetCatalogPortletInstanceConfiguration.class;
    }

    @Override
    public String getConfigurationPid() {
        return PetCatalogPortletKeys.PETCATALOG;
    }
}