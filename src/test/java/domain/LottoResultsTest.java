package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultsTest {

    @DisplayName("LottoResults를 생성하는 기능")
    @Test
    void generate() {
        //given
        Map<LottoRank, Long> results = new HashMap<>();
        results.put(LottoRank.FIRST, 1L);
        results.put(LottoRank.SECOND, 4L);

        //when
        LottoResults lottoResults = new LottoResults(results);

        //then
        assertThat(lottoResults).isNotNull();
    }

    @DisplayName("총 당첨금액을 반환하는 기능")
    @Test
    void getTotalWinningMoney() {
        //given
        Map<LottoRank, Long> results = new HashMap<>();
        results.put(LottoRank.FIRST, 1L);
        results.put(LottoRank.SECOND, 4L);
        LottoResults lottoResults = new LottoResults(results);

        //when
        Money totalMoney = lottoResults.getTotalWinningMoney();

        //then
        assertThat(totalMoney).isEqualTo(Money.valueOf(2_120_000_000));
    }
}
