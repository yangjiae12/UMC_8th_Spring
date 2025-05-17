package umc.spring.service.member;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDTO;

public abstract class MemberCommandService {
    public abstract Member joinMember(MemberRequestDTO.JoinDto request);
}
