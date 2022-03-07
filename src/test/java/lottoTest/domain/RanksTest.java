package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.Rank;
import lotto.domain.Ranks;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class RanksTest {

    @Test
    void 당첨_통계_기능_테스트() {
        List<Rank> result = List.of(Rank.MATCH_SIX_NUMBERS, Rank.MATCH_FIVE_AND_BONUS_NUMBERS, Rank.MATCH_FIVE_NUMBERS);
        Ranks ranks = Ranks.getRanksFrom(result);
        Map<Rank, Integer> statistics = ranks.getStatistics();

        assertThat(statistics.get(Rank.MATCH_SIX_NUMBERS)).isEqualTo(1);
        assertThat(statistics.get(Rank.MATCH_FIVE_AND_BONUS_NUMBERS)).isEqualTo(1);
        assertThat(statistics.get(Rank.MATCH_FIVE_NUMBERS)).isEqualTo(1);
        assertThat(statistics.get(Rank.MATCH_FOUR_NUMBERS)).isEqualTo(0);
        assertThat(statistics.get(Rank.MATCH_THREE_NUMBERS)).isEqualTo(0);
        assertThat(statistics.get(Rank.MATCH_MISS)).isEqualTo(0);
    }
}
