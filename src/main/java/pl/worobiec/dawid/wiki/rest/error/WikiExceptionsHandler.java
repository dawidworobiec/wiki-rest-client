package pl.worobiec.dawid.wiki.rest.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.worobiec.dawid.wiki.domain.exception.NoFootballClubFoundInResponseException;
import pl.worobiec.dawid.wiki.domain.exception.WikiClientErrorException;
import pl.worobiec.dawid.wiki.domain.exception.WikiServerErrorException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class WikiExceptionsHandler {

    private final Logger LOG = LoggerFactory.getLogger(WikiExceptionsHandler.class);

    @ExceptionHandler(value = NoFootballClubFoundInResponseException.class)
    public ResponseEntity<String> handleNoFootballClubFoundInResponseException(NoFootballClubFoundInResponseException e) {
        logError(e);
        return new ResponseEntity<>("No football club found in top 10 results", NOT_FOUND);
    }

    @ExceptionHandler(value = WikiClientErrorException.class)
    public ResponseEntity<String> handleWikiClientErrorException(WikiClientErrorException e) {
        logError(e);
        return new ResponseEntity<>("Unexpected wiki client error exception.", INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = WikiServerErrorException.class)
    public ResponseEntity<String> handleWikiServerErrorException(WikiServerErrorException e) {
        logError(e);
        return new ResponseEntity<>("Unexpected wiki server error exception.", INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> otherExceptionHandler(Exception e) {
        logError(e);
        return new ResponseEntity<>("Unexpected client error exception.", INTERNAL_SERVER_ERROR);
    }

    private void logError(Exception e) {
        LOG.error(e.getMessage(), e);
    }
}
