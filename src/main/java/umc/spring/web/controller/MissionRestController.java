package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.mission.MissionCommandService;
import umc.spring.validation.annotation.ExistPage;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Tag(name = "미션 API", description = "미션 관련 요청 처리")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/add")
    public umc.spring.apiPayload.ApiResponse<MissionResponseDTO.CreateResultDTO> create(@RequestBody @Valid MissionRequestDTO.CreateMission request) {
        Mission mission = missionCommandService.createMission(request);
        return umc.spring.apiPayload.ApiResponse.onSuccess(MissionConverter.toCreateResultDTO(mission));
    }

    @GetMapping("/stores/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게에 등록된 미션들을 10개씩 페이징하여 조회합니다. page는 1부터 시작합니다.",
            parameters = {
                    @Parameter(name = "storeId", in = ParameterIn.PATH, description = "가게 ID"),
                    @Parameter(name = "page", in = ParameterIn.QUERY, description = "조회할 페이지 번호 (1부터 시작)")
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @ApiResponse(responseCode = "STORE404", description = "존재하지 않는 가게입니다.", content = @Content(schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class))),
            @ApiResponse(responseCode = "VALIDATION_ERROR", description = "유효하지 않은 페이지 번호입니다.", content = @Content(schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)))
    })
    public umc.spring.apiPayload.ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getStoreMissions(
            @PathVariable(name = "storeId") Long storeId,
            @ExistPage @RequestParam("page") Integer page) {

        Page<Mission> missions = missionCommandService.getMissionsByStore(storeId, page);
        return umc.spring.apiPayload.ApiResponse.onSuccess(MissionConverter.toMissionPreviewListDTO(missions));
    }
}