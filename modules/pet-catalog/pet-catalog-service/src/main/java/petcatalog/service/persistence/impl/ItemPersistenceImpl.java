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

package petcatalog.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import petcatalog.exception.NoSuchItemException;

import petcatalog.model.Item;
import petcatalog.model.impl.ItemImpl;
import petcatalog.model.impl.ItemModelImpl;

import petcatalog.service.persistence.ItemPersistence;
import petcatalog.service.persistence.ItemUtil;
import petcatalog.service.persistence.impl.constants.petcatalogPersistenceConstants;

/**
 * The persistence implementation for the item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ItemPersistence.class)
public class ItemPersistenceImpl
	extends BasePersistenceImpl<Item> implements ItemPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ItemUtil</code> to access the item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ItemImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByName;
	private FinderPath _finderPathWithPaginationCountByName;

	/**
	 * Returns all the items where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching items
	 */
	@Override
	public List<Item> findByName(String name) {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Item> findByName(String name, int start, int end) {
		return findByName(name, start, end, null);
	}

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
	@Override
	public List<Item> findByName(
		String name, int start, int end,
		OrderByComparator<Item> orderByComparator) {

		return findByName(name, start, end, orderByComparator, true);
	}

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
	@Override
	public List<Item> findByName(
		String name, int start, int end,
		OrderByComparator<Item> orderByComparator, boolean useFinderCache) {

		name = Objects.toString(name, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByName;
		finderArgs = new Object[] {name, start, end, orderByComparator};

		List<Item> list = null;

		if (useFinderCache) {
			list = (List<Item>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Item item : list) {
					if (!StringUtil.wildcardMatches(
							item.getName(), name, '_', '%', '\\', false)) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_ITEM_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(StringUtil.toLowerCase(name));
				}

				list = (List<Item>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first item in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item
	 * @throws NoSuchItemException if a matching item could not be found
	 */
	@Override
	public Item findByName_First(
			String name, OrderByComparator<Item> orderByComparator)
		throws NoSuchItemException {

		Item item = fetchByName_First(name, orderByComparator);

		if (item != null) {
			return item;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the first item in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item, or <code>null</code> if a matching item could not be found
	 */
	@Override
	public Item fetchByName_First(
		String name, OrderByComparator<Item> orderByComparator) {

		List<Item> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item
	 * @throws NoSuchItemException if a matching item could not be found
	 */
	@Override
	public Item findByName_Last(
			String name, OrderByComparator<Item> orderByComparator)
		throws NoSuchItemException {

		Item item = fetchByName_Last(name, orderByComparator);

		if (item != null) {
			return item;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the last item in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item, or <code>null</code> if a matching item could not be found
	 */
	@Override
	public Item fetchByName_Last(
		String name, OrderByComparator<Item> orderByComparator) {

		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<Item> list = findByName(name, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the items before and after the current item in the ordered set where name LIKE &#63;.
	 *
	 * @param itemId the primary key of the current item
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	@Override
	public Item[] findByName_PrevAndNext(
			long itemId, String name, OrderByComparator<Item> orderByComparator)
		throws NoSuchItemException {

		name = Objects.toString(name, "");

		Item item = findByPrimaryKey(itemId);

		Session session = null;

		try {
			session = openSession();

			Item[] array = new ItemImpl[3];

			array[0] = getByName_PrevAndNext(
				session, item, name, orderByComparator, true);

			array[1] = item;

			array[2] = getByName_PrevAndNext(
				session, item, name, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Item getByName_PrevAndNext(
		Session session, Item item, String name,
		OrderByComparator<Item> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ITEM_WHERE);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_NAME_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(ItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindName) {
			queryPos.add(StringUtil.toLowerCase(name));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(item)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Item> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the items that the user has permission to view where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching items that the user has permission to view
	 */
	@Override
	public List<Item> filterFindByName(String name) {
		return filterFindByName(
			name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the items that the user has permission to view where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @return the range of matching items that the user has permission to view
	 */
	@Override
	public List<Item> filterFindByName(String name, int start, int end) {
		return filterFindByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the items that the user has permissions to view where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching items that the user has permission to view
	 */
	@Override
	public List<Item> filterFindByName(
		String name, int start, int end,
		OrderByComparator<Item> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByName(name, start, end, orderByComparator);
		}

		name = Objects.toString(name, "");

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ITEM_WHERE);
		}
		else {
			sb.append(_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_NAME_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ItemModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ItemModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), Item.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, ItemImpl.class);
			}
			else {
				sqlQuery.addEntity(_FILTER_ENTITY_TABLE, ItemImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindName) {
				queryPos.add(StringUtil.toLowerCase(name));
			}

			return (List<Item>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the items before and after the current item in the ordered set of items that the user has permission to view where name LIKE &#63;.
	 *
	 * @param itemId the primary key of the current item
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	@Override
	public Item[] filterFindByName_PrevAndNext(
			long itemId, String name, OrderByComparator<Item> orderByComparator)
		throws NoSuchItemException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByName_PrevAndNext(itemId, name, orderByComparator);
		}

		name = Objects.toString(name, "");

		Item item = findByPrimaryKey(itemId);

		Session session = null;

		try {
			session = openSession();

			Item[] array = new ItemImpl[3];

			array[0] = filterGetByName_PrevAndNext(
				session, item, name, orderByComparator, true);

			array[1] = item;

			array[2] = filterGetByName_PrevAndNext(
				session, item, name, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Item filterGetByName_PrevAndNext(
		Session session, Item item, String name,
		OrderByComparator<Item> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ITEM_WHERE);
		}
		else {
			sb.append(_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_NAME_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ItemModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ItemModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), Item.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, ItemImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, ItemImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		if (bindName) {
			queryPos.add(StringUtil.toLowerCase(name));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(item)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Item> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the items where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByName(String name) {
		for (Item item :
				findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(item);
		}
	}

	/**
	 * Returns the number of items where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching items
	 */
	@Override
	public int countByName(String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathWithPaginationCountByName;

		Object[] finderArgs = new Object[] {name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ITEM_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(StringUtil.toLowerCase(name));
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of items that the user has permission to view where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching items that the user has permission to view
	 */
	@Override
	public int filterCountByName(String name) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByName(name);
		}

		name = Objects.toString(name, "");

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_ITEM_WHERE);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_NAME_NAME_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), Item.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindName) {
				queryPos.add(StringUtil.toLowerCase(name));
			}

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_NAME_NAME_2 =
		"lower(item.name) LIKE ?";

	private static final String _FINDER_COLUMN_NAME_NAME_3 =
		"(item.name IS NULL OR item.name LIKE '')";

	private FinderPath _finderPathWithPaginationFindByNameDesc;
	private FinderPath _finderPathWithPaginationCountByNameDesc;

	/**
	 * Returns all the items where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @return the matching items
	 */
	@Override
	public List<Item> findByNameDesc(String name, String description) {
		return findByNameDesc(
			name, description, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Item> findByNameDesc(
		String name, String description, int start, int end) {

		return findByNameDesc(name, description, start, end, null);
	}

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
	@Override
	public List<Item> findByNameDesc(
		String name, String description, int start, int end,
		OrderByComparator<Item> orderByComparator) {

		return findByNameDesc(
			name, description, start, end, orderByComparator, true);
	}

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
	@Override
	public List<Item> findByNameDesc(
		String name, String description, int start, int end,
		OrderByComparator<Item> orderByComparator, boolean useFinderCache) {

		name = Objects.toString(name, "");
		description = Objects.toString(description, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByNameDesc;
		finderArgs = new Object[] {
			name, description, start, end, orderByComparator
		};

		List<Item> list = null;

		if (useFinderCache) {
			list = (List<Item>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Item item : list) {
					if (!StringUtil.wildcardMatches(
							item.getName(), name, '_', '%', '\\', false) ||
						!StringUtil.wildcardMatches(
							item.getDescription(), description, '_', '%', '\\',
							false)) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_ITEM_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAMEDESC_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_NAMEDESC_NAME_2);
			}

			boolean bindDescription = false;

			if (description.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_3);
			}
			else {
				bindDescription = true;

				sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(StringUtil.toLowerCase(name));
				}

				if (bindDescription) {
					queryPos.add(StringUtil.toLowerCase(description));
				}

				list = (List<Item>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first item in the ordered set where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item
	 * @throws NoSuchItemException if a matching item could not be found
	 */
	@Override
	public Item findByNameDesc_First(
			String name, String description,
			OrderByComparator<Item> orderByComparator)
		throws NoSuchItemException {

		Item item = fetchByNameDesc_First(name, description, orderByComparator);

		if (item != null) {
			return item;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nameLIKE");
		sb.append(name);

		sb.append(", descriptionLIKE");
		sb.append(description);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the first item in the ordered set where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item, or <code>null</code> if a matching item could not be found
	 */
	@Override
	public Item fetchByNameDesc_First(
		String name, String description,
		OrderByComparator<Item> orderByComparator) {

		List<Item> list = findByNameDesc(
			name, description, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item in the ordered set where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item
	 * @throws NoSuchItemException if a matching item could not be found
	 */
	@Override
	public Item findByNameDesc_Last(
			String name, String description,
			OrderByComparator<Item> orderByComparator)
		throws NoSuchItemException {

		Item item = fetchByNameDesc_Last(name, description, orderByComparator);

		if (item != null) {
			return item;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nameLIKE");
		sb.append(name);

		sb.append(", descriptionLIKE");
		sb.append(description);

		sb.append("}");

		throw new NoSuchItemException(sb.toString());
	}

	/**
	 * Returns the last item in the ordered set where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item, or <code>null</code> if a matching item could not be found
	 */
	@Override
	public Item fetchByNameDesc_Last(
		String name, String description,
		OrderByComparator<Item> orderByComparator) {

		int count = countByNameDesc(name, description);

		if (count == 0) {
			return null;
		}

		List<Item> list = findByNameDesc(
			name, description, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public Item[] findByNameDesc_PrevAndNext(
			long itemId, String name, String description,
			OrderByComparator<Item> orderByComparator)
		throws NoSuchItemException {

		name = Objects.toString(name, "");
		description = Objects.toString(description, "");

		Item item = findByPrimaryKey(itemId);

		Session session = null;

		try {
			session = openSession();

			Item[] array = new ItemImpl[3];

			array[0] = getByNameDesc_PrevAndNext(
				session, item, name, description, orderByComparator, true);

			array[1] = item;

			array[2] = getByNameDesc_PrevAndNext(
				session, item, name, description, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Item getByNameDesc_PrevAndNext(
		Session session, Item item, String name, String description,
		OrderByComparator<Item> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_ITEM_WHERE);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAMEDESC_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_NAMEDESC_NAME_2);
		}

		boolean bindDescription = false;

		if (description.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_3);
		}
		else {
			bindDescription = true;

			sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(ItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindName) {
			queryPos.add(StringUtil.toLowerCase(name));
		}

		if (bindDescription) {
			queryPos.add(StringUtil.toLowerCase(description));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(item)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Item> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the items that the user has permission to view where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @return the matching items that the user has permission to view
	 */
	@Override
	public List<Item> filterFindByNameDesc(String name, String description) {
		return filterFindByNameDesc(
			name, description, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the items that the user has permission to view where name LIKE &#63; and description LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param description the description
	 * @param start the lower bound of the range of items
	 * @param end the upper bound of the range of items (not inclusive)
	 * @return the range of matching items that the user has permission to view
	 */
	@Override
	public List<Item> filterFindByNameDesc(
		String name, String description, int start, int end) {

		return filterFindByNameDesc(name, description, start, end, null);
	}

	/**
	 * Returns an ordered range of all the items that the user has permissions to view where name LIKE &#63; and description LIKE &#63;.
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
	 * @return the ordered range of matching items that the user has permission to view
	 */
	@Override
	public List<Item> filterFindByNameDesc(
		String name, String description, int start, int end,
		OrderByComparator<Item> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByNameDesc(
				name, description, start, end, orderByComparator);
		}

		name = Objects.toString(name, "");
		description = Objects.toString(description, "");

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ITEM_WHERE);
		}
		else {
			sb.append(_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAMEDESC_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_NAMEDESC_NAME_2);
		}

		boolean bindDescription = false;

		if (description.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_3);
		}
		else {
			bindDescription = true;

			sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ItemModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ItemModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), Item.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, ItemImpl.class);
			}
			else {
				sqlQuery.addEntity(_FILTER_ENTITY_TABLE, ItemImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindName) {
				queryPos.add(StringUtil.toLowerCase(name));
			}

			if (bindDescription) {
				queryPos.add(StringUtil.toLowerCase(description));
			}

			return (List<Item>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the items before and after the current item in the ordered set of items that the user has permission to view where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param itemId the primary key of the current item
	 * @param name the name
	 * @param description the description
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	@Override
	public Item[] filterFindByNameDesc_PrevAndNext(
			long itemId, String name, String description,
			OrderByComparator<Item> orderByComparator)
		throws NoSuchItemException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByNameDesc_PrevAndNext(
				itemId, name, description, orderByComparator);
		}

		name = Objects.toString(name, "");
		description = Objects.toString(description, "");

		Item item = findByPrimaryKey(itemId);

		Session session = null;

		try {
			session = openSession();

			Item[] array = new ItemImpl[3];

			array[0] = filterGetByNameDesc_PrevAndNext(
				session, item, name, description, orderByComparator, true);

			array[1] = item;

			array[2] = filterGetByNameDesc_PrevAndNext(
				session, item, name, description, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Item filterGetByNameDesc_PrevAndNext(
		Session session, Item item, String name, String description,
		OrderByComparator<Item> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ITEM_WHERE);
		}
		else {
			sb.append(_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAMEDESC_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_NAMEDESC_NAME_2);
		}

		boolean bindDescription = false;

		if (description.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_3);
		}
		else {
			bindDescription = true;

			sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ItemModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ItemModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), Item.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, ItemImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, ItemImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		if (bindName) {
			queryPos.add(StringUtil.toLowerCase(name));
		}

		if (bindDescription) {
			queryPos.add(StringUtil.toLowerCase(description));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(item)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Item> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the items where name LIKE &#63; and description LIKE &#63; from the database.
	 *
	 * @param name the name
	 * @param description the description
	 */
	@Override
	public void removeByNameDesc(String name, String description) {
		for (Item item :
				findByNameDesc(
					name, description, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(item);
		}
	}

	/**
	 * Returns the number of items where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @return the number of matching items
	 */
	@Override
	public int countByNameDesc(String name, String description) {
		name = Objects.toString(name, "");
		description = Objects.toString(description, "");

		FinderPath finderPath = _finderPathWithPaginationCountByNameDesc;

		Object[] finderArgs = new Object[] {name, description};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ITEM_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAMEDESC_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_NAMEDESC_NAME_2);
			}

			boolean bindDescription = false;

			if (description.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_3);
			}
			else {
				bindDescription = true;

				sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(StringUtil.toLowerCase(name));
				}

				if (bindDescription) {
					queryPos.add(StringUtil.toLowerCase(description));
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of items that the user has permission to view where name LIKE &#63; and description LIKE &#63;.
	 *
	 * @param name the name
	 * @param description the description
	 * @return the number of matching items that the user has permission to view
	 */
	@Override
	public int filterCountByNameDesc(String name, String description) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByNameDesc(name, description);
		}

		name = Objects.toString(name, "");
		description = Objects.toString(description, "");

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_ITEM_WHERE);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAMEDESC_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_NAMEDESC_NAME_2);
		}

		boolean bindDescription = false;

		if (description.isEmpty()) {
			sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_3);
		}
		else {
			bindDescription = true;

			sb.append(_FINDER_COLUMN_NAMEDESC_DESCRIPTION_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), Item.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindName) {
				queryPos.add(StringUtil.toLowerCase(name));
			}

			if (bindDescription) {
				queryPos.add(StringUtil.toLowerCase(description));
			}

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_NAMEDESC_NAME_2 =
		"lower(item.name) LIKE ? AND ";

	private static final String _FINDER_COLUMN_NAMEDESC_NAME_3 =
		"(item.name IS NULL OR item.name LIKE '') AND ";

	private static final String _FINDER_COLUMN_NAMEDESC_DESCRIPTION_2 =
		"lower(item.description) LIKE ?";

	private static final String _FINDER_COLUMN_NAMEDESC_DESCRIPTION_3 =
		"(item.description IS NULL OR item.description LIKE '')";

	public ItemPersistenceImpl() {
		setModelClass(Item.class);

		setModelImplClass(ItemImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the item in the entity cache if it is enabled.
	 *
	 * @param item the item
	 */
	@Override
	public void cacheResult(Item item) {
		entityCache.putResult(
			entityCacheEnabled, ItemImpl.class, item.getPrimaryKey(), item);

		item.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the items in the entity cache if it is enabled.
	 *
	 * @param items the items
	 */
	@Override
	public void cacheResult(List<Item> items) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (items.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Item item : items) {
			if (entityCache.getResult(
					entityCacheEnabled, ItemImpl.class, item.getPrimaryKey()) ==
						null) {

				cacheResult(item);
			}
			else {
				item.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all items.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the item.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Item item) {
		entityCache.removeResult(
			entityCacheEnabled, ItemImpl.class, item.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Item> items) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Item item : items) {
			entityCache.removeResult(
				entityCacheEnabled, ItemImpl.class, item.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, ItemImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new item with the primary key. Does not add the item to the database.
	 *
	 * @param itemId the primary key for the new item
	 * @return the new item
	 */
	@Override
	public Item create(long itemId) {
		Item item = new ItemImpl();

		item.setNew(true);
		item.setPrimaryKey(itemId);

		return item;
	}

	/**
	 * Removes the item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemId the primary key of the item
	 * @return the item that was removed
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	@Override
	public Item remove(long itemId) throws NoSuchItemException {
		return remove((Serializable)itemId);
	}

	/**
	 * Removes the item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item
	 * @return the item that was removed
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	@Override
	public Item remove(Serializable primaryKey) throws NoSuchItemException {
		Session session = null;

		try {
			session = openSession();

			Item item = (Item)session.get(ItemImpl.class, primaryKey);

			if (item == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(item);
		}
		catch (NoSuchItemException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Item removeImpl(Item item) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(item)) {
				item = (Item)session.get(
					ItemImpl.class, item.getPrimaryKeyObj());
			}

			if (item != null) {
				session.delete(item);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (item != null) {
			clearCache(item);
		}

		return item;
	}

	@Override
	public Item updateImpl(Item item) {
		boolean isNew = item.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(item);

				item.setNew(false);
			}
			else {
				item = (Item)session.merge(item);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			entityCacheEnabled, ItemImpl.class, item.getPrimaryKey(), item,
			false);

		item.resetOriginalValues();

		return item;
	}

	/**
	 * Returns the item with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item
	 * @return the item
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	@Override
	public Item findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemException {

		Item item = fetchByPrimaryKey(primaryKey);

		if (item == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return item;
	}

	/**
	 * Returns the item with the primary key or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param itemId the primary key of the item
	 * @return the item
	 * @throws NoSuchItemException if a item with the primary key could not be found
	 */
	@Override
	public Item findByPrimaryKey(long itemId) throws NoSuchItemException {
		return findByPrimaryKey((Serializable)itemId);
	}

	/**
	 * Returns the item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemId the primary key of the item
	 * @return the item, or <code>null</code> if a item with the primary key could not be found
	 */
	@Override
	public Item fetchByPrimaryKey(long itemId) {
		return fetchByPrimaryKey((Serializable)itemId);
	}

	/**
	 * Returns all the items.
	 *
	 * @return the items
	 */
	@Override
	public List<Item> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Item> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<Item> findAll(
		int start, int end, OrderByComparator<Item> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<Item> findAll(
		int start, int end, OrderByComparator<Item> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Item> list = null;

		if (useFinderCache) {
			list = (List<Item>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ITEM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ITEM;

				sql = sql.concat(ItemModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Item>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Item item : findAll()) {
			remove(item);
		}
	}

	/**
	 * Returns the number of items.
	 *
	 * @return the number of items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ITEM);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "itemId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ITEM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item persistence.
	 */
	@Activate
	public void activate() {
		ItemModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		ItemModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByName",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByNameDesc = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNameDesc",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByNameDesc = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByNameDesc",
			new String[] {String.class.getName(), String.class.getName()});

		_setItemUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setItemUtilPersistence(null);

		entityCache.removeCache(ItemImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private void _setItemUtilPersistence(ItemPersistence itemPersistence) {
		try {
			Field field = ItemUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, itemPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = petcatalogPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.petcatalog.model.Item"),
			true);
	}

	@Override
	@Reference(
		target = petcatalogPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = petcatalogPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ITEM = "SELECT item FROM Item item";

	private static final String _SQL_SELECT_ITEM_WHERE =
		"SELECT item FROM Item item WHERE ";

	private static final String _SQL_COUNT_ITEM =
		"SELECT COUNT(item) FROM Item item";

	private static final String _SQL_COUNT_ITEM_WHERE =
		"SELECT COUNT(item) FROM Item item WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"item.itemId";

	private static final String _FILTER_SQL_SELECT_ITEM_WHERE =
		"SELECT DISTINCT {item.*} FROM PETS_ITEM item WHERE ";

	private static final String
		_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {PETS_ITEM.*} FROM (SELECT DISTINCT item.itemId FROM PETS_ITEM item WHERE ";

	private static final String
		_FILTER_SQL_SELECT_ITEM_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN PETS_ITEM ON TEMP_TABLE.itemId = PETS_ITEM.itemId";

	private static final String _FILTER_SQL_COUNT_ITEM_WHERE =
		"SELECT COUNT(DISTINCT item.itemId) AS COUNT_VALUE FROM PETS_ITEM item WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "item";

	private static final String _FILTER_ENTITY_TABLE = "PETS_ITEM";

	private static final String _ORDER_BY_ENTITY_ALIAS = "item.";

	private static final String _ORDER_BY_ENTITY_TABLE = "PETS_ITEM.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Item exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Item exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ItemPersistenceImpl.class);

}