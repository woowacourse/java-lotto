package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("당첨 번호 매치 개수와 보너스 번호 매치 여부로 Rank 반환")
    void getRank() {
        Assertions.assertThat(Rank.getRank(5, true))
                .isEqualTo(Rank.RANK_2);
    }
}