package pl.kurs.schooldiary.validations;

import javax.swing.text.html.Option;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

    @Override
    public void initialize(Pesel constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)
            return false;
        return s.matches("\\d{11}");
    }
}
