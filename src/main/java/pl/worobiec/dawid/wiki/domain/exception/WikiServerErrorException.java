package pl.worobiec.dawid.wiki.domain.exception;

import org.springframework.web.reactive.function.client.ClientResponse;

public class WikiServerErrorException extends RuntimeException {
    public WikiServerErrorException(ClientResponse clientResponse) {
        super("Wiki returned server exception with " + clientResponse.statusCode() + " status code.");
    }
}
