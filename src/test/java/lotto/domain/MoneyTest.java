package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("천원 단위의 돈을 생성한다.")
    public void createMoney() {
        // given
        Money money = new Money(1000);
        // then
        Assertions.assertThat(money).isNotNull();
    }

    @Test
    @DisplayName("음수의 금액을 생성할 수 없다.")
    void throwsExceptionWithNegativeAmount() {
        // given
        int amount = -1000;
        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Money(amount));
    }

    @Test
    @DisplayName("나누기 연산을 할 수 있다.")
    public void divideMoneyWithOtherMoney() {
        // given
        Money aMoney = new Money(1000);
        Money bMoney = new Money(100);
        // when
        Money dividedMoney = aMoney.divide(bMoney);
        // then
        Assertions.assertThat(dividedMoney.getAmount()).isEqualTo(10);
    }

    @Test
    @DisplayName("대소를 비교할 수 있다.")
    public void compareGreaterThanOrEqualToMoney() {
        // given
        Money aMoney = Money.from(100);
        Money bMoney = Money.from(101);
        // when
        boolean compare = bMoney.isGreatThanOrEqualTo(aMoney);
        // then
        Assertions.assertThat(compare).isTrue();
    }

    @Test
    @DisplayName("빼기 연산을 할 수 있다.")
    public void minusMoneyWithOtherMoney() {
        // given
        Money aMoney = Money.from(1000);
        Money bMoney = Money.from(500);
        // when
        Money minusMoney = aMoney.minus(bMoney);
        // then
        Assertions.assertThat(minusMoney.getAmount()).isEqualTo(500);
    }
}
