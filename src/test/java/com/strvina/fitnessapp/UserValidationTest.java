package com.strvina.fitnessapp;

import com.strvina.fitnessapp.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidationTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testEmailInvalid() {
        User user = new User();
        user.setEmail("nevalidanemail"); // nevalidan email
        user.setPassword("Dobrap@ss1");  // validna lozinka
        user.setName("Marko");
        

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        violations.forEach(v -> System.out.println("Polje: " + v.getPropertyPath() + " - Poruka: " + v.getMessage()));
        System.out.println("Ukupan broj grešaka: " + violations.size());

        assertFalse(violations.isEmpty(), "Validator bi trebao da detektuje nevalidan email");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")),
                   "Email polje mora biti problem");
    }


    @Test
public void testLozinkaBezVelikogSlova() {
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("slass"); // nema veliko slovo
    user.setName("Mika");
    

    Set<ConstraintViolation<User>> violations = validator.validate(user);

    violations.forEach(v -> System.out.println("Polje: " + v.getPropertyPath() + " - Poruka: " + v.getMessage()));
    System.out.println("Ukupan broj grešaka: " + violations.size());

    assertFalse(violations.isEmpty(), "Validator bi trebao da detektuje problem sa lozinkom");
    assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")), "Lozinka mora biti problem");
}


}
