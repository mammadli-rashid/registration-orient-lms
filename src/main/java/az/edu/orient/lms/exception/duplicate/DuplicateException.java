package az.edu.orient.lms.exception.duplicate;

import az.edu.orient.lms.exception.CustomException;
import org.springframework.http.HttpStatus;

public abstract class DuplicateException extends CustomException {
    private String message;
    public DuplicateException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
