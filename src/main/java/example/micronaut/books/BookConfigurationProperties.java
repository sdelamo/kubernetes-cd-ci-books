package example.micronaut.books;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("app")
public class BookConfigurationProperties implements BookConfiguration {

    public static final String DEFAULT_BOOK_TITLE = "Building Microservices";

    public static final Integer DEFAULT_BOOK_PAGES = 20;

    private String bookTitle = DEFAULT_BOOK_TITLE;

    private Integer bookPages = DEFAULT_BOOK_PAGES;

    @Override
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public Integer getBookPages() {
        return bookPages;
    }

    public void setBookPages(Integer bookPages) {
        this.bookPages = bookPages;
    }
}
