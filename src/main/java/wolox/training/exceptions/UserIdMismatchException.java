package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User ID Mismatch")
public class UserIdMismatchException extends RuntimeException {

    public UserIdMismatchException(String message) {
        super(message);
    }

}
