package org.codehowpedia.springdata.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehowpedia.springdata.domain.Books;
import org.springframework.jdbc.core.RowMapper;

public class BooksDetailsRowMapper implements RowMapper<Books> {

    Books book = new Books();

    @Override
    public Books mapRow(ResultSet rSet, int arg1) throws SQLException {
        book.setBookId(rSet.getString("book_id"));
        book.setBookName(rSet.getString("book_name"));
        book.setAuthor1_Name(rSet.getString("author1_name"));
        book.setAuthor2_Name(rSet.getString("author2_name"));
        return book;
    }

}
