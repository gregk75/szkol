package petcatalog.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import petcatalog.constants.PetCatalogPortletKeys;
import petcatalog.model.Item;
import petcatalog.service.ItemLocalServiceUtil;
import petcatalog.service.ItemServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PetCatalogPortletKeys.PETCATALOG,
                "mvc.command.name=editItem"
        },
        service = MVCActionCommand.class)
public class EditItemActionCommand extends BaseMVCActionCommand {
    private static Log log = LogFactoryUtil.getLog(EditItemActionCommand.class);

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        String cmd = ParamUtil.getString(actionRequest, "cmd");

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute("THEME_DISPLAY");
        try {
            if (Constants.ADD.equals(cmd) || Constants.UPDATE.equals(cmd)) {
                update(actionRequest, themeDisplay);
            } else if (Constants.DELETE.equals(cmd)) {
                delete(actionRequest, themeDisplay);
            }

        } catch (Exception e) {
            log.error(e);
            SessionErrors.add(actionRequest, "error");
        }

    }

    private void delete(ActionRequest actionRequest, ThemeDisplay themeDisplay) {
    }

    private void update(ActionRequest actionRequest, ThemeDisplay themeDisplay) throws SystemException, PortalException {
        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
        String name = ParamUtil.getString(actionRequest, "name");
        String desc = ParamUtil.getString(actionRequest, "description");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(Item.class.getName(), actionRequest);

        Item item = null;
        if (Constants.ADD.equals(cmd)) {
            item = ItemLocalServiceUtil.addItem(name, desc, serviceContext);
        } else {
            long petId = ParamUtil.getLong(actionRequest, "petId");
            item = ItemServiceUtil.updateItem(petId, name, desc, serviceContext);
        }

    }
}


