package az.edu.orient.lms.validation.validator;

import az.edu.orient.lms.validation.annotation.MobileNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber, String> {

    private static final String PHONE_NUMBER_PATTERN = "^[+]?\\d+$";

    @Override
    public void initialize(MobileNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (mobileNumber == null || mobileNumber.isBlank()) {
            return true;
        }
        return mobileNumber.matches(PHONE_NUMBER_PATTERN);
    }
}

