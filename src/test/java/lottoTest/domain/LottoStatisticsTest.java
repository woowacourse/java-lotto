package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.LottoStatistics;
import lotto.domain.Rank;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoStatisticsTest {

    @Test
    void 당첨_통계_기능_테스트() {
        List<Rank> result = List.of(Rank.FIRST, Rank.SECOND, Rank.THIRD);
        LottoStatistics lottoStatistics = new LottoStatistics(result);
        EnumMap<Rank, Integer> statistics = lottoStatistics.getStatistics();

        assertAll(
                () -> assertThat(statistics.get(Rank.FIRST)).isEqualTo(1),
                () -> assertThat(statistics.get(Rank.SECOND)).isEqualTo(1),
                () -> assertThat(statistics.get(Rank.FOURTH)).isEqualTo(0),
                () -> assertThat(statistics.get(Rank.FIFTH)).isEqualTo(0),
                () -> assertThat(statistics.get(Rank.MISS)).isEqualTo(0)
        );
    }

    @Test
    void 당첨_수익률_계산_기능_테스트() {
        List<Rank> result = List.of(Rank.FIFTH, Rank.FOURTH);
        LottoStatistics lottoStatistics = new LottoStatistics(result);
        assertThat(lottoStatistics.getYield()).isEqualTo(55000 / (double) 2000);
    }
}
