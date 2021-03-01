package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticsTest {

    @DisplayName("결과값 생성 확인")
    @Test
    void checkCreate() {
        List<Result> results = Arrays.asList(Result.SECOND, Result.FIFTH);

        Statistics statistics = new Statistics(results);

        assertThat(statistics.getRankCount(Result.FIRST)).isEqualTo(0);
        assertThat(statistics.getRankCount(Result.SECOND)).isEqualTo(1);
        assertThat(statistics.getRankCount(Result.THIRD)).isEqualTo(0);
        assertThat(statistics.getRankCount(Result.FOURTH)).isEqualTo(0);
        assertThat(statistics.getRankCount(Result.FIFTH)).isEqualTo(1);

    }
}