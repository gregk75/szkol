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
 * Provides a wrapper for {@link ItemLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ItemLocalService
 * @generated
 */
public class ItemLocalServiceWrapper
	implements ItemLocalService, ServiceWrapper<ItemLocalService> {

	public ItemLocalServiceWrapper(ItemLocalService itemLocalService) {
		_itemLocalService = itemLocalService;
	}

	/**
	 * Adds the item to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param item the item
	 * @return the item that was added
	 */
	@Override
	public petcatalog.model.Item addItem(petcatalog.model.Item item) {
		return _itemLocalService.addItem(item);
	}

	@Override
	public petcatalog.model.Item addItem(
			String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _itemLocalService.addItem(name, description, serviceContext);
	}

	/**
	 * Returns the number of items where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching items
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByName(String name)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemLocalService.countByName(name);
	}

	/**
	 * Creates a new item with the primary key. Does not add the item to the database.
	 *
	 * @param itemId the primary key for the new item
	 * @return the new item
	 */
	@Override
	public petcatalog.model.Item createItem(long itemId) {
		return _itemLocalService.createItem(itemId);
	}

	/**
	 * Deletes the item from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param item the item
	 * @return the item that was removed
	 */
	@Override
	public petcatalog.model.Item deleteItem(petcatalog.model.Item item) {
		return _itemLocalService.deleteItem(item);
	}

	/**
	 * Deletes the item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemId the primary key of the item
	 * @return the item that was removed
	 * @throws PortalException if a item with the primary key could not be found
	 */
	@Override
	public petcatalog.model.Item deleteItem(long itemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemLocalService.deleteItem(itemId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _itemLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>petcatalog.model.impl.ItemModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _itemLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>petcatalog.model.impl.ItemModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _itemLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _itemLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _itemLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public petcatalog.model.Item fetchItem(long itemId) {
		return _itemLocalService.fetchItem(itemId);
	}

	/**
	 * Returns all the items where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching items
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public java.util.List<petcatalog.model.Item> findByName(String name)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemLocalService.findByName(name);
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
	@Override
	public java.util.List<petcatalog.model.Item> findByName(
			String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemLocalService.findByName(name, start, end);
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
	@Override
	public java.util.List<petcatalog.model.Item> findByName(
			String name, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _itemLocalService.findByName(
			name, start, end, orderByComparator);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _itemLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _itemLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the item with the primary key.
	 *
	 * @param itemId the primary key of the item
	 * @return the item
	 * @throws PortalException if a item with the primary key could not be found
	 */
	@Override
	public petcatalog.model.Item getItem(long itemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemLocalService.getItem(itemId);
	}

	/**
	 * Returns a range of all the items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>petcatalog.model.impl.ItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @return the range of items
	 */
	@Override
	public java.util.List<petcatalog.model.Item> getItems(int start, int end) {
		return _itemLocalService.getItems(start, end);
	}

	/**
	 * Returns the number of items.
	 *
	 * @return the number of items
	 */
	@Override
	public int getItemsCount() {
		return _itemLocalService.getItemsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _itemLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itemLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param item the item
	 * @return the item that was updated
	 */
	@Override
	public petcatalog.model.Item updateItem(petcatalog.model.Item item) {
		return _itemLocalService.updateItem(item);
	}

	@Override
	public petcatalog.model.Item updateItem(
			long petId, String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _itemLocalService.updateItem(
			petId, name, description, serviceContext);
	}

	@Override
	public ItemLocalService getWrappedService() {
		return _itemLocalService;
	}

	@Override
	public void setWrappedService(ItemLocalService itemLocalService) {
		_itemLocalService = itemLocalService;
	}

	private ItemLocalService _itemLocalService;

}