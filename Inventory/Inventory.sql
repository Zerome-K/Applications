CREATE DATABASE INVENTORY;
use inventory;

create table products(
	UPC INT unsigned  not null auto_increment primary key,
    NAME VARCHAR(255),
    TYPE INT,
    QUANTITY INT,
    COST_PRICE DOUBLE,
    SELLING_PRICE DOUBLE,
    RE_LEVEL INT
);

ALTER TABLE products
ADD FOREIGN KEY (TYPE) REFERENCES categories(ID);

 create table admins
(
UserId int  AUTO_INCREMENT,
UserName VARCHAR(255) NOT NULL,
UserPassword varchar(255) NOT NULL,
PRIMARY KEY (UserId)
);


CREATE TABLE CUSTOMERS(
	CUSTOMER_ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) UNIQUE,
    COMPANY VARCHAR(255),
    MOBILE VARCHAR(10) UNIQUE,
    E_MAIL VARCHAR(255),
    RECEIVVABLES DOUBLE,
    PRIMARY KEY(CUSTOMER_ID)
);

ALTER TABLE products AUTO_INCREMENT = 10000;




CREATE TABLE SALE_ORDERS(
	DATE DATE,
    ORDER_ID int UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    PRODUCT_ID INT UNSIGNED,
    CUSTOMER_ID int,
    status INT1,
    AMOUNT DOUBLE,
    PRIMARY KEY(ORDER_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES customers(CUSTOMER_ID) on DELETE CASCADE,
    FOREIGN KEY (PRODUCT_ID) REFERENCES products(UPC) 
);


ALTER TABLE child_table_name 
  ADD CONSTRAINT fk_name 
  FOREIGN KEY (child_column_name) 
  REFERENCES parent_table_name(parent_column_name) 
  ON DELETE CASCADE;


ALTER TABLE sale_orders AUTO_INCREMENT = 20000;


CREATE TABLE PURCHASE_ORDERS(
	DATE DATE,
    PO_ORDER_ID int UNSIGNED NOT NULL AUTO_INCREMENT,
    VENDOR_NAME VARCHAR(255),
    PRODUCT_UPC INT UNSIGNED,
    QUANTITY INT,
    AMOUNT DOUBLE,
    PRIMARY KEY(PO_ORDER_ID),
    FOREIGN KEY (PRODUCT_UPC) REFERENCES products(UPC)
);


ALTER TABLE PURCHASE_ORDERS AUTO_INCREMENT=40000;


create TABLE packageInfo(
	id INT1 not null PRIMARY KEY,
    state VARCHAR (20)
);

CREATE TABLE categories(
	ID int not null primary key AUTO_INCREMENT,
    CATEGORY VARCHAR(255) UNIQUE
    );
    
ALTER TABLE categories
ADD UNIQUE (CATEGORY);


select * from categories;

SELECT ORDER_ID, STATUS
FROM sale_orders
INNER JOIN status
ON sale_orders.STATUS = status.id;


 select count(*) as count from products
 where quantity < re_level;
 
 
 SELECT t1.DATE,t1.ORDER_ID, t2.NAME, t4.NAME as P_name, t1.QUANTITY,t4.SELLING_PRICE, t3.state, t1.AMOUNT, t1.quantity
	FROM sale_orders as t1
    LEFT JOIN customers as t2
    ON t1.CUSTOMER_ID = t2.CUSTOMER_ID
    LEFT JOIN packageinfo as t3
    ON t1.status = t3.id
    LEFT JOIN products as t4
    ON t1.PRODUCT_ID = t4.UPC;
    
    SELECT sale_orders.ORDER_ID, customers.CUSTOMER_NAME, products.ITEM_NAME, products.SELLING_PRICE, sale_orders.QUANTITY, packageinfo.state, sale_orders.DATE, sale_orders.AMOUNT
FROM (((sale_orders
INNER JOIN customers ON sale_orders.CUSTOMER_ID = customers.CUSTOMER_ID)
INNER JOIN products ON sale_orders.PRODUCT_ID = products.UPC)
INNER JOIN packageinfo ON sale_orders.status = packageinfo.id);

ALTER TABLE sale_orders RENAME COLUMN Quantity TO Quota;
    
    ALTER TABLE customers RENAME COLUMN Customer_Name TO CUSTOMER_NAME;
    
    UPDATE sale_orders SET STATUS = 2 WHERE ORDER_ID = 20000;
    
    
SELECT po.DATE, po.PO_ORDER_ID, po.VENDOR_NAME, item.NAME, item.COST_PRICE,po.QUANTITY,po.AMOUNT
FROM purchase_orders AS po 
INNER JOIN products as item 
ON (po.PRODUCT_UPC = item.UPC);


alter table sale_orders
ADD COLUMN Quantity INT AFTER PRODUCT_ID;


select * from purchase_orders;

select * from sale_orders;

select * from packageinfo;
 
 select * from products;
 
 select * from customers;
 
  select * from categories;
 
select * from admins;

CREATE TABLE removedItems(
	id int not null PRIMARY key AUTO_INCREMENT,
    Rname VARCHAR(255),
    TotalOrders INT,
    Gained double
    );
    
    
alter table sale_orders AUTO_INCREMENT = 20000;

    
SELECT SUM(AMOUNT) as totalAmount FROM sale_orders WHERE DATE = '2022-10-19';
 