package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @Test
    @DisplayName("당첨금에 따라 수익률을 맞게 계산하는지 확인")
    void earning_rate() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Money money = new Money("14000");

        Map<Rank, Integer> rankCount = new HashMap<>();
        rankCount.put(Rank.FIFTH, 1);
        LottoResult lottoResult = new LottoResult(rankCount);

        double earningRate = lottoResult.findEarningRate(money.getMoney());
        assertThat(decimalFormat.format(earningRate)).isEqualTo("0.36");
    }
}
