package com.strvina.fitnessapp;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class RegexTest {

    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
        message = "Lozinka mora imati veliko slovo, malo slovo, broj i specijalni karakter"
    )
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        RegexTest test = new RegexTest();

        test.setPassword("slabap@ss1"); // nema veliko slovo

        Set<ConstraintViolation<RegexTest>> violations = validator.validate(test);

        violations.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));

        System.out.println("Ima grešaka? " + !violations.isEmpty());
    }
}
