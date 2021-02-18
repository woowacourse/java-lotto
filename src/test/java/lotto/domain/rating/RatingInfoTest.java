package lotto.domain.rating;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RatingInfoTest {

    @Test
    @DisplayName("등수 정보 업데이트")
    void updateRatingInfo() {
        RatingInfo ratingInfo = new RatingInfo();
        Rating rating = Rating.FIRST;
        ratingInfo.update(rating);
        assertThat(ratingInfo.get(rating)).isEqualTo(1);
    }
}