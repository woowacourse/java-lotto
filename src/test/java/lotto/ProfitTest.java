package lotto;

import domain.LottoResult;
import domain.Profit;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {

    @Test
    void 총_수익_계산_테스트() {
        Map<LottoResult, Integer> result = new HashMap<LottoResult, Integer>(){{
            put(LottoResult.FIRST, 0);
            put(LottoResult.SECOND, 0);
            put(LottoResult.THIRD, 1);
            put(LottoResult.FOURTH, 1);
            put(LottoResult.FIFTH, 2);
        }};

        Profit profit = new Profit();
        int profitRatio = profit.calculateProfitRatio(result, 4);
        assertThat(profitRatio).isEqualTo(390);
    }
}
