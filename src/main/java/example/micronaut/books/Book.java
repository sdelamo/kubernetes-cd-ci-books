package example.micronaut.books;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Book {

    private String title;
    private Integer pages;

    public Book() {
    }

    public Book(String title, Integer pages) {
        this.title = title;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
