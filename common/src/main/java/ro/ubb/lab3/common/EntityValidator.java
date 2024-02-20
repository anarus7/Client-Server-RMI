package ro.ubb.lab3.common;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class EntityValidator<T> {
    public void validate(T entity) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(entity);
            if (!violations.isEmpty()) {
                System.out.println("Errors:");
                for (ConstraintViolation<T> violation : violations) {
                    System.out.println(violation.getMessage());
                }
            } else {
                System.out.println("Validation successful");
            }

        }
    }
}

