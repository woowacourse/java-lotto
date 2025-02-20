package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("당첨 개수와 보너스 볼 일치 여부에 따른 등수 테스트")
    @Test
    void 당첨_개수와_보너스_볼_일치_여부에_따른_등수_테스트() {
        assertThat(Rank.fromResult(6, true)).isEqualTo(Rank.FIRST);
        assertThat(Rank.fromResult(6, false)).isEqualTo(Rank.FIRST);

        assertThat(Rank.fromResult(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.fromResult(5, false)).isEqualTo(Rank.THIRD);

        assertThat(Rank.fromResult(4, true)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.fromResult(4, false)).isEqualTo(Rank.FOURTH);

        assertThat(Rank.fromResult(3, true)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.fromResult(3, false)).isEqualTo(Rank.FIFTH);

        assertThat(Rank.fromResult(2, true)).isEqualTo(Rank.NONE);
        assertThat(Rank.fromResult(2, false)).isEqualTo(Rank.NONE);
    }

    @Test
    void NONE인_경우_false를_반환한다() {
        Rank rank = Rank.NONE;
        assertThat(rank.isNotNone()).isEqualTo(false);
    }

    @Test
    void NONE이_아닌_경우_false를_반환한다() {
        Rank rank = Rank.FIFTH;
        assertThat(rank.isNotNone()).isEqualTo(true);
    }

    @Test
    void SECOND인_경우_true를_반환한다() {
        Rank rank = Rank.SECOND;
        assertThat(rank.isSecond()).isEqualTo(true);
    }

    @Test
    void SECOND가_아닌_경우_false를_반환한다() {
        Rank rank = Rank.FIFTH;
        assertThat(rank.isSecond()).isEqualTo(false);
    }
}
