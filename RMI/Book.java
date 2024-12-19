package RMI;

import java.io.Serializable;

public class Book implements Serializable{
    private static final long serialVersionUID = 20241123L;
    private String id;
    private String title;
    private String author;
    private int yearPublished;
    private int pageCount;
    private String code;

    public Book() {}
    public Book(String id, String title, String author, int yearPublished, int pageCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.pageCount = pageCount;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter và Setter cho title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter và Setter cho author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter và Setter cho yearPublished
    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    // Getter và Setter cho pageCount
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    // Getter và Setter cho code
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
