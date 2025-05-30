package umc.spring.service.mission;

import ch.qos.logback.core.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public Page<Mission> getMissionsByStore(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store 값이 없습니다."));
        return missionRepository.findAllByStore(store, PageRequest.of(page - 1, 10));
    }
}
