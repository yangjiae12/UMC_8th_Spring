package umc.spring.service.mission;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Mission createMission(MissionRequestDTO.CreateMission request);

    Page<Mission> getMissionsByStore(Long storeId, Integer page);
}
