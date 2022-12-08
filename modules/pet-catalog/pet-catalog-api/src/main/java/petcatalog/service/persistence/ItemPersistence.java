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

package petcatalog.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

import petcatalog.exception.NoSuchItemException;

import petcatalog.model.Item;

/**
 * The persistence interface for the item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItemUtil
 * @generated
 */
@ProviderType
public interface ItemPersistence extends BasePersistence<Item> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemUtil} to access the item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the items where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching items
	 */
	public java.util.List<Item> findByName(String name);

	/**
	 * Returns a range of all the items where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @return the range of matching items
	 */
	public java.util.List<Item> findByName(String name, int start, int end);

	/**
	 * Returns an ordered range of all the items where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching items
	 */
	public java.util.List<Item> findByName(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator);

	/**
	 * Returns an ordered range of all the items where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching items
	 */
	public java.util.List<Item> findByName(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first item in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item
	 * @throws NoSuchItemException if a matching item could not be found
	 */
	public Item findByName_First(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator<Item>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the first item in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item, or <code>null</code> if a matching item could not be found
	 */
	public Item fetchByName_First(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator);

	/**
	 * Returns the last item in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item
	 * @throws NoSuchItemException if a matching item could not be found
	 */
	public Item findByName_Last(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator<Item>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the last item in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item, or <code>null</code> if a matching item could not be found
	 */
	public Item fetchByName_Last(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator);

	/**
	 * Returns the items before and after the current item in the ordered set where name LIKE &#63;.
	 *
	 * @param itemId the primary key of the current item
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	public Item[] findByName_PrevAndNext(
			long itemId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<Item>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Removes all the items where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 */
	public void removeByName(String name);

	/**
	 * Returns the number of items where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching items
	 */
	public int countByName(String name);

	/**
	 * Returns all the items where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @return the matching items
	 */
	public java.util.List<Item> findByNameDesc(String name, String description);

	/**
	 * Returns a range of all the items where name LIKE &#63; and description LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param description the description
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @return the range of matching items
	 */
	public java.util.List<Item> findByNameDesc(
		String name, String description, int start, int end);

	/**
	 * Returns an ordered range of all the items where name LIKE &#63; and description LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param description the description
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching items
	 */
	public java.util.List<Item> findByNameDesc(
		String name, String description, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator);

	/**
	 * Returns an ordered range of all the items where name LIKE &#63; and description LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param description the description
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching items
	 */
	public java.util.List<Item> findByNameDesc(
		String name, String description, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first item in the ordered set where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item
	 * @throws NoSuchItemException if a matching item could not be found
	 */
	public Item findByNameDesc_First(
			String name, String description,
			com.liferay.portal.kernel.util.OrderByComparator<Item>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the first item in the ordered set where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item, or <code>null</code> if a matching item could not be found
	 */
	public Item fetchByNameDesc_First(
		String name, String description,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator);

	/**
	 * Returns the last item in the ordered set where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item
	 * @throws NoSuchItemException if a matching item could not be found
	 */
	public Item findByNameDesc_Last(
			String name, String description,
			com.liferay.portal.kernel.util.OrderByComparator<Item>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the last item in the ordered set where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item, or <code>null</code> if a matching item could not be found
	 */
	public Item fetchByNameDesc_Last(
		String name, String description,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator);

	/**
	 * Returns the items before and after the current item in the ordered set where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param itemId the primary key of the current item
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	public Item[] findByNameDesc_PrevAndNext(
			long itemId, String name, String description,
			com.liferay.portal.kernel.util.OrderByComparator<Item>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Removes all the items where name LIKE &#63; and description LIKE &#63; from the database.
	 *
	 * @param name the name
	 * @param description the description
	 */
	public void removeByNameDesc(String name, String description);

	/**
	 * Returns the number of items where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @return the number of matching items
	 */
	public int countByNameDesc(String name, String description);

	/**
	 * Caches the item in the entity cache if it is enabled.
	 *
	 * @param item the item
	 */
	public void cacheResult(Item item);

	/**
	 * Caches the items in the entity cache if it is enabled.
	 *
	 * @param items the items
	 */
	public void cacheResult(java.util.List<Item> items);

	/**
	 * Creates a new item with the primary key. Does not add the item to the database.
	 *
	 * @param itemId the primary key for the new item
	 * @return the new item
	 */
	public Item create(long itemId);

	/**
	 * Removes the item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemId the primary key of the item
	 * @return the item that was removed
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	public Item remove(long itemId) throws NoSuchItemException;

	public Item updateImpl(Item item);

	/**
	 * Returns the item with the primary key or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param itemId the primary key of the item
	 * @return the item
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	public Item findByPrimaryKey(long itemId) throws NoSuchItemException;

	/**
	 * Returns the item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemId the primary key of the item
	 * @return the item, or <code>null</code> if a item with the primary key could not be found
	 */
	public Item fetchByPrimaryKey(long itemId);

	/**
	 * Returns all the items.
	 *
	 * @return the items
	 */
	public java.util.List<Item> findAll();

	/**
	 * Returns a range of all the items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @return the range of items
	 */
	public java.util.List<Item> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of items
	 */
	public java.util.List<Item> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator);

	/**
	 * Returns an ordered range of all the items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of items
	 */
	public java.util.List<Item> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Item>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the items from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of items.
	 *
	 * @return the number of items
	 */
	public int countAll();

}