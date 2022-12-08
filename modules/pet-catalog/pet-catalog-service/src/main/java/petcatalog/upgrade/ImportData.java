package petcatalog.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

public class ImportData extends UpgradeProcess {

    @Override
    protected void doUpgrade() throws Exception {
        String template = StringUtil.read(ImportData.class.getResourceAsStream("META-INF/sql/insert-data.sql"));

        runSQLTemplateString(template, false, false);
    }

}