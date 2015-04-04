package org.codehowpedia.springdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehowpedia.springdata.domain.Books;
import org.codehowpedia.springdata.rowmapper.BooksDetailsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SpringDataAccessImpl implements SpringDataAccess {
    final static Logger lgr = Logger.getLogger(SpringDataAccessImpl.class);
    @Autowired
    JdbcTemplate springJdbc;

    @Override
    public boolean testConnection() {
        if (springJdbc != null) {
            lgr.info("connection SUCCESS!");
            return true;
        }
        else {
            lgr.info("connection FAIL :(");
            return false;
        }
    }

    @Override
    @Transactional
    public int insertBooksDetail(Books book) {

        String sql = "insert into BOOKS_DETAILS(book_id,book_name,author1_name) values(?,?,?)";
        lgr.info("Query to insert books detail : " + sql);
        int insertedRows = springJdbc.update(sql, book.getBookId(), book.getBookName(), book.getAuthor1_Name());
        return insertedRows;
    }

    @Override
    @Transactional
    public Books getBooksDetail(Books book) {
        String sql = "select * from books_details where book_id= ?";
        book = springJdbc.queryForObject(sql, new Object[] {book.getBookId()}, new BooksDetailsRowMapper());
        return book;
    }

    @Override
    @Transactional
    public ArrayList<Books> getAllBooksDetail() {
        ArrayList<Books> booksList = new ArrayList<Books>();
        String sql = "select * from books_details";
        List<Map<String, Object>> rows = springJdbc.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Books book = new Books();
            book.setBookId((String) row.get("book_id"));
            book.setBookName((String) row.get("book_name"));
            book.setAuthor1_Name((String) row.get("author1_name"));
            book.setAuthor2_Name((String) row.get("author2_name"));
            booksList.add(book);
        }
        return booksList;
    }

    @Override
    @Transactional
    public int updateBooksDetail(Books book) {
        String sql = "update books_details set book_name = ?,author2_name=? where book_id=?";
        int updatedRows = springJdbc.update(sql, new Object[] {book.getBookName(), book.getAuthor2_Name(), book.getBookId()});
        return updatedRows;
    }

    @Override
    @Transactional
    public int deleteBooksDetail(Books book) {
        String sql = "delete from books_details where book_id= ?";
        int deletedRows = springJdbc.update(sql, new Object[] {book.getBookId()});
        return deletedRows;
    }
}
