package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.Money.NOT_ENOUGH_MONEY_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//TODO:
// 정상적인 생성자 작동 테스트
// 검증 테스트에 메세지까지 확인하기
public class MoneyTest {
    @DisplayName("올바른 입력값 확인")
    @Test
    void moneyConstructor() {
        Money money = new Money(1_000);

        assertThat(money).isEqualTo(new Money(1_000));
    }

    @DisplayName("1000원 이하 인지 확인 ")
    @Test
    void validateNotEnoughMoney() {
        assertThatThrownBy(() -> new Money(999)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ENOUGH_MONEY_ERROR);
    }

    @DisplayName("금액 따라 로또 갯수 산출 하는지")
    @Test
    void calculateNumberOfLotto() {
        Money money = new Money(13_500);
        int numberOfLotto = money.calculateAffordableLottoQuantity();

        assertThat(numberOfLotto).isEqualTo(13);
    }

    @DisplayName("input으로 받은 금액 중에서 실제로 로또를 구매한 금액을 제대로 산출하는지")
    @Test
    void calculateMoneyActuallyInvested() {
        Money money = new Money(3_999);

        assertThat(money.calculateMoneyActuallyInvested()).isEqualTo(new Money(3_000));
    }

    @DisplayName("수익률 계산")
    @Test
    void calculateProfitRate() {
        Money moneyInvested = new Money(2_000);
        Money profit = new Money(10_000);

        assertThat(moneyInvested.calculateProfitRate(profit)).isEqualTo((float) 10_000 / 2_000);
    }

    //TODO
    // validateAffordability test
    // calculateMoneyLeft test
    @DisplayName("수동 로또 구매 후 잔돈 계산 제대로 하는지")
    @Test
    void calculateMoneyLeft() {
        Money money = new Money(5540);
        int manualLottoQuantity = 3;

        assertThat(money.calculateMoneyLeft(manualLottoQuantity)).isEqualTo(new Money(2540));
    }
}