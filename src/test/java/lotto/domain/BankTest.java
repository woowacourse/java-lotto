package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class BankTest {

    @Test
    void 구입_금액만큼_money가_증가한다() {
        Bank bank = new Bank();
        bank.pay(5000);

        assertThat(bank).extracting("usedMoney").isEqualTo(5000);
    }
}
