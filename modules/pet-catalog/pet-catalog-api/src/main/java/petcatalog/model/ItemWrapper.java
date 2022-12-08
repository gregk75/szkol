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

package petcatalog.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Item}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Item
 * @generated
 */
public class ItemWrapper
	extends BaseModelWrapper<Item> implements Item, ModelWrapper<Item> {

	public ItemWrapper(Item item) {
		super(item);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemId", getItemId());
		attributes.put("productId", getProductId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("imageUrl", getImageUrl());
		attributes.put("imageThumbUrl", getImageThumbUrl());
		attributes.put("price", getPrice());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long itemId = (Long)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String productId = (String)attributes.get("productId");

		if (productId != null) {
			setProductId(productId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String imageUrl = (String)attributes.get("imageUrl");

		if (imageUrl != null) {
			setImageUrl(imageUrl);
		}

		String imageThumbUrl = (String)attributes.get("imageThumbUrl");

		if (imageThumbUrl != null) {
			setImageThumbUrl(imageThumbUrl);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}
	}

	/**
	 * Returns the description of this item.
	 *
	 * @return the description of this item
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the image thumb url of this item.
	 *
	 * @return the image thumb url of this item
	 */
	@Override
	public String getImageThumbUrl() {
		return model.getImageThumbUrl();
	}

	/**
	 * Returns the image url of this item.
	 *
	 * @return the image url of this item
	 */
	@Override
	public String getImageUrl() {
		return model.getImageUrl();
	}

	/**
	 * Returns the item ID of this item.
	 *
	 * @return the item ID of this item
	 */
	@Override
	public long getItemId() {
		return model.getItemId();
	}

	/**
	 * Returns the name of this item.
	 *
	 * @return the name of this item
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the price of this item.
	 *
	 * @return the price of this item
	 */
	@Override
	public double getPrice() {
		return model.getPrice();
	}

	/**
	 * Returns the primary key of this item.
	 *
	 * @return the primary key of this item
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the product ID of this item.
	 *
	 * @return the product ID of this item
	 */
	@Override
	public String getProductId() {
		return model.getProductId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the description of this item.
	 *
	 * @param description the description of this item
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the image thumb url of this item.
	 *
	 * @param imageThumbUrl the image thumb url of this item
	 */
	@Override
	public void setImageThumbUrl(String imageThumbUrl) {
		model.setImageThumbUrl(imageThumbUrl);
	}

	/**
	 * Sets the image url of this item.
	 *
	 * @param imageUrl the image url of this item
	 */
	@Override
	public void setImageUrl(String imageUrl) {
		model.setImageUrl(imageUrl);
	}

	/**
	 * Sets the item ID of this item.
	 *
	 * @param itemId the item ID of this item
	 */
	@Override
	public void setItemId(long itemId) {
		model.setItemId(itemId);
	}

	/**
	 * Sets the name of this item.
	 *
	 * @param name the name of this item
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the price of this item.
	 *
	 * @param price the price of this item
	 */
	@Override
	public void setPrice(double price) {
		model.setPrice(price);
	}

	/**
	 * Sets the primary key of this item.
	 *
	 * @param primaryKey the primary key of this item
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the product ID of this item.
	 *
	 * @param productId the product ID of this item
	 */
	@Override
	public void setProductId(String productId) {
		model.setProductId(productId);
	}

	@Override
	protected ItemWrapper wrap(Item item) {
		return new ItemWrapper(item);
	}

}