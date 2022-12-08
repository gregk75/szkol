package petcatalog.upgrade;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class PetCatalogUpgrade implements UpgradeStepRegistrator {

    @Override
    public void register(Registry registry) {
/*
        registry.register(
                "petcatalog.service",
                "0.0.0", "2.0.0",
                new ImportData());
*/
    }

}
