package org.codehowpedia.springdata;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.codehowpedia.springdata.domain.Books;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring-data-config.xml")
public class SpringDataAccessTest {
    final static Logger lgr = Logger.getLogger(SpringDataAccessTest.class);
    @Autowired
    SpringDataAccess springDao;
    @Autowired
    Books book;

    /** make use of this method to debug the jdbc connection status initially */
    // @Test
    public void testJdbcConnection() {
        springDao.testConnection(); // returns true if connection is succcessful!
    }

    // @Test
    public void create() {
        book.setBookId("idhia14");
        book.setBookName("Hibernate In Action");
        book.setAuthor1_Name("Christian Bauer");
        int insertedRows = springDao.insertBooksDetail(book);
        lgr.info("Number of inserted row(s) : " + insertedRows);
    }

    // @Test
    public void read() {
        book.setBookId("idhia14");
        book = springDao.getBooksDetail(book);
        lgr.info("Book Details : ");
        lgr.info("\n Book Name : " + book.getBookName());
        lgr.info("\n Author1 Name : " + book.getAuthor1_Name());
    }

    // @Test
    public void readAll() {
        ArrayList<Books> booksList = springDao.getAllBooksDetail();
        lgr.info("Total books available : " + booksList.size());

    }

    // @Test
    public void update() {
        book.setBookId("idhia14");
        book.setBookName("Java Persistence With Hibernate");
        book.setAuthor2_Name("Gavin King");
        int updatedRows = springDao.updateBooksDetail(book); // update the book name and author2_name
        lgr.info("Number of updated row(s) : " + updatedRows);
    }

    // @Test
    public void delete() {
        book.setBookId("idhia14");
        int deletedRows = springDao.deleteBooksDetail(book);
        lgr.info("Number of deleted row(s) : " + deletedRows);
    }

    @Test
    public void doNothingJunitTestMethod() {
        // A `do nothing` JUnit test method to make the maven build successful, in case if the DB connections are not configured.
    }

}
