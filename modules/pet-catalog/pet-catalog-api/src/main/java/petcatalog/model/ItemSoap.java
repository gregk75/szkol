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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link petcatalog.service.http.ItemServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ItemSoap implements Serializable {

	public static ItemSoap toSoapModel(Item model) {
		ItemSoap soapModel = new ItemSoap();

		soapModel.setItemId(model.getItemId());
		soapModel.setProductId(model.getProductId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setImageUrl(model.getImageUrl());
		soapModel.setImageThumbUrl(model.getImageThumbUrl());
		soapModel.setPrice(model.getPrice());

		return soapModel;
	}

	public static ItemSoap[] toSoapModels(Item[] models) {
		ItemSoap[] soapModels = new ItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ItemSoap[][] toSoapModels(Item[][] models) {
		ItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ItemSoap[] toSoapModels(List<Item> models) {
		List<ItemSoap> soapModels = new ArrayList<ItemSoap>(models.size());

		for (Item model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ItemSoap[soapModels.size()]);
	}

	public ItemSoap() {
	}

	public long getPrimaryKey() {
		return _itemId;
	}

	public void setPrimaryKey(long pk) {
		setItemId(pk);
	}

	public long getItemId() {
		return _itemId;
	}

	public void setItemId(long itemId) {
		_itemId = itemId;
	}

	public String getProductId() {
		return _productId;
	}

	public void setProductId(String productId) {
		_productId = productId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getImageUrl() {
		return _imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		_imageUrl = imageUrl;
	}

	public String getImageThumbUrl() {
		return _imageThumbUrl;
	}

	public void setImageThumbUrl(String imageThumbUrl) {
		_imageThumbUrl = imageThumbUrl;
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	private long _itemId;
	private String _productId;
	private String _name;
	private String _description;
	private String _imageUrl;
	private String _imageThumbUrl;
	private double _price;

}