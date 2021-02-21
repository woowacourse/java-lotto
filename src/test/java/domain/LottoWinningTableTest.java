package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoWinningTableTest {

    @DisplayName("LottoResults를 생성하는 기능")
    @Test
    void generate() {
        //given
        Map<LottoRank, Long> results = new HashMap<>();
        results.put(LottoRank.FIRST, 1L);
        results.put(LottoRank.SECOND, 4L);

        //when
        LottoWinningTable lottoWinningTable = new LottoWinningTable(results);

        //then
        assertThat(lottoWinningTable).isNotNull();
    }

    @DisplayName("총 당첨금액을 반환하는 기능")
    @Test
    void getTotalWinningMoney() {
        //given
        Map<LottoRank, Long> results = new HashMap<>();
        results.put(LottoRank.FIRST, 1L);
        results.put(LottoRank.SECOND, 4L);
        LottoWinningTable lottoWinningTable = new LottoWinningTable(results);

        //when
        Money totalMoney = lottoWinningTable.getTotalWinningMoney();

        //then
        assertThat(totalMoney).isEqualTo(new Money(2_120_000_000));
    }
}