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
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.memberMission.MemberMissionCommandService;
import umc.spring.validation.annotation.ExistPage;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Tag(name = "미션 도전 API", description = "멤버의 미션 도전 관련 요청 처리")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/challenge")
    public umc.spring.apiPayload.ApiResponse<MemberMissionResponseDTO.CreateResultDTO> create(@RequestBody @Valid MemberMissionRequestDTO.CreateMemberMission request) {
        MemberMission memberMission = memberMissionCommandService.challengeMission(request);
        return umc.spring.apiPayload.ApiResponse.onSuccess(MemberMissionConverter.toCreateResultDTO(memberMission));
    }

    @GetMapping("/members/{memberId}/missions/progress")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "회원이 현재 진행 중인 미션들을 10개씩 페이징하여 조회합니다. page는 1부터 시작합니다.",
            parameters = {
                    @Parameter(name = "memberId", in = ParameterIn.PATH, description = "회원 ID"),
                    @Parameter(name = "page", in = ParameterIn.QUERY, description = "조회할 페이지 번호 (1부터 시작)")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @ApiResponse(responseCode = "MEMBER404", description = "존재하지 않는 회원입니다.", content = @Content(schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class))),
            @ApiResponse(responseCode = "VALIDATION_ERROR", description = "유효하지 않은 페이지 번호입니다.", content = @Content(schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)))
    })
    public umc.spring.apiPayload.ApiResponse<MissionResponseDTO.InProgressMissionListDTO> getInProgressMissions(
            @PathVariable(name = "memberId") Long memberId,
            @ExistPage @RequestParam("page") Integer page) {

        Page<MemberMission> missions = memberMissionCommandService.getInProgressMissionsByMember(memberId, page);
        return umc.spring.apiPayload.ApiResponse.onSuccess(MissionConverter.toInProgressMissionListDTO(missions));
    }

    @PatchMapping("/members/{memberMissionId}/complete")
    @Operation(summary = "미션 완료 처리 API", description = "진행 중인 미션을 완료 상태로 변경합니다. CHALLENGING 상태일 때만 변경 가능.")
    @Parameter(name = "memberMissionId", in = ParameterIn.PATH, description = "멤버 미션 ID")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @ApiResponse(responseCode = "MISSION404", description = "해당 멤버 미션이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class))),
            @ApiResponse(responseCode = "MISSION409", description = "이미 완료된 미션입니다.", content = @Content(schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)))
    })
    public umc.spring.apiPayload.ApiResponse<Void> completeMission(
            @PathVariable(name = "memberMissionId") Long memberMissionId) {

        memberMissionCommandService.completeMission(memberMissionId);
        return umc.spring.apiPayload.ApiResponse.onSuccess(null);
    }
}
