package org.genesiscode.practicethree.annotations;

import org.genesiscode.practicethree.annotations.validators.GreaterThanValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = GreaterThanValidator.class)
public @interface GreaterThan {

    String message() default "must be greater than one digit.";

    int valueMin() default 9;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
