package org.codehowpedia.springdata.domain;

public class Books {
    private String bookId;
    private String bookName;
    private String author1_Name;
    private String author2_Name;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor1_Name() {
        return author1_Name;
    }

    public void setAuthor1_Name(String author1_Name) {
        this.author1_Name = author1_Name;
    }

    public String getAuthor2_Name() {
        return author2_Name;
    }

    public void setAuthor2_Name(String author2_Name) {
        this.author2_Name = author2_Name;
    }

}
