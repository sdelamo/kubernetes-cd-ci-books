package example.micronaut.books


import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class BooksControllerConfigurationOverrideSpec extends Specification {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, [
            'app.book-title': 'Harry Potter',
            'app.book-pages': 80,
    ])

    @AutoCleanup
    @Shared
    HttpClient httpClient = embeddedServer.applicationContext.createBean(HttpClient, embeddedServer.URL)

    BlockingHttpClient getClient() {
        httpClient.toBlocking()
    }

    void "accesing the book controller returns a list of books, book title comes from config"() {
        when:
        HttpRequest request = HttpRequest.GET('/books')

        HttpResponse<List<Book>> resp = client.exchange(request, Argument.of(List, Book))

        then:
        resp.status() == HttpStatus.OK
        resp.body().size() == 1
        resp.body().get(0).title == 'Harry Potter'
        resp.body().get(0).pages == 80
    }


}
