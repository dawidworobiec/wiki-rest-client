package pl.worobiec.dawid.wiki.domain.exception;

public class NoFootballClubFoundInResponseException extends RuntimeException {

    public NoFootballClubFoundInResponseException() {
        super("Could not find football club in response exception.");
    }
}
