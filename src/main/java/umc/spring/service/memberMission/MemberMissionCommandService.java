package umc.spring.service.memberMission;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;

public interface MemberMissionCommandService {
    MemberMission challengeMission(MemberMissionRequestDTO.CreateMemberMission request);

    Page<MemberMission> getInProgressMissionsByMember(Long memberId, Integer page);
}