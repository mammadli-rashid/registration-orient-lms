package az.edu.orient.lms.exception.notfound;

import az.edu.orient.lms.exception.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends CustomException {
    private final String message;

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
        this.message = message;
    }
}
