package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BankTest {
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void 구입_금액만큼_money가_증가한다() {
        bank.pay(5000);

        assertThat(bank).extracting("usedMoney").isEqualTo(5000);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 금액은_0원_이상_지불해야_한다(int payment) {
        assertThatThrownBy(() -> bank.pay(payment)).isInstanceOf(IllegalArgumentException.class);
    }
}
