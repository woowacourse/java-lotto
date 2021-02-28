package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;
import org.junit.jupiter.api.Test;

public class EarningRateTest {

    @Test
    void calculate() {
        final RatingInfo ratingInfo = new RatingInfo();
        final long money = 10000L;
        final double expected = 200_000.0;
        ratingInfo.update(Rating.FIRST);

        EarningRate earningRate = new EarningRate();
        assertThat(earningRate.calculate(ratingInfo, 10000)).isEqualTo(expected);
    }

}
