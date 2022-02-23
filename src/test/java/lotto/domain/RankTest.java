package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("보너스 볼을 포함하여 5개를 맞춘 경우 2등이어야한다.")
    @Test
    void 이등_보너스_포함_당첨() {
        // given
        Rank rank = Rank.of(5, true);

        // when & then
        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrizeMoney()).isEqualTo(30_000_000);
    }
}