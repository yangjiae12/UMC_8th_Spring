package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }

    public static MemberMissionResponseDTO.CreateResultDTO toCreateResultDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.CreateResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .joinedAt(LocalDateTime.now())
                .build();
    }
}
