package umc.spring.service.review;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewService {

    Review createReview(ReviewRequestDTO.CreateReview request);
}
