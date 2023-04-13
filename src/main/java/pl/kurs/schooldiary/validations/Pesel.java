package pl.kurs.schooldiary.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PeselValidator.class)
@Target({FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface Pesel {
    String message() default "Wrong PESEL!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
//TODO dokończyć omawianie o @Retention i stworzyć pesel validator
