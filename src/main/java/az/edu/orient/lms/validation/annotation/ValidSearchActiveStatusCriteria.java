package az.edu.orient.lms.validation.annotation;

import az.edu.orient.lms.validation.validator.PasswordStrengthValidator;
import az.edu.orient.lms.validation.validator.SearchActiveStatusCriteriaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SearchActiveStatusCriteriaValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSearchActiveStatusCriteria {
    String message() default "Active status can be ACTIVE or BLOCKED for ordinary search requests";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
