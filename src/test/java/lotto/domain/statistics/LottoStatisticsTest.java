package lotto.domain.statistics;

import static org.assertj.core.api.Assertions.*;

import lotto.domain.primitive.Money;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;
import lotto.domain.statistics.LottoStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {
    private static final String ENTER = System.lineSeparator();
    RatingInfo ratingInfo;
    LottoStatistics lottoStatistics;

    @BeforeEach
    void setup() {
        ratingInfo = new RatingInfo();
        lottoStatistics = new LottoStatistics(ratingInfo);
    }

    @Test
    void firstPrizeToString() {
        Rating rating = Rating.FIRST;
        int count = ratingInfo.get(rating);

        String actual = lottoStatistics.ratingToString(rating, count);

        assertThat(actual).isEqualTo("6개 일치 (2000000000원) - 0개" + ENTER);
    }

    @Test
    void secondPrizeToString() {
        Rating rating = Rating.SECOND;
        int count = ratingInfo.get(rating);

        String actual = lottoStatistics.ratingToString(rating, count);

        assertThat(actual).isEqualTo("5개 일치, 보너스 볼 일치 (30000000원) - 0개" + ENTER);
    }

    @Test
    void getEarningRate() {
        int actual = lottoStatistics.getEarningRate(new Money(5000));
        assertThat(actual).isEqualTo(0);
    }

    @Test
    void getTotalSum() {
        ratingInfo.update(Rating.FIRST);
        assertThat(lottoStatistics.totalSum()).isEqualTo(2000000000);
    }
}