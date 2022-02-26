package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.Rank;
import lotto.domain.Ranks;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class RanksTest {

    @Test
    void 당첨_통계_기능_테스트() {
        List<Rank> result = List.of(Rank.FIRST, Rank.SECOND, Rank.THIRD);
        Ranks ranks = new Ranks(result);
        EnumMap<Rank, Integer> statistics = ranks.getStatistics();

        assertThat(statistics.get(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.get(Rank.SECOND)).isEqualTo(1);
        assertThat(statistics.get(Rank.THIRD)).isEqualTo(1);
        assertThat(statistics.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(statistics.get(Rank.FIFTH)).isEqualTo(0);
        assertThat(statistics.get(Rank.MISS)).isEqualTo(0);
    }
}
