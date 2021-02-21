package lotto.domain.rating;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RatingCounterTest {
    @Test
    @DisplayName("등수 정보 업데이트")
    void updateRatingInfo() {
        RatingCounter ratingCounter = new RatingCounter();
        Rating rating = Rating.FIRST;
        ratingCounter.update(rating);
        assertThat(ratingCounter.get(rating)).isEqualTo(1);
    }
}