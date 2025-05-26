package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import umc.spring.validation.annotation.ExistPage;

@Component
public class PageExistValidator implements ConstraintValidator<ExistPage, Integer> {
    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        return page != null && page >= 1;
    }
}