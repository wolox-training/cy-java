package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book Already Owned")
public class BookAlreadyOwnedException extends RuntimeException {

    public BookAlreadyOwnedException(String message) {
        super(message);
    }

}
