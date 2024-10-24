package az.edu.orient.lms.exception.incorrectinput;

import az.edu.orient.lms.exception.CustomException;
import org.springframework.http.HttpStatus;

public class IncorrectInputException extends CustomException {
    public IncorrectInputException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
