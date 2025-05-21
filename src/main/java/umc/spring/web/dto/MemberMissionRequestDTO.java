package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.NoDuplicateChallenge;

@Getter
public class MemberMissionRequestDTO {

    @Getter
    @NoDuplicateChallenge
    public static class CreateMemberMission {
        @NotNull
        Long memberId;

        @NotNull
        Long missionId;
    }
}
