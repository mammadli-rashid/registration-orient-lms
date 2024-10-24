package az.edu.orient.lms.validation.validator;

import az.edu.orient.lms.constant.ActiveStatus;
import az.edu.orient.lms.validation.annotation.ValidSearchActiveStatusCriteria;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SearchActiveStatusCriteriaValidator implements ConstraintValidator<ValidSearchActiveStatusCriteria, ActiveStatus> {

    @Override
    public void initialize(ValidSearchActiveStatusCriteria constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ActiveStatus activeStatus, ConstraintValidatorContext constraintValidatorContext) {
        return !activeStatus.equals(ActiveStatus.DELETED);
    }
}
