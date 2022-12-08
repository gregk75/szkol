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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import petcatalog.model.Item;
import petcatalog.service.base.ItemLocalServiceBaseImpl;
import petcatalog.service.persistence.ItemUtil;

import java.util.List;

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

	/**
	 * Returns all the items where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching items
	 * @throws SystemException if a system exception occurred
	 */
	public List<Item> findByName(String name) throws SystemException {
		return ItemUtil.findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}


	/**
	 * Returns a range of all the items where name LIKE &#63;.
	 * <p>
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name  the name
	 * @param start the lower bound of the range of items
	 * @param end   the upper bound of the range of items (not inclusive)
	 * @return the range of matching items
	 * @throws SystemException if a system exception occurred
	 */
	public List<Item> findByName(String name, int start, int end)
			throws SystemException {
		if (Validator.isNull(name)) {
			return getItems(start, end);
		} else {
			name = addPercent(name);

			return ItemUtil.findByName(name, start, end);
		}
	}

	private String addPercent(String name) {
		if (!name.startsWith(StringPool.PERCENT)) {
			name = StringPool.PERCENT + name;
		}
		if (!name.endsWith(StringPool.PERCENT)) {
			name = name + StringPool.PERCENT;
		}
		return name;
	}

	/**
	 * Returns an ordered range of all the items where name LIKE &#63;.
	 * <p>
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name              the name
	 * @param start             the lower bound of the range of items
	 * @param end               the upper bound of the range of items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching items
	 * @throws SystemException if a system exception occurred
	 */
	public List<Item> findByName(String name, int start, int end,
								 OrderByComparator orderByComparator) throws SystemException {
		if (Validator.isNotNull(name)) {
			name = addPercent(name);
		}
		return ItemUtil.findByName(name, start, end, orderByComparator);
	}

	/**
	 * Returns the number of items where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		return ItemUtil.countByName(name);
	}

}