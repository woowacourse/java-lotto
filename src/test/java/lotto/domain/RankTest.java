package lotto.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class RankTest {
    @Test
    void 일치_번호_개수와_보너스_일치_여부를_기반으로_등수를_판단한다() {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST);
            softly.assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
            softly.assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
            softly.assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH);
            softly.assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH);
            softly.assertThat(Rank.of(2, false)).isEqualTo(Rank.NONE);
            softly.assertThat(Rank.of(1, false)).isEqualTo(Rank.NONE);
            softly.assertThat(Rank.of(0, false)).isEqualTo(Rank.NONE);
        });
    }
}
