package umc.spring.service.store;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store createStore(StoreRequestDTO.CreateStore request);
}
