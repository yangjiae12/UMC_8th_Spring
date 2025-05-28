package umc.spring.service.memberMission;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.member.MemberRepository;
import umc.spring.repository.memberMission.MemberMissionRepository;
import umc.spring.repository.mission.MissionRepository;
import umc.spring.web.dto.MemberMissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MemberMission challengeMission(MemberMissionRequestDTO.CreateMemberMission request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("미션 없음"));

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
        return memberMissionRepository.save(memberMission);
    }

    @Override
    public Page<MemberMission> getInProgressMissionsByMember(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));
        return memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, PageRequest.of(page - 1, 10));
    }

    @Override
    @Transactional
    public void completeMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_MISSION_NOT_FOUND));

        if (memberMission.getStatus() != MissionStatus.CHALLENGING) {
            throw new GeneralException(ErrorStatus.MEMBER_MISSION_ALREADY_COMPLETED);
        }

        memberMission.changeStatus(MissionStatus.COMPLETED);
    }
}
