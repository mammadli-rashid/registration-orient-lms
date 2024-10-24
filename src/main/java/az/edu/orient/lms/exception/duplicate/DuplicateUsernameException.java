package az.edu.orient.lms.exception.duplicate;

import lombok.Getter;

@Getter
public class DuplicateUsernameException extends DuplicateException {
    private final String username;

    public DuplicateUsernameException(String username) {
        super(buildMessage(username));
        this.username = username;
    }

    private static String buildMessage(String username) {
        return "Username " + username + " already exists!";
    }
}
