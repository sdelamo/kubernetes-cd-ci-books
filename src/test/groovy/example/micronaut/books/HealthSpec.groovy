package example.micronaut.books

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class HealthSpec extends Specification {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @AutoCleanup
    @Shared
    HttpClient httpClient = embeddedServer.applicationContext.createBean(HttpClient, embeddedServer.URL)

    BlockingHttpClient getClient() {
        httpClient.toBlocking()
    }

    void "/health is enabled"() {
        given:
        HttpRequest request = HttpRequest.GET('/health')

        when:
        HttpResponse<String> resp = client.exchange(request, String)

        then:
        resp.status() == HttpStatus.OK
        resp.body().contains '"status":"UP"'

    }
}
