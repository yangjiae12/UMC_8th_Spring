package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberMissionRequestDTO {

    @Getter
    public static class CreateMemberMission {
        @NotNull
        Long memberId;

        @NotNull
        Long missionId;
    }
}
