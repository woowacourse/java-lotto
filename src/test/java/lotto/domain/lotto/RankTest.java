package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {
    @Test
    void 일치하는_개수에_따른_Rank_확인_1등() {
        assertThat(Rank.valueOf(6, false)).isEqualTo(Rank.FIRST);
    }

    @Test
    void 일치하는_개수에_따른_Rank_확인_2등() {
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 일치하는_개수에_따른_Rank_확인_3등() {
        assertThat(Rank.valueOf(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    void 일치하는_개수에_따른_Rank_확인_4등() {
        assertThat(Rank.valueOf(4, false)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 일치하는_개수에_따른_Rank_확인_꽝() {
        assertThat(Rank.valueOf(2, false)).isEqualTo(Rank.MISS);
    }

    @Test
    void Rank_확인_음수() {
        assertThatThrownBy(() -> Rank.valueOf(-1, false))
                .isInstanceOf(NullPointerException.class);
    }
}