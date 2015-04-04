# Demo on CRUD operations using spring's JdbcTemplate

First you need to configure the oracle database to perform the CRUD operations explained in this demo.

After the database configuration, create the following table:

`
CREATE TABLE BOOKS_DETAILS
  (
    BOOK_ID      VARCHAR2(10),
    BOOK_NAME    VARCHAR2(50),
    AUTHOR1_NAME VARCHAR2(30),
    AUTHOR2_NAME VARCHAR2(30),
    CONSTRAINT BOOKID_PK PRIMARY KEY (BOOK_ID)
  );
`

Once the database table is created, you can just play with the test methods written in this demo.

Refer the `SpringDataAccessTest` class for the methods explained below:
     
     testJdbcConnection() -> to test the jdbc connection status

     create() -> to insert new book entry in to the above created books_details table

     read() -> to get the detail of a particular book entry

     readAll() -> to get details of all the available books

     update() -> to update the details of a particular book

     delete() -> to delete the details of a book by using its book_id
     
     
     
