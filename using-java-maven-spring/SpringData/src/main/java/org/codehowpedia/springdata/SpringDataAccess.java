
package org.codehowpedia.springdata;

import java.util.ArrayList;

import org.codehowpedia.springdata.domain.Books;

public interface SpringDataAccess {

    boolean testConnection();

    int insertBooksDetail(Books book);

    Books getBooksDetail(Books book);

    ArrayList<Books> getAllBooksDetail();

    int updateBooksDetail(Books book);

    int deleteBooksDetail(Books book);
}
