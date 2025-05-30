package umc.spring.repository.memberMission;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionIdAndStatus(@NotNull Long memberId, @NotNull Long missionId, MissionStatus missionStatus);

    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);
}
