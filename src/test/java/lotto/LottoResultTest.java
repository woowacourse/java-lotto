package lotto;

import domain.LottoRank;
import domain.LottoResult;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 총_수익_계산_테스트() {
        Map<LottoRank, Integer> result = new HashMap<>();
        int i = 0;
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, i++);
        }
        LottoResult lottoResult = new LottoResult(result);

        int profitRatio = lottoResult.calculateProfitRatio(10);
        assertThat(profitRatio).isEqualTo(3317);
    }
}
