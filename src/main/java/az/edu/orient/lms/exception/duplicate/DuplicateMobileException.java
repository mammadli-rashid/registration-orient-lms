package az.edu.orient.lms.exception.duplicate;

import lombok.Getter;

@Getter
public class DuplicateMobileException extends DuplicateException{
    private final String mobile;

    public DuplicateMobileException(String mobile) {
        super(buildMessage(mobile));
        this.mobile = mobile;
    }

    private static String buildMessage(String mobile) {
        return "There is already user registered with the mobile number " + mobile + "!";
    }
}
