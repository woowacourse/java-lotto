package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @DisplayName("총 수익금액 계산")
    @Test
    void calculateTotalPrize() {
        Map<LottoRank, Integer> rankResult = new HashMap<>();
        rankResult.put(LottoRank.FIRST, 10);
        rankResult.put(LottoRank.FIFTH, 10);
        rankResult.put(LottoRank.FOURTH, 10);
        LottoResult result = new LottoResult(rankResult);
        assertThat(result.calculateTotalPrize()).isEqualTo((2_000_000_000L + 5_000 + 50_000) * 10);
    }
}
