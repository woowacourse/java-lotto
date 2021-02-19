package lotto.domain.lotto;

import lotto.domain.rank.RankFactory;
import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RankFactoryTest {
    @Test
    @DisplayName("등수에 맞는 랭킹 객체를 반환한다.")
    void createRanking_getRankOfIntRankingInput() {
        Arrays.stream(RankFactory.values()).forEach(
                v -> {
                    Rank rank = RankFactory.createRanking(v, 1L);
                    assertThat(v.isSameRank(rank.getRank())).isTrue();
                }
        );
    }

    @Test
    @DisplayName("조건에 맞는 랭킹 반환")
    void getRank_returnRankOfCondition() {
        assertThat(RankFactory.getRank(6, false)).isEqualTo(RankFactory.FIRST);
        assertThat(RankFactory.getRank(5, true)).isEqualTo(RankFactory.SECOND);
        assertThat(RankFactory.getRank(5, false)).isEqualTo(RankFactory.THIRD);
        assertThat(RankFactory.getRank(4, false)).isEqualTo(RankFactory.FOURTH);
        assertThat(RankFactory.getRank(3, false)).isEqualTo(RankFactory.FIFTH);

        assertThat(RankFactory.getRank(6, true)).isEqualTo(RankFactory.FIRST);
        assertThat(RankFactory.getRank(4, true)).isEqualTo(RankFactory.FOURTH);
        assertThat(RankFactory.getRank(3, true)).isEqualTo(RankFactory.FIFTH);
    }
}
