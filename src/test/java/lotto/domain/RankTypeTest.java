package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTypeTest {
    @Test
    void 보너스볼이_포함된_5개숫자_일치하는_경우() {
        assertThat(RankType.valueOf(5, true)).isEqualTo(RankType.SECOND);
    }

    @Test
    void 보너스볼이_포함되지_않는_5개숫자_일치하는_경우() {
        assertThat(RankType.valueOf(5, false)).isEqualTo(RankType.THIRD);
    }

    @Test
    void 당첨이_안됐을경우_테스트() {
        assertThat(RankType.valueOf(1, false)).isEqualTo(RankType.NOTHING);
    }

    @Test
    void 당첨금_리턴하는지_확인() {
        assertThat(RankType.valueOf(6, false).getPrize()).isEqualTo(2000000000);
    }
}
