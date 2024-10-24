package az.edu.orient.lms.exception.incorrectinput;

import lombok.Getter;

@Getter
public class IncorrectUsernameOrPasswordException extends IncorrectInputException {
    public IncorrectUsernameOrPasswordException() {
        super("Username or password is incorrect!");
    }
}
