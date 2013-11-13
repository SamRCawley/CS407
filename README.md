CS407
=====

CS407 Project

Database Schema:

Tables (PK = Primary Key, FK = Foreign Key, NN = Not Null, UQ = Unique)

 - Accounts
    - id [numeric] (PK, NN, UQ)
    - username [varchar 100] (UQ, NN)
    - email [varchar 250] (NN)
    - pw_hash [varchar 250]
    - permission_level [numeric] (default 0)

 - Products
    - id [numeric](PK, NN, UQ)
    - productname [varchar 200](UQ, NN)
    - price [numeric](NN)
    - category [numeric](FK --> cid in Categories table)
    - description [varchar 500]

 - Categories
   - cid [numeric] (PK, NN, UQ)
   - categoryname [varchar 200] (NN, UQ)


The Accounts table would hold user accounts.
Dr. Williams requested that we include an administrator interface to modify products.
This means that permission_level will be a toggle bit for whether a user is an admin or not (0 user, 1 admin)
Usernames must be unique across accounts, however email addresses do not. pw_hash can store an arbitrary password
hash (doesn't have to be secure really, just something other than plain text, maybe SHA-256)

The Products table will house all of our products that we offer. Each product has an id, a name, price (double),
a category (join on cid in categories table) and a description.

The Categories table will serve as a lookup table to categorize our products
e.g.
SELECT *
FROM Products AS p
JOIN Categories AS c
ON c.cid = p.id
WHERE c.categoryname = 'Electronics';
