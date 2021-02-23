package lotto.ranking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RankingTest {

    @Test
    @DisplayName("상금 생성 확인")
    void priceCreate() {
        assertThat(Ranking.makePrice(4, false)).isEqualTo(Ranking.FORTH);
        assertThat(Ranking.makePrice(5, true)).isEqualTo(Ranking.SECOND);
        assertThat(Ranking.makePrice(5, false)).isEqualTo(Ranking.THIRD);
    }
}
