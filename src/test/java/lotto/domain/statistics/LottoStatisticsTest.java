package lotto.domain.statistics;

import lotto.domain.primitive.Money;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsTest {
    private static final String ENTER = System.lineSeparator();
    private RatingCounter ratingCounter;
    private LottoStatistics lottoStatistics;

    @BeforeEach
    void setup() {
        ratingCounter = new RatingCounter();
        lottoStatistics = new LottoStatistics(ratingCounter);
    }

    @Test
    void getEarningRate() {
        double actual = lottoStatistics.getEarningRate(new Money(5000));
        assertThat(actual).isEqualTo(0);
    }

    @Test
    void getTotalSum() {
        ratingCounter.update(Rating.FIRST);
        assertThat(lottoStatistics.totalSum()).isEqualTo(2000000000);
    }
}