/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package petcatalog.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionHelper;
import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;

import org.osgi.service.component.annotations.Reference;
import petcatalog.model.Item;
import petcatalog.service.base.ItemServiceBaseImpl;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;


/**
 * @author Brian Wing Shun Chan
 */
@Component(
        property = {
                "json.web.service.context.name=foo",
                "json.web.service.context.path=Item"
        },
        service = AopService.class
)
public class ItemServiceImpl extends ItemServiceBaseImpl {

    @Reference(
            target = "(model.class.name=petcatalog.model.Item)"
    )
    private ModelResourcePermission<Item>
            itemModelResourcePermission;

    public Item updateItem(
            long petId, String name, String description, ServiceContext serviceContext)
            throws SystemException, PortalException {

        ModelResourcePermissionHelper.check(
                itemModelResourcePermission, getPermissionChecker(),
                serviceContext.getCompanyId(), serviceContext.getCompanyId(), ActionKeys.UPDATE);

        return itemLocalService.updateItem(petId, name, description, serviceContext);
    }

}