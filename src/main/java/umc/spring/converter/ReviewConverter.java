package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.CreateReview request, Member member, Store store) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResponseDTO.JoinResultDTO toCreateResultDTO(Review review) {
        return ReviewResponseDTO.JoinResultDTO.builder()
                .ratingId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
