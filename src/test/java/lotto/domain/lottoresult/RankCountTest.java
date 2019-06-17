package lotto.domain.lottoresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankCountTest {
    @Test
    void isWinningResultTest1() {
        assertThat(new RankCount(LottoRank.FAIL, 3).isWinningResult()).isFalse();
    }

    @Test
    void isWinningResultTest2() {
        assertThat(new RankCount(LottoRank.FIFTH, 3).isWinningResult()).isTrue();
    }
}