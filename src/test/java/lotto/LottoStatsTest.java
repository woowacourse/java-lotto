package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoStatsTest {

    RatingInfo ratingInfo;
    LottoStats lottoStats;

    @BeforeEach
    void setup() {
        ratingInfo = new RatingInfo();
        lottoStats = new LottoStats();
    }

    @Test
    void firstPrizeToString() {
        Rating rating = Rating.FIRST;
        int count = ratingInfo.get(rating);

        String actual = lottoStats.ratingToString(rating, count);

        assertThat(actual).isEqualTo("6개 일치 (2000000000원) - 0개");
    }

    @Test
    void secondPrizeToString() {
        Rating rating = Rating.SECOND;
        int count = ratingInfo.get(rating);

        String actual = lottoStats.ratingToString(rating, count);

        assertThat(actual).isEqualTo("5개 일치, 보너스 볼 일치(30000000원) - 0개");
    }
}