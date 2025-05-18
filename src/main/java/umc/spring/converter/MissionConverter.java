package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.CreateMission request, Store store) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.CreateResultDTO toCreateResultDTO(Mission mission) {
        return MissionResponseDTO.CreateResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
