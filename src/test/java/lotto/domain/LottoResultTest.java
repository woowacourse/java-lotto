package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @Test
    @DisplayName("수익률을 올바르게 계산하는지 확인")
    void earning_rate() {
        Map<Rank, Integer> ranks = new HashMap<>();
        ranks.put(Rank.FIFTH, 1);
        LottoResult lottoResult = new LottoResult(ranks);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Money money = new Money("5000");

        double earningRate = lottoResult.findEarningRate(money.getMoney());
        assertThat(decimalFormat.format(earningRate)).isEqualTo("1.00");
    }
}
