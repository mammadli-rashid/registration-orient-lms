package az.edu.orient.lms.exception.duplicate;

import lombok.Getter;

@Getter
public class DuplicateEmailException extends DuplicateException {
    private final String email;

    public DuplicateEmailException(String email) {
        super(buildMessage(email));
        this.email = email;
    }

    private static String buildMessage(String email) {
        return "There is already user registered with the email " + email + "!";
    }
}
