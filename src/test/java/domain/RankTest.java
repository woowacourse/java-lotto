package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    void Rank별_당첨_금액을_제대로_알려주는지_테스트() {
        assertThat(Rank.FIFTH.getWinningMoney()).isEqualTo(5000);
    }

    @Test
    void 일치하는_숫자에_맞는_rank를_되돌려주는지_테스트() {
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
    }
}
