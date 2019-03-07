package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Book Already owned")
public class BookAlreadyOwnedException extends RuntimeException {

    public BookAlreadyOwnedException(String message) {
        super(message);
    }

}
