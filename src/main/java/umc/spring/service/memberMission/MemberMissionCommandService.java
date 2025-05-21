package umc.spring.service.memberMission;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;

public interface MemberMissionCommandService {
    MemberMission challengeMission(MemberMissionRequestDTO.CreateMemberMission request);
}