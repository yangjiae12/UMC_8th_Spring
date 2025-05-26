package umc.spring.service.review;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewService {

    Review createReview(ReviewRequestDTO.CreateReview request);

    Page<Review> getReviewList(Long StoreId, Integer page);
}
