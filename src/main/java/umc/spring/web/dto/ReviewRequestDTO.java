package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReview {
        @NotNull
        Float score;

        String body;

        @NotNull
        Long missionId;

        @NotNull
        Long memberId;

        @NotNull
        Long storeId;
    }
}
