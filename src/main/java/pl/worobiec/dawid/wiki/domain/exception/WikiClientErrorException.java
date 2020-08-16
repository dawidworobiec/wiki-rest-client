package pl.worobiec.dawid.wiki.domain.exception;

import org.springframework.web.reactive.function.client.ClientResponse;

public class WikiClientErrorException extends RuntimeException {
    public WikiClientErrorException(ClientResponse clientResponse) {
        super("Wiki returned client exception with " + clientResponse.statusCode() + " status code.");
    }
}
