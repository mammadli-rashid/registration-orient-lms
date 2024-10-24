package az.edu.orient.lms.exception.notfound;

import lombok.Getter;

@Getter
public class RegistrationRequestTokenNotFoundException extends NotFoundException{
    private final String token;

    public RegistrationRequestTokenNotFoundException(String token) {
        super(buildMessage(token));
        this.token = token;
    }

    private static String buildMessage(String token) {
        return "Registration token " + token + " not found!";
    }
}
