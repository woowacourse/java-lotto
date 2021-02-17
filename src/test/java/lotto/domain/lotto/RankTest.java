package lotto.domain.lotto;

import lotto.domain.rank.Ranking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class RankTest {
    @Test
    @DisplayName("등수에 맞는 랭킹 객체를 반환한다.")
    void createRanking_getRankOfIntRankingInput() {
        for(int i=1; i<=5; i++) {
            Ranking rank = Rank.createRanking(i, 1L);
            assertThat(i ).isEqualTo(rank.getRank());
        }
    }

    @Test
    @DisplayName("등수에 속하지 않는 랭킹을 요청하면 에러")
    void createRanking_getInvalidRankOfIntRankingInput() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> Rank.createRanking(10, 0L)
        ).withMessage("매칭되는 등수가 없습니다.");
    }
}
