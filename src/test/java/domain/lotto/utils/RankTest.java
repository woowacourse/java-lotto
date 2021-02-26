package domain.lotto.utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("로또 번호를 당첨 번호에 맞춰서 등수를 결정한다.")
    void testGetCorrectRankMatchResult() {
        assertThat(Rank.FIRST).isEqualTo(Rank.of(6, true));
        assertThat(Rank.FIRST).isEqualTo(Rank.of(6, false));
        assertThat(Rank.SECOND).isEqualTo(Rank.of(5, true));
        assertThat(Rank.THIRD).isEqualTo(Rank.of(5, false));
        assertThat(Rank.FOURTH).isEqualTo(Rank.of(4, true));
        assertThat(Rank.FOURTH).isEqualTo(Rank.of(4, false));
        assertThat(Rank.FIFTH).isEqualTo(Rank.of(3, true));
        assertThat(Rank.FIFTH).isEqualTo(Rank.of(3, false));
        assertThat(Rank.NO_MATCH).isEqualTo(Rank.of(0, false));
    }

}