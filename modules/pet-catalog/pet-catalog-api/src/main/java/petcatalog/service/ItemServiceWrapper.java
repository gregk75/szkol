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

package petcatalog.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemService}.
 *
 * @author Brian Wing Shun Chan
 * @see ItemService
 * @generated
 */
public class ItemServiceWrapper
	implements ItemService, ServiceWrapper<ItemService> {

	public ItemServiceWrapper(ItemService itemService) {
		_itemService = itemService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _itemService.getOSGiServiceIdentifier();
	}

	@Override
	public petcatalog.model.Item updateItem(
			long petId, String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _itemService.updateItem(
			petId, name, description, serviceContext);
	}

	@Override
	public ItemService getWrappedService() {
		return _itemService;
	}

	@Override
	public void setWrappedService(ItemService itemService) {
		_itemService = itemService;
	}

	private ItemService _itemService;

}