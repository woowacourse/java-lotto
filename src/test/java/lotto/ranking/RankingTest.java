package lotto.ranking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RankingTest {

    @Test
    @DisplayName("상금 생성 확인")
    void priceCreate() {
        assertAll(
                () -> assertThat(Ranking.makePrice(2, false)).isEqualTo(Ranking.NOTHING),
                () -> assertThat(Ranking.makePrice(3, false)).isEqualTo(Ranking.FIFTH),
                () -> assertThat(Ranking.makePrice(4, false)).isEqualTo(Ranking.FORTH),
                () -> assertThat(Ranking.makePrice(5, false)).isEqualTo(Ranking.THIRD),
                () -> assertThat(Ranking.makePrice(5, true)).isEqualTo(Ranking.SECOND),
                () -> assertThat(Ranking.makePrice(6, false)).isEqualTo(Ranking.FIRST)
        );
    }
}
