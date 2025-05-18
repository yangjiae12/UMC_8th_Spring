package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionRequestDTO {

    @Getter
    public static class CreateMission {
        @NotNull
        private Integer reward;

        @NotNull
        private LocalDate deadline;

        @NotBlank
        private String missionSpec;

        @NotNull
        private Long storeId;
    }
}