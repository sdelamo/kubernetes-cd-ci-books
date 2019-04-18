package example.micronaut.books;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Collections;
import java.util.List;

@Controller("/books")
public class BooksController {
    private final BookConfiguration bookConfiguration;

    public BooksController(BookConfiguration bookConfiguration) {
        this.bookConfiguration = bookConfiguration;
    }

    @Get
    List<Book> index() {
        return Collections.singletonList(new Book(bookConfiguration.getBookTitle(), bookConfiguration.getBookPages()));
    }
}
