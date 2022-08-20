package org.genesiscode.practicethree.annotation.validator;

import org.genesiscode.practicethree.annotation.GreaterThan;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GreaterThanValidator implements ConstraintValidator<GreaterThan, Long> {

    private int valueMin;

    @Override
    public void initialize(GreaterThan constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        valueMin = constraintAnnotation.valueMin();
    }

    @Override
    public boolean isValid(Long seed, ConstraintValidatorContext constraintValidatorContext) {
        return seed > valueMin;
    }
}
