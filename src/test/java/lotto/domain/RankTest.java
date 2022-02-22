package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @DisplayName("1등의 상금을 계산할 수 있다.")
    @Test
    void calculateFirstRankMoney() {
        final Rank first = Rank.FIRST;
        final long count = 3;

        assertThat(Rank.calculateMoney(first, count)).isEqualTo(6000000000L);
    }
}
