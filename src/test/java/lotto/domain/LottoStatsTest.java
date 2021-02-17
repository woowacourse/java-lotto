package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.domain.LottoStats;
import lotto.domain.Money;
import lotto.domain.Rating;
import lotto.domain.RatingInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoStatsTest {
    private static final String ENTER = System.lineSeparator();
    RatingInfo ratingInfo;
    LottoStats lottoStats;

    @BeforeEach
    void setup() {
        ratingInfo = new RatingInfo();
        lottoStats = new LottoStats(ratingInfo);
    }

    @Test
    void firstPrizeToString() {
        Rating rating = Rating.FIRST;
        int count = ratingInfo.get(rating);

        String actual = lottoStats.ratingToString(rating, count);

        assertThat(actual).isEqualTo("6개 일치 (2000000000원) - 0개" + ENTER);
    }

    @Test
    void secondPrizeToString() {
        Rating rating = Rating.SECOND;
        int count = ratingInfo.get(rating);

        String actual = lottoStats.ratingToString(rating, count);

        assertThat(actual).isEqualTo("5개 일치, 보너스 볼 일치 (30000000원) - 0개" + ENTER);
    }

    @Test
    void getEarningRate() {
        int actual = lottoStats.getEarningRate(new Money(5000));
        assertThat(actual).isEqualTo(0);
    }

    @Test
    void getTotalSum() {
        ratingInfo.update(Rating.FIRST);
        assertThat(lottoStats.totalSum()).isEqualTo(2000000000);
    }
}