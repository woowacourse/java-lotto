package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    void Rank별_당첨_금액을_제대로_알려주는지_테스트() {
        assertThat(Rank.FIFTH.getWinningMoney()).isEqualTo(5000);
    }
}
