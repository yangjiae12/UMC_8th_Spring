package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.memberMission.MemberMissionRepository;
import umc.spring.validation.annotation.NoDuplicateChallenge;
import umc.spring.web.dto.MemberMissionRequestDTO;

@Component
@RequiredArgsConstructor
public class NoDuplicateChallengeValidator implements ConstraintValidator<NoDuplicateChallenge, MemberMissionRequestDTO.CreateMemberMission> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(MemberMissionRequestDTO.CreateMemberMission dto, ConstraintValidatorContext context) {
        if (dto.getMemberId() == null || dto.getMissionId() == null) return true;

        boolean exists = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                dto.getMemberId(), dto.getMissionId(), MissionStatus.CHALLENGING
        );

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.DUPLICATE_CHALLENGE.getCode() + ":" + ErrorStatus.DUPLICATE_CHALLENGE.getMessage())
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
