package lotto;

import domain.LottoRank;
import domain.Profit;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {

    @Test
    void 총_수익_계산_테스트() {
        Map<LottoRank, Integer> result = new HashMap<LottoRank, Integer>(){{
            put(LottoRank.FIRST, 0);
            put(LottoRank.SECOND, 0);
            put(LottoRank.THIRD, 1);
            put(LottoRank.FOURTH, 1);
            put(LottoRank.FIFTH, 2);
        }};

        Profit profit = new Profit();
        int profitRatio = profit.calculateProfitRatio(result, 4);
        assertThat(profitRatio).isEqualTo(390);
    }
}
