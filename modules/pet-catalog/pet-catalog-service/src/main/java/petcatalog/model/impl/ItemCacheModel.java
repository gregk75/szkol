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

package petcatalog.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import petcatalog.model.Item;

/**
 * The cache model class for representing Item in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ItemCacheModel implements CacheModel<Item>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ItemCacheModel)) {
			return false;
		}

		ItemCacheModel itemCacheModel = (ItemCacheModel)object;

		if (itemId == itemCacheModel.itemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{itemId=");
		sb.append(itemId);
		sb.append(", productId=");
		sb.append(productId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", imageUrl=");
		sb.append(imageUrl);
		sb.append(", imageThumbUrl=");
		sb.append(imageThumbUrl);
		sb.append(", price=");
		sb.append(price);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Item toEntityModel() {
		ItemImpl itemImpl = new ItemImpl();

		itemImpl.setItemId(itemId);

		if (productId == null) {
			itemImpl.setProductId("");
		}
		else {
			itemImpl.setProductId(productId);
		}

		if (name == null) {
			itemImpl.setName("");
		}
		else {
			itemImpl.setName(name);
		}

		if (description == null) {
			itemImpl.setDescription("");
		}
		else {
			itemImpl.setDescription(description);
		}

		if (imageUrl == null) {
			itemImpl.setImageUrl("");
		}
		else {
			itemImpl.setImageUrl(imageUrl);
		}

		if (imageThumbUrl == null) {
			itemImpl.setImageThumbUrl("");
		}
		else {
			itemImpl.setImageThumbUrl(imageThumbUrl);
		}

		itemImpl.setPrice(price);

		itemImpl.resetOriginalValues();

		return itemImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemId = objectInput.readLong();
		productId = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		imageUrl = objectInput.readUTF();
		imageThumbUrl = objectInput.readUTF();

		price = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(itemId);

		if (productId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productId);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (imageUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imageUrl);
		}

		if (imageThumbUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imageThumbUrl);
		}

		objectOutput.writeDouble(price);
	}

	public long itemId;
	public String productId;
	public String name;
	public String description;
	public String imageUrl;
	public String imageThumbUrl;
	public double price;

}