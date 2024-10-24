package az.edu.orient.lms.validation.annotation;

import az.edu.orient.lms.validation.validator.ValidStartEndDatesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidStartEndDatesValidator.class)
@Documented
public @interface ValidStartEndDates {
    String message() default "Start date must be earlier than end date!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
