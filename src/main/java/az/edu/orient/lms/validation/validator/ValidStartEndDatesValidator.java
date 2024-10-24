package az.edu.orient.lms.validation.validator;

import az.edu.orient.lms.validation.annotation.ValidStartEndDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Date;

public class ValidStartEndDatesValidator implements ConstraintValidator<ValidStartEndDates, Object> {

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            Field startDateField = object.getClass().getDeclaredField("startDate");
            Field endDateField = object.getClass().getDeclaredField("endDate");

            startDateField.setAccessible(true);
            endDateField.setAccessible(true);

            Date startDate = (Date) startDateField.get(object);
            Date endDate = (Date) endDateField.get(object);

            if (startDate == null || endDate == null) {
                return true;
            }

            return startDate.before(endDate);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }
}