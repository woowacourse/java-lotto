package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @DisplayName("수익률을 계산한다")
    @Test
    void calculateYield() {
        final Map<Rank, Integer> rankResults = Rank.createInitResultMap();
        rankResults.put(Rank.FIFTH, 1);
        rankResults.put(Rank.NOT_THING, 13);

        final LottoResult lottoResult = new LottoResult(rankResults);

        assertThat(lottoResult.calculateYield()).isEqualTo(5000 / 14000.0);
    }
}
