package pl.kurs.schooldiary.validations;

import org.springframework.beans.factory.annotation.Value;
import pl.kurs.schooldiary.services.PathBuilderService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VulgarismValidator implements ConstraintValidator<NonVulgar, String> {
//    @Value("${vulgarisms}")
        private List<String> vulgarWords;
    //TODO zrobic tworzenie sciezki dokumentu

    @Override
    public void initialize(NonVulgar constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        PathBuilderService pathBuilderService = new PathBuilderService();
        String filePath = pathBuilderService.createPath();
        try {
            String content = Files.readString(Path.of(filePath));
            vulgarWords = Arrays.asList(content.split(","));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vulgarWords = vulgarWords.stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
//        PathBuilderService pathBuilderService = new PathBuilderService();
//        String filePath = pathBuilderService.createPath();
//        try {
//            String content = Files.readString(Path.of(filePath));
//            vulgarWords = Arrays.asList(content.split(","));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        s = Optional.ofNullable(s)
                .map(String::toLowerCase)
                .map(String::trim)
                .orElse("");
        return !vulgarWords.contains(s);
    }
}
