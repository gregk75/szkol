package petcatalog.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(category = "petcatalog-settings", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
        id = "petcatalog.configuration.PetCatalogPortletInstanceConfiguration",
        localization = "content/Language",
        name = "pet-catalog-portlet-instance-configuration-name")
public interface PetCatalogPortletInstanceConfiguration {
    @Meta.AD(description = "images-url-help", name = "images-url", required = false)
    public String imagesUrl();
}