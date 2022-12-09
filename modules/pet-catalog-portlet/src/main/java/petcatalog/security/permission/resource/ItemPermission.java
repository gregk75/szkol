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

package petcatalog.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import petcatalog.model.Item;

@Component(immediate = true, service = {})
public class ItemPermission {

    public static boolean contains(
            PermissionChecker permissionChecker, Item item,
            String actionId)
            throws PortalException {

        return itemModelResourcePermission.contains(
                permissionChecker, item, actionId);
    }

    public static boolean contains(
            PermissionChecker permissionChecker, long classPK, String actionId)
            throws PortalException {

        return itemModelResourcePermission.contains(
                permissionChecker, classPK, actionId);
    }

    @Reference(
            target = "(model.class.name=petcatalog.model.Item)",
            unbind = "-"
    )
    protected void setModelResourcePermission(
            ModelResourcePermission<Item> modelResourcePermission) {

        itemModelResourcePermission = modelResourcePermission;
    }

    private static ModelResourcePermission<Item>
            itemModelResourcePermission;

}