package umc.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.mission.MissionRepository;
import umc.spring.repository.store.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Mission createMission(MissionRequestDTO.CreateMission request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게가 존재하지 않습니다."));

        Mission mission = MissionConverter.toMission(request, store);
        return missionRepository.save(mission);
    }
}
