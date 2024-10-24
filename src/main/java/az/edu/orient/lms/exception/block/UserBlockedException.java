package az.edu.orient.lms.exception.block;

import az.edu.orient.lms.exception.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserBlockedException extends CustomException {
    private final String username;

    public UserBlockedException(String username) {
        super(HttpStatus.CONFLICT, buildMessage(username));
        this.username = username;
    }

    public static String buildMessage(String username) {
        return "User " + username + " blocked! Please contact with our office!";
    }
}
