package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PrizeTest {

    @Test
    void 등수_확인() {
        //given
        Prize prize = new Prize(3, false);

        //when & then
        Assertions.assertThat(prize.getMatchRank()).isEqualTo(Rank.FIFTH);
    }
}
