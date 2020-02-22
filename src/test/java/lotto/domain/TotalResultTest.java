package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class TotalResultTest {

    @Test
    void calculateProfitRate() {
        Map<LottoRank, Integer> rankResult = new HashMap<>();
        rankResult.put(LottoRank.FIRST, 10);
        rankResult.put(LottoRank.FIFTH, 10);
        rankResult.put(LottoRank.FOURTH, 10);
        LottoResult result = new LottoResult(rankResult);
        long totalPrize = (2_000_000_000L + 5_000L + 50_000L) * 10L;
        TotalResult totalResult = new TotalResult(result, new LottoCount(30));
        assertThat(totalResult.getProfitRate()).isEqualTo(totalPrize / 30000L * 100L);
    }
}
