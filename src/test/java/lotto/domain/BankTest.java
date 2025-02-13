package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BankTest {
    @Test
    void 구입_금액만큼_money가_증가한다() {
        Bank bank = new Bank();
        bank.use(5000);

        assertThat(bank).extracting("usedMoney").isEqualTo(5000);
    }

    @Test
    void 수익률을_계산한다() {
        Bank bank = new Bank();
        bank.use(10000);
        Map<Rank, Integer> map = Map.of(Rank.FIFTH, 1);
        double result = bank.calculateRateOfReturn(map);

        assertThat(Math.floor(result * 100) / 100).isEqualTo(0.50);
    }
}