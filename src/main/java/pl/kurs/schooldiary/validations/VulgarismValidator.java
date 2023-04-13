package pl.kurs.schooldiary.validations;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VulgarismValidator implements ConstraintValidator<NonVulgar, String> {
    @Value("${vulgarisms}")
    private List<String> vulgarWords;
    //TODO zrobic tworzenie sciezki dokumentu

    @Override
    public void initialize(NonVulgar constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        vulgarWords = vulgarWords.stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        s = Optional.ofNullable(s)
                .map(String::toLowerCase)
                .map(String::trim)
                .orElse("");
        return !vulgarWords.contains(s);
    }
}
