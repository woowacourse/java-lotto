package lotto.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoResultTest {

    @Test
    void 결과구성() {
        LottoResult result = new LottoResult();
        assertEquals(0, result.results().get(Rank.LOSE));
        result.plus(Rank.LOSE);
        assertEquals(1, result.results().get(Rank.LOSE));
    }


    @Test
    void 수익률() {
        LottoResult result = new LottoResult();
        result.plus(Rank.LOSE);
        result.plus(Rank.FIFTH);
        result.plus(Rank.FIFTH);
        assertEquals(
                new BigDecimal(Rank.FIFTH.money() + Rank.FIFTH.money() + Rank.LOSE.money())
                        .divide(new BigDecimal(3000), 3, RoundingMode.CEILING),
                result.summury());
    }

    @Test
    void 외부에서_맵_변경() {
        LottoResult result = new LottoResult();
        result.plus(Rank.FIRST);
        assertEquals(1, result.results().get(Rank.FIRST));
        result.results().put(Rank.FIRST, 3);
        assertEquals(1, result.results().get(Rank.FIRST));
    }
}