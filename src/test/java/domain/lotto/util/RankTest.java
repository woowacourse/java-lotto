package domain.lotto.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("로또 등수가 잘 계산된다.")
    void lotto_rank_test() {
        assertThat(Rank.FOURTH).isEqualTo(Rank.check(4, true));
        assertThat(Rank.NO_MATCH).isEqualTo(Rank.check(10, true));
    }
}
