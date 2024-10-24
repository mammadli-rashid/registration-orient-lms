package az.edu.orient.lms.validation.validator;

import az.edu.orient.lms.validation.annotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator implements ConstraintValidator<ValidPassword, String> {

    private static final int MIN_LENGTH = 8;  // Minimum length of the password
    private static final String LOWERCASE_REGEX = "(.*[a-z].*)";  // At least one lowercase letter
    private static final String UPPERCASE_REGEX = "(.*[A-Z].*)";  // At least one uppercase letter
    private static final String DIGIT_REGEX = "(.*[0-9].*)";        // At least one digit
    private static final String SPECIAL_CHAR_REGEX = "(.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*$)";  // At least one special character

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        boolean hasMinLength = password.length() >= MIN_LENGTH;
        boolean hasLowercase = password.matches(LOWERCASE_REGEX);
        boolean hasUppercase = password.matches(UPPERCASE_REGEX);
        boolean hasDigit = password.matches(DIGIT_REGEX);
        boolean hasSpecialChar = password.matches(SPECIAL_CHAR_REGEX);

        return hasMinLength && hasLowercase && hasUppercase && hasDigit && hasSpecialChar;
    }
}
