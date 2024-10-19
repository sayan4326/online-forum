package com.yourcompanyname.forum.customValidation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = AlphaNumericpasswordValidator.class)
@Documented
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
public @interface AlphaNumericPassword {

	String message() default "Password must be at least 6 characters long and include an uppercase letter, lowercase letter, number, and special character";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
