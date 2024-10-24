package az.edu.orient.lms.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public abstract class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
}
