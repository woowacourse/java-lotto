package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BankTest {
    @Test
    void 구입_금액만큼_money가_증가한다() {
        Bank bank = new Bank();
        bank.pay(5000);

        assertThat(bank).extracting("usedMoney").isEqualTo(5000);
    }

    @Test
    void 수익률을_계산한다() {
        Bank bank = new Bank();
        bank.pay(10000);
        Map<Rank, Long> map = Map.of(Rank.FIFTH, 1L);
        BigDecimal result = bank.calculateRateOfReturn(map);
        assertThat(result.setScale(2, RoundingMode.HALF_UP).doubleValue()).isEqualTo(0.50);
    }
}