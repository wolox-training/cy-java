package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Book ID Mismatch")
public class BookIdMismatchException extends RuntimeException {

    public BookIdMismatchException(String message) {
        super(message);
    }
}
