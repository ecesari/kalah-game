package com.es.kalaha.validation;

import com.es.kalaha.application.dto.PlayerCreateRequest;
import com.github.javafaker.Faker;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserValidations {
    private static ValidatorFactory factory;
    private static Validator validator;
    private static Faker faker;

    @BeforeAll
    static void initAll() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        faker = new Faker();

    }

    @Test
    public void Given_NameNull_PlayerCreateRequest_ShouldViolateValidation() {
        PlayerCreateRequest request = new PlayerCreateRequest();
        Set<ConstraintViolation<PlayerCreateRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void Given_NameNull_PlayerCreateRequest_ShouldPassValidation() {
        PlayerCreateRequest request = new PlayerCreateRequest();
        request.name = faker.internet().slug();
        Set<ConstraintViolation<PlayerCreateRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }
}
