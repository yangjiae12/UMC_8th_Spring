package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missions) {
        List<MissionResponseDTO.MissionPreviewDTO> list = missions.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .missionList(list)
                .listSize(list.size())
                .totalPage(missions.getTotalPages())
                .totalElements((int) missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }

    public static MissionResponseDTO.InProgressMissionDTO toInProgressMissionDTO(MemberMission mm) {
        return MissionResponseDTO.InProgressMissionDTO.builder()
                .missionSpec(mm.getMission().getMissionSpec())
                .deadline(mm.getMission().getDeadline())
                .reward(mm.getMission().getReward())
                .storeName(mm.getMission().getStore().getName())
                .build();
    }

    public static MissionResponseDTO.InProgressMissionListDTO toInProgressMissionListDTO(Page<MemberMission> memberMissions) {
        List<MissionResponseDTO.InProgressMissionDTO> list = memberMissions.stream()
                .map(MissionConverter::toInProgressMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.InProgressMissionListDTO.builder()
                .missionList(list)
                .listSize(list.size())
                .totalPage(memberMissions.getTotalPages())
                .totalElements((int) memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }

}
