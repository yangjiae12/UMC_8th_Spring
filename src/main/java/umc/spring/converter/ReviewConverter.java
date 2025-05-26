package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public static ReviewResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(
                        review.getMember() != null ? review.getMember().getName() : "알 수 없음"
                )
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreviewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements((int) reviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }

    public static ReviewResponseDTO.MemberReviewPreviewDTO toMemberReviewDTO(Review review) {
        return ReviewResponseDTO.MemberReviewPreviewDTO.builder()
                .storeName(Optional.ofNullable(review.getStore()).map(Store::getName).orElse("알 수 없음"))
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toLocalDate() : LocalDate.now())
                .build();
    }

    public static ReviewResponseDTO.MemberReviewPreviewListDTO toMemberReviewPreviewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.MemberReviewPreviewDTO> previewList = reviewList.stream()
                .map(ReviewConverter::toMemberReviewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.MemberReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements((int) reviewList.getTotalElements())
                .listSize(previewList.size())
                .reviewList(previewList)
                .build();
    }
}
