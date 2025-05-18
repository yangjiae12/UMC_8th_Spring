package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StoreRequestDTO {

    @Getter
    public static class CreateStore {
        @NotBlank
        String name;

        @NotBlank
        String address;

        @NotNull
        Float score;

        @NotNull
        Long regionId;

    }
}
