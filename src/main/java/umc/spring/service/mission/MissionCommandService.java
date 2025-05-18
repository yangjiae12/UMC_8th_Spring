package umc.spring.service.mission;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Mission createMission(MissionRequestDTO.CreateMission request);
}
