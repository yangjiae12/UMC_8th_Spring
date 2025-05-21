package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.NoDuplicateChallengeValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoDuplicateChallengeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoDuplicateChallenge {
    String message() default "이미 해당 미션에 도전 중입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
