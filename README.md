CS407
=====

CS407 Project

<h3>Database Schema:</h3>

<b>Tables (PK = Primary Key, FK = Foreign Key, NN = Not Null, UQ = Unique)</b>
<br /><br />
<table>
 <tr><th colspan='3'>Accounts</th></tr>
 <tr><th>Column Name</th><th>Data Type</th><th>Attributes</th></tr>
 <tr><th>id</th><td>numeric</td><td>PK, NN, UQ</td></tr>
 <tr><th>username</th><td>varchar 100</td><td>UQ, NN</td></tr>
 <tr><th>email</th><td>varchar 250</td><td>NN</td></tr>
 <tr><th>pw_hash</th><td>varchar 250</td><td>-</td></tr>
 <tr><th>permission_level</th><td>numeric</td><td>default 0</td></tr>
</table>
<br /><br />
<p>
<i>The Accounts table would hold user accounts.
Dr. Williams requested that we include an administrator interface to modify products.
This means that permission_level will be a toggle bit for whether a user is an admin or not (0 user, 1 admin)
Usernames must be unique across accounts, however email addresses do not. pw_hash can store an arbitrary password
hash (doesn't have to be secure really, just something other than plain text, maybe SHA-256)</i>
</p>
<br /><br />
<table>
 <tr><th colspan='3'>Products</th></tr>
 <tr><th>Column Name</th><th>Data Type</th><th>Attributes</th></tr>
 <tr><th>id</th><td>numeric</td><td>PK, NN, UQ</td></tr>
 <tr><th>productname</th><td>varchar 200</td><td>UQ, NN</td></tr>
 <tr><th>price</th><td>numeric</td><td>NN</td></tr>
 <tr><th>category</th><td>numeric</td><td>FK --> cid in Categories</td></tr>
 <tr><th>description</th><td>varchar 500</td><td>-</td></tr>
</table>
<br /><br />
<p>
<i>The Products table will house all of our products that we offer. Each product has an id, a name, price (double),
a category (join on cid in categories table) and a description.</i>
</p>
<br /><br />
<table>
 <tr><th colspan='3'>Categories</th></tr>
 <tr><th>Column Name</th><th>Data Type</th><th>Attributes</th></tr>
 <tr><th>cid</th><td>numeric</td><td>PK, NN, UQ</td></tr>
 <tr><th>categoryname</th><td>varchar 200</td><td>NN, UQ</td></tr>
</table>
<br /><br />
<p>
 <i>The Categories table will serve as a lookup table to categorize our products
e.g.
SELECT *
FROM Products AS p
JOIN Categories AS c
ON c.cid = p.id
WHERE c.categoryname = 'Electronics';</i>
</p>


The following SQL will create the tables we need.  Only changes were a few data type choices, and a join table for categories and products (many to many).

!!!!!!!!!!!IMPORTANT!!!!!!!!   Database is "CS407Project" with APP and pass


create table "APP".ACCOUNTS
(
	ID INTEGER not null primary key,
	USERNAME VARCHAR(100) not null,
	EMAIL VARCHAR(250) not null,
	PW_HASH VARCHAR(250) not null,
	PERMISSION_LEVEL SMALLINT default 0 not null
);

create table "APP".PRODUCTS
(
	ID INTEGER not null primary key,
	PRODUCTNAME VARCHAR(200) not null,
	PRICE DECIMAL(5) not null,
	DESCRIPTION VARCHAR(500)
);

create table "APP".CATEGORIES
(
	CID INTEGER not null primary key,
	CATEGORYNAME VARCHAR(200) not null
);

create table "APP".PRODUCTS_CATEGORIES
(
	PID INTEGER not null,
	CID INTEGER not null,
	PRIMARY KEY(PID, CID),
	FOREIGN KEY(PID) REFERENCES PRODUCTS(ID),
	FOREIGN KEY(CID) REFERENCES CATEGORIES(CID)
);

