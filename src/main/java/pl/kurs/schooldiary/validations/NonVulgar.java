package pl.kurs.schooldiary.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = VulgarismValidator.class)
@Target({FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface NonVulgar {
    String message() default "Vulgar word provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
