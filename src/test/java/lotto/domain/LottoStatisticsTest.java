package lotto.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {

    @Test
    @DisplayName("로또 순위로 통계를 만든다.")
    public void createStatisticsWithLottoRanks() {
        // given
        List<LottoRank> ranks = List.of(LottoRank.values());
        // when
        LottoStatistics statistics = LottoStatistics.fromComparedInformation(
            ranks, new Money(LottoRank.values().length * LottoLine.PRICE)
        );
        // then
        Assertions.assertThat(statistics).isNotNull();
    }
}
