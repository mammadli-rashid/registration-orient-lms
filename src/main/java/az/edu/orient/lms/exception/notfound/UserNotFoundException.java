package az.edu.orient.lms.exception.notfound;

import lombok.Getter;

@Getter
public class UserNotFoundException extends NotFoundException {
    private String username;
    private Long id;

    public UserNotFoundException(String username) {
        super(buildMessage(username));
        this.username = username;
    }

    public UserNotFoundException(Long id) {
        super(buildMessage(buildMessage(id)));
        this.id = id;
    }

    private static String buildMessage(String username) {
        return "User " + username + " not found!";
    }
    private static String buildMessage(Long id) {
        return "User with id " + id + " not found!";
    }
}
