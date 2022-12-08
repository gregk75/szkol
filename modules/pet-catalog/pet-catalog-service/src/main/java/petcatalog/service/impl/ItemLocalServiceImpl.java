/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
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
import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;

import petcatalog.model.Item;
import petcatalog.service.base.ItemLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=petcatalog.model.Item",
	service = AopService.class
)
public class ItemLocalServiceImpl extends ItemLocalServiceBaseImpl {
	public Item addItem(String name, String description, ServiceContext serviceContext)
			throws SystemException, PortalException {

		long id = this.counterLocalService.increment();
		Item n = this.itemPersistence.create(id);

//        n.setCompanyId(serviceContext.getCompanyId());
//        long userId = serviceContext.getUserId();
//        User user = this.userLocalService.getUser(userId);
//        n.setUserId(user.getUserId());
//        n.setUserName(user.getFullName());
//        n.setUserUuid(user.getUuid());
//        n.setCreateDate(new Date());
//        n.setStartDate(startDate != null ? startDate : n.getCreateDate());
//
//        n.setGroupId(serviceContext.getScopeGroupId());

		n.setName(name);
		n.setDescription(description);

		n = this.itemPersistence.update(n, serviceContext);

		return n;
	}

	public Item updateItem(
			long petId, String name, String description,ServiceContext serviceContext)
			throws SystemException, PortalException {

		Item n = this.itemPersistence.fetchByPrimaryKey(petId);

		long userId = serviceContext.getUserId();
		User user = this.userLocalService.getUser(userId);
//        n.setModifiedByUserId(user.getUserId());
//        n.setModifiedByUserName(user.getFullName());
//        n.setModifiedByUserUuid(user.getUuid());
//        n.setModifiedDate(new Date());

		n.setName(name);
		n.setDescription(description);

		n = this.itemPersistence.update(n, serviceContext);

		return n;
	}

}