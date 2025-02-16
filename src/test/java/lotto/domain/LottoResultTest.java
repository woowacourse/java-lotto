package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void 수익률을_계산한다() {
        Bank bank = new Bank();
        bank.pay(10000);
        LottoResult result = new LottoResult(Map.of(Rank.FIFTH, 1L));
        BigDecimal rate = result.calculateRateOfReturn(bank.getUsedMoney());
        assertThat(rate.setScale(2, RoundingMode.HALF_UP).doubleValue()).isEqualTo(0.50);
    }
}
