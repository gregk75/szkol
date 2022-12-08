create table PETS_ITEM (
	itemId LONG not null primary key,
	productId VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(1200) null,
	imageUrl VARCHAR(75) null,
	imageThumbUrl VARCHAR(75) null,
	price DOUBLE
);