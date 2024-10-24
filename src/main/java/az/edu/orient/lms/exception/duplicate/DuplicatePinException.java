package az.edu.orient.lms.exception.duplicate;

import lombok.Getter;

@Getter
public class DuplicatePinException extends DuplicateException {
    private final String username;

    public DuplicatePinException(String pin) {
        super(buildMessage(pin));
        this.username = pin;
    }

    private static String buildMessage(String pin) {
        return "PIN " + pin + " already exists!";
    }
}