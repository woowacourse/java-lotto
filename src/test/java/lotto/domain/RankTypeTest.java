package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTypeTest {
    @Test
    void _1등() {
        assertThat(RankType.valueOf(6, false)).isEqualTo(RankType.FIRST);
    }

    @Test
    void _2등() {
        assertThat(RankType.valueOf(5, true)).isEqualTo(RankType.SECOND);
    }

    @Test
    void _3등() {
        assertThat(RankType.valueOf(5, false)).isEqualTo(RankType.THIRD);
    }

    @Test
    void _4등() {
        assertThat(RankType.valueOf(4, false)).isEqualTo(RankType.FOURTH);
    }

    @Test
    void _5등() {
        assertThat(RankType.valueOf(3, false)).isEqualTo(RankType.FIFTH);
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
