package lotto.type;

import java.util.List;

import lotto.domain.ticketresult.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @DisplayName("countMatchedNumbers를 통해 특정 LottoMatchType 찾기 - 0개")
    @Test
    void Should_Return_ExactLottoMatchType_When_CountMatchedNumbers_Empty() {
        List<Rank> ranks
            = Rank.getLottoMatchType(1);
        Assertions.assertThat(ranks.size()).isEqualTo(0);
    }

    @DisplayName("countMatchedNumbers를 통해 특정 LottoMatchType 찾기 - 1개")
    @Test
    void Should_Return_ExactLottoMatchType_When_CountMatchedNumbers_Single() {
        List<Rank> ranks
            = Rank.getLottoMatchType(3);
        Assertions.assertThat(ranks.size()).isEqualTo(1);
    }

    @DisplayName("countMatchedNumbers를 통해 특정 LottoMatchType 찾기 - 2개")
    @Test
    void Should_Return_ExactLottoMatchType_When_CountMatchedNumbers_Double() {
        List<Rank> ranks
            = Rank.getLottoMatchType(5);
        Assertions.assertThat(ranks.size()).isEqualTo(2);
    }
}
