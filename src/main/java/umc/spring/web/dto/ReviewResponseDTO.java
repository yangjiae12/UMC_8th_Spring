package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        Long ratingId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewListDTO {
        List<ReviewPreviewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Integer totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewDTO {
        String ownerNickname;
        Float score;
        String body;
        LocalDate createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberReviewPreviewDTO {
        private String storeName;
        private Float score;
        private String body;
        private LocalDate createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberReviewPreviewListDTO {
        private List<MemberReviewPreviewDTO> reviewList;
        private Integer listSize;
        private Integer totalPage;
        private Integer totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
